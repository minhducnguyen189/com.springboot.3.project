package com.springboot.project.service;

import com.springboot.project.job.EmailJob;
import org.quartz.*;
import org.quartz.spi.MutableTrigger;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Service
public class JobDetailService {

    public JobDetail buildJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();

        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString())
                .withDescription("logging job")
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    public Trigger buildTrigger(JobDetail jobDetail, Set<Integer> daysOfWeek, LocalTime time) {
        MutableTrigger mutableTrigger = DailyTimeIntervalScheduleBuilder
                .dailyTimeIntervalSchedule()
                .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(time.getHour(), time.getMinute()))
                .onDaysOfTheWeek(daysOfWeek)
                .withMisfireHandlingInstructionFireAndProceed()
                .build();
        return TriggerBuilder
                .newTrigger()
                .forJob(jobDetail)
                .withSchedule(mutableTrigger.getScheduleBuilder())
                .build();
    }

}
