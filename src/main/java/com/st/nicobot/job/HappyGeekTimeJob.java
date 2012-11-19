/**
 * 
 */
package com.st.nicobot.job;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import com.st.nicobot.job.task.HappyGeekTimeTask;

/**
 * @author Julien
 *
 */
public class HappyGeekTimeJob extends AbstractJob {

	public HappyGeekTimeJob() {
		delay = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
		task = new HappyGeekTimeTask();
		startAtCreation = true;
		name = "HappyGeekTimeJob";		
	}
	
	@Override
	public void start() {
		if (timer == null) {
			timer = new Timer();
			
			Calendar nextUpdate = Calendar.getInstance(TimeZone.getTimeZone("CET"));
			nextUpdate.set(Calendar.HOUR_OF_DAY, 13);
			nextUpdate.set(Calendar.MINUTE, 37);
			Date nextUpdateDate = nextUpdate.getTime();
			
			Calendar currentCalendar = Calendar.getInstance(TimeZone.getTimeZone("CET"));
			Date currentDate = currentCalendar.getTime();
			
			if (currentDate.after(nextUpdateDate)) {
				nextUpdate.set(Calendar.DAY_OF_YEAR, nextUpdate.get(Calendar.DAY_OF_YEAR) + 1);
				nextUpdateDate = nextUpdate.getTime();
			}
			
			timer.scheduleAtFixedRate(getTask(), nextUpdateDate, getDelay());
			
			System.out.println("Job " + name + " started !");
		}
	}
	
}
