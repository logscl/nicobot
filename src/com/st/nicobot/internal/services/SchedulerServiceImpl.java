/**
 * 
 */
package com.st.nicobot.internal.services;

import java.util.List;

import com.st.nicobot.job.Job;
import com.st.nicobot.services.SchedulerService;
import com.st.nicobot.utils.ClassLoader;

/**
 * @author Julien
 *
 */
public class SchedulerServiceImpl implements SchedulerService {

	private List<Job> jobs;
	
	private static SchedulerService instance;
	
	public static SchedulerService getInstance() {
		if (instance == null) {
			instance = new SchedulerServiceImpl();
			((SchedulerServiceImpl)instance).init();
		}
		
		return instance;
	}
	
	public void init() {
		 jobs = ClassLoader.getInstance().getInstancesOfClass(Job.class);
	}
	
	@Override
	public void startScheduler() {
		System.out.println("Starting all jobs ...");
		
		for(Job j : jobs) {
			j.start();
		}
	}

	@Override
	public void stopScheduler() {
		System.out.println("Stopping all jobs ...");
	
		for(Job j : jobs) {
			j.stop();
		}
	}
}
