package com.st.nicobot.internal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javassist.Modifier;

import org.picocontainer.MutablePicoContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.context.ApplicationContextAware;
import com.st.nicobot.context.ClassLoader;
import com.st.nicobot.job.Job;
import com.st.nicobot.services.SchedulerService;

/**
 * @author Julien
 *
 */
public class SchedulerServiceImpl implements SchedulerService, ApplicationContextAware {

	private static Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);
	
	private MutablePicoContainer appCtx;
	
	private List<Job> jobs;
	
	public SchedulerServiceImpl() {	}
	
	@Override
	public void startScheduler() {
		logger.info("Starting all jobs ...");
		
		for(Job j : getJobs()) {
			j.launch();
		}
	}

	@Override
	public void stopScheduler() {
		logger.info("Stopping all jobs ...");
	
		for(Job j : getJobs()) {
			j.terminate();
		}
	}
	
	@Override
	public void setApplicationContext(MutablePicoContainer appCtx) {
		this.appCtx = appCtx;
	}
	
	private List<Job> getJobs() {
		if (jobs == null) {
			jobs = new ArrayList<Job>();
			
			Set<Class<? extends Job>> classes = ClassLoader.getSubTypesOf(Job.class);
			
			for(Class<? extends Job> clazz : classes) {
				if (!Modifier.isAbstract(clazz.getModifiers())) {
					jobs.add(appCtx.getComponent(clazz));
				}
			}
		}
		
		return jobs;
	}
}
