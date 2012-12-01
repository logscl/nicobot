package com.st.nicobot.job;

import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.job.task.Task;

/**
 * @author Julien
 *
 */
public abstract class AbstractJob implements Job {

	private static Logger logger = LoggerFactory.getLogger(AbstractJob.class);
	
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
	public void launch() {
		if (timer == null) {
			timer = new Timer();
			timer.scheduleAtFixedRate(getTask(), getStartDelay(), getDelay());

			logger.info("Job {} started, task's first execution in {} seconds.", name, (getStartDelay()/1000));
		}
	}
	
	@Override
	public void terminate() {
		if (timer != null) {
			timer.cancel();
			
			logger.info("Job {} stopped !", name);
		}
	}

}
