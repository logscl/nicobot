package com.st.nicobot.job;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.job.task.HappyGeekTimeTask;

/**
 * @author Julien
 *
 */
public class HappyGeekTimeJob extends AbstractJob {

	private static Logger logger = LoggerFactory.getLogger(HappyGeekTimeJob.class);
	
	@Inject
	private HappyGeekTimeTask task;
	
	public HappyGeekTimeJob() {}
	
	public void start() {
		delay = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
		startAtCreation = true;
		name = "HappyGeekTimeJob";		
		
		super.task = task;
	}
	
	@Override
	public void launch() {
		if (timer == null) {
			timer = new Timer();
			
			Calendar nextUpdate = Calendar.getInstance(TimeZone.getTimeZone("CET"));
			nextUpdate.set(Calendar.HOUR_OF_DAY, 13);
			nextUpdate.set(Calendar.MINUTE, 37);
			nextUpdate.set(Calendar.SECOND, 00);
			Date nextUpdateDate = nextUpdate.getTime();
			
			Calendar currentCalendar = Calendar.getInstance(TimeZone.getTimeZone("CET"));
			Date currentDate = currentCalendar.getTime();
			
			if (currentDate.after(nextUpdateDate)) {
				nextUpdate.set(Calendar.DAY_OF_YEAR, nextUpdate.get(Calendar.DAY_OF_YEAR) + 1);
				nextUpdateDate = nextUpdate.getTime();
			}
			
			timer.scheduleAtFixedRate(getTask(), nextUpdateDate, getDelay());
			
			logger.info("Job {} started, task's first execution in {} seconds.", 
					name, ((nextUpdateDate.getTime() - currentDate.getTime())/1000));	
		}
	}
	
}
