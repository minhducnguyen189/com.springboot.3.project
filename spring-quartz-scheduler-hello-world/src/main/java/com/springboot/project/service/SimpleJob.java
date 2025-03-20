package com.springboot.project.service;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.MessageFormat;

public class SimpleJob implements Job {

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
    String param = dataMap.getString("param");
    System.out.println(MessageFormat.format("Job: {0}; Param: {1}", getClass(), param));
  }
}
