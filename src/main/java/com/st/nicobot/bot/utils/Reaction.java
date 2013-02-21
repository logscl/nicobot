package com.st.nicobot.bot.utils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Reaction implements Serializable {

	private static final long serialVersionUID = -9167874891022108827L;
	
	private Pattern pattern;
	private String response;
	private int cooldownTimer;
	private Map<String, Date> lastSpokenTimeByChan = new HashMap<String, Date>();
	
	/**
	 * Crée une réaction a un message sur le chan
	 * @param regex La regex à envoyer à Pattern.regex
	 * @param response la réponse à retourner en cas de succès au message
	 * @param caseInsensitive précise si le message est Case Insensitive ou non
	 * @param cooldownTimer Temps minimum pour ne pas répéter la réaction (en secondes)
	 */
	public Reaction(String regex, String response, boolean caseInsensitive,	int cooldownTimer) {
		pattern = Pattern.compile(regex, (caseInsensitive) ? Pattern.CASE_INSENSITIVE : 0);
		this.response = response;
		this.cooldownTimer = cooldownTimer;
	}
	
	/**
	 * Crée une réaction Case insensitive et sans cooldown
	 * @param regex La regex à envoyer à Pattern.regex
	 * @param response la réponse à retourner en cas de succès au message
	 * @param response
	 */
	public Reaction(String regex, String response) {
		pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		this.response = response;
		this.cooldownTimer = 0;
	}
	
	/**
	 * Vérifie la regex par rapport au message donné.
	 * Si elle est valide, retoune <code>true</code>
	 * @param message le message à vérifier
	 * @return <code>true</code> si la regex valide le message.
	 */
	public boolean match(String message) {
		return pattern.matcher(message).matches();
	}
	
	public boolean canSaySentence(String chan) {
		if(cooldownTimer > 0) {
			if(getSpokenTime(chan) != null) {
				Date lastTime = getSpokenTime(chan);
				Calendar cal = Calendar.getInstance();
				cal.setTime(lastTime);
				cal.add(Calendar.SECOND, cooldownTimer);
				if(cal.getTime().after(new Date())) {
					return false;
				}
			}
		}
		return true;
	}
	
	public Date getSpokenTime(String chan) {
		return lastSpokenTimeByChan.get(chan);
	}
	
	public void addSpokenTime(String chan, Date date) {
		lastSpokenTimeByChan.put(chan, date);
	}

	public Pattern getPattern() {
		return pattern;
	}

	public String getResponse() {
		return response;
	}

	public int getCooldownTimer() {
		return cooldownTimer;
	}
}
