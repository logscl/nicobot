package com.st.nicobot.job;

import java.util.concurrent.TimeUnit;

import org.picocontainer.annotations.Inject;

import com.st.nicobot.job.task.HeartBeatTask;

/**
 * @author Julien
 *
 */
public class HeartBeatJob extends AbstractJob {

	@Inject
	private HeartBeatTask task;
	
	public HeartBeatJob() {}
	
	public void start() {
		delay = TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES);
		startAtCreation = true;
		name = "HeartBeatJob";

		super.task = task;
	}

	@Override
	public boolean isAutoStartup() {
		return false;
	}
}
