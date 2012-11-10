/**
 * 
 */
package com.st.nicobot.job;

import java.util.concurrent.TimeUnit;

import com.st.nicobot.job.task.HeartBeatTask;

/**
 * @author Julien
 *
 */
public class HeartBeatJob extends AbstractJob {

	public HeartBeatJob() {
		delay = TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES);
		task = new HeartBeatTask();
		startAtCreation = true;
		name = "HeartBeatJob";
	}

}
