package com.st.nicobot.internal.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.services.LeetGreetingService;

public class LeetGreetingServiceImpl implements LeetGreetingService {
	
	private static Logger logger = LoggerFactory.getLogger(LeetGreetingServiceImpl.class);

    private boolean leetHourActive = false;

    // clé=chan,list=users
    private Map<String,Set<String>> leetGreeters = new HashMap<String,Set<String>>();

    private static Pattern[] triggers = new Pattern[]{
            Pattern.compile(".*h+ ?g+ ?t+.*", Pattern.CASE_INSENSITIVE),
            Pattern.compile(".*Happy.*Geek.*Time.*", Pattern.CASE_INSENSITIVE)
    };

    @Override
    public void init() {
        leetHourActive = true;
        leetGreeters = new HashMap<String, Set<String>>();
    }

    public void finish() {
        leetHourActive = false;
    }

    @Override
    public void addGreeter(String channel, String nickname, String message) {
        if(!hasAlreadyGreeted(channel,nickname)) {
            for(Pattern pattern : triggers) {
                if(pattern.matcher(message).matches()) {
                	logger.debug("Cha-ching ! trigger found");
                    leetGreeters.get(channel).add(nickname);
                }
            }
        }
    }

    @Override
    public boolean isLeetHourActive() {
        return leetHourActive;
    }

    private boolean hasAlreadyGreeted(String channel, String nickname) {
        if(leetGreeters.get(channel) == null) {
            leetGreeters.put(channel, new HashSet<String>());
            return false;
        }   else {
            // normalement non nécessaire avec le set
            for(String nick : leetGreeters.get(channel)) {
                if(nickname.equalsIgnoreCase(nick)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public Map<String, Set<String>> getGreeters() {
    	return leetGreeters;
    }
}
