package com.springboot.project.helper;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;

public class JobSchedulerHelper {

    private static final String SEPARATOR = "|";

    private final String timeZoneId;
    private final String jobGroup;
    private final Class <? extends Job> jobClass;

    public JobSchedulerHelper(String timeZoneId, String jobGroup, Class<? extends Job> jobClass) {
        this.timeZoneId = timeZoneId;
        this.jobGroup = jobGroup;
        this.jobClass = jobClass;
    }

    public JobDetail buildJobDetail(String identify, JobDataMap jobDataMap) {
        return JobBuilder.newJob(this.jobClass)
                .withIdentity(identify, this.jobGroup)
                .withDescription(identify)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    public Trigger buildTrigger(JobDetail jobDetail, Set<Integer> daysOfWeek, LocalTime time) {
        return TriggerBuilder
                .newTrigger()
                .withIdentity(jobDetail.getKey().getName() + SEPARATOR + time, this.jobGroup)
                .withDescription(jobDetail.getKey().getName())
                .forJob(jobDetail)
                .withSchedule(CronScheduleBuilder
                        .atHourAndMinuteOnGivenDaysOfWeek(time.getHour(), time.getMinute(), daysOfWeek.toArray(new Integer[0]))
                        .withMisfireHandlingInstructionFireAndProceed()
                        .inTimeZone(TimeZone.getTimeZone(this.timeZoneId)))
                .startNow()
                .build();
    }

    public static class Builder {
        private String timeZoneId;
        private String jobGroup;
        private Class <? extends Job> jobClass;

        public Builder() {
        }

        public Builder withTimeZoneId(String timeZoneId) {
            this.timeZoneId = timeZoneId;
            return this;
        }

        public Builder withJobGroup(String jobGroup) {
            this.jobGroup = jobGroup;
            return this;
        }

        public Builder withJobClass(Class <? extends Job> jobClass) {
            this.jobClass = jobClass;
            return this;
        }

        public JobSchedulerHelper build() {
            this.validateObject();
            return new JobSchedulerHelper(this.timeZoneId, this.jobGroup, this.jobClass);
        }

        private void validateObject() {
            if (Objects.isNull(this.timeZoneId)
                || Objects.isNull(this.jobClass)
                || Objects.isNull(this.jobGroup)) {
                throw new IllegalArgumentException("Can not create JobSchedulerHelper some params got null value");
            }
        }
    }

}
