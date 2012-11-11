package com.st.nicobot.job;

import com.st.nicobot.job.task.Task;

/**
 * @author Julien
 *
 */
public interface Job {

	/**
	 * Retourne la tache
	 * @return
	 */
	public Task getTask();
	
	/**
	 * Demarre le job
	 */
	public void start();
	
	/**
	 * Interrompt le job
	 */
	public void stop();
	
}
