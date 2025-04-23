package com.springboot.project.job;

import com.springboot.project.config.ApplicationProperty;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class EmailJob extends QuartzJobBean {

    private final ApplicationProperty applicationProperty;

    @Autowired
    public EmailJob(ApplicationProperty applicationProperty) {
        this.applicationProperty = applicationProperty;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        ZonedDateTime zonedDateTime =
                ZonedDateTime.now(ZoneId.of(this.applicationProperty.getQuartz().getTimeZone()));
        System.out.println(
                "Email Job triggered at: " + zonedDateTime + " with context: " + context);
    }
}
