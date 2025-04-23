package com.springboot.project.service;

import com.springboot.project.config.ApplicationProperty;
import com.springboot.project.entity.EmailSchedulerEntity;
import com.springboot.project.helper.JobSchedulerHelper;
import com.springboot.project.job.EmailJob;
import com.springboot.project.model.JobDataEnum;
import java.time.LocalTime;
import java.util.*;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSchedulerService {

    private static final String JOB_GROUP = "email-job";

    private final ApplicationProperty applicationProperty;
    private final EmailDataService emailDataService;
    private final Scheduler scheduler;

    @Autowired
    public EmailSchedulerService(
            ApplicationProperty applicationProperty,
            EmailDataService emailDataService,
            Scheduler scheduler) {
        this.applicationProperty = applicationProperty;
        this.emailDataService = emailDataService;
        this.scheduler = scheduler;
    }

    public void triggerScheduler(UUID emailDataId) {
        EmailSchedulerEntity entity = this.emailDataService.getEmailSchedulerEntity(emailDataId);

        JobDataMap jobDataMap = this.buildJobDataMap(entity);
        JobSchedulerHelper jobSchedulerHelper =
                new JobSchedulerHelper.Builder()
                        .withTimeZoneId(this.applicationProperty.getQuartz().getTimeZone())
                        .withJobClass(EmailJob.class)
                        .withJobGroup(JOB_GROUP)
                        .build();

        JobDetail jobDetail =
                jobSchedulerHelper.buildJobDetail(String.valueOf(entity.getId()), jobDataMap);
        Map<JobDetail, Set<? extends Trigger>> scheduleData = new HashMap<>();
        Set<Trigger> triggers = new HashSet<>();
        for (LocalTime time : entity.getTimesOfDay()) {
            Trigger trigger =
                    jobSchedulerHelper.buildTrigger(jobDetail, entity.getDaysOfWeek(), time);
            triggers.add(trigger);
        }
        scheduleData.put(jobDetail, triggers);
        try {
            List<TriggerKey> existedTriggerKeys =
                    this.scheduler.getTriggersOfJob(jobDetail.getKey()).stream()
                            .map(Trigger::getKey)
                            .toList();
            this.scheduler.unscheduleJobs(existedTriggerKeys);
            this.scheduler.scheduleJobs(scheduleData, true);
        } catch (SchedulerException ex) {
            throw new RuntimeException("Can not schedule job", ex);
        }
    }

    private JobDataMap buildJobDataMap(EmailSchedulerEntity entity) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(JobDataEnum.SUBJECT.getValue(), entity.getSubject());
        jobDataMap.put(JobDataEnum.EMAIL_CONTENT.getValue(), entity.getEmailContent());
        jobDataMap.put(JobDataEnum.EMAIL_ADDRESS.getValue(), entity.getEmailAddress());
        return jobDataMap;
    }
}
