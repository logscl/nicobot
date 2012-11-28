/**
 * 
 */
package com.st.nicobot.internal.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.job.Job;
import com.st.nicobot.services.SchedulerService;
import com.st.nicobot.utils.ClassLoader;

/**
 * @author Julien
 *
 */
public class SchedulerServiceImpl implements SchedulerService {

	private static Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);
	
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
		logger.info("Starting all jobs ...");
		
		for(Job j : jobs) {
			j.start();
		}
	}

	@Override
	public void stopScheduler() {
		logger.info("Stopping all jobs ...");
	
		for(Job j : jobs) {
			j.stop();
		}
	}
}
