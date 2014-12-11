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
	public void launch();
	
	/**
	 * Interrompt le job
	 */
	public void terminate();

	/**
	 * Indique si le job doit démarrer avec le bot ou non.
	 * Permet également de désactiver un job.
	 */
	public boolean isAutoStartup();

}
