/**
 * 
 */
package com.st.nicobot.job;

import java.util.Timer;

import com.st.nicobot.job.task.Task;

/**
 * @author Julien
 *
 */
public abstract class AbstractJob implements Job {

	protected boolean startAtCreation;
	protected long delay;
	protected Timer timer;
	protected Task task;
	protected String name;
	
	/**
	 * Retourne l'intervalle de temps entre 2 executions d'une tache
	 * @return
	 */
	public long getDelay() {
		return delay;
	}

	/**
	 * Est ce que la tache doit etre executée à sa création ?
	 * @return
	 */
	public boolean isStartAtCreation() {
		return startAtCreation;
	}

	@Override
	public Task getTask() {
		return task;
	}
	
	/**
	 * Retourne le delai avant le demarrage du job.<br/>
	 * Si {@code startAtCreation} est true, alors 0 est retourné.<br/>
	 * Sinon, c'est {@link AbstractJob#getDelay()}
	 * @return
	 */
	protected long getStartDelay() {
		long startDelay = 0;
		
		if (!startAtCreation) {
			startDelay = getDelay();
		}
		
		return startDelay;
	}

	@Override
	public void start() {
		if (timer == null) {
			timer = new Timer();
			timer.scheduleAtFixedRate(getTask(), getStartDelay(), getDelay());
			
			System.out.println("Job " + name + " started !");
		}
	}
	
	@Override
	public void stop() {
		if (timer != null) {
			timer.cancel();
			
			System.out.println("Job " + name + " stopped !");
		}
	}

}
