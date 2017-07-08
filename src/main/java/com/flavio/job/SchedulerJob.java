package com.flavio.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SchedulerJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		System.out.println("JSF2 + Quartz");
	}

}
