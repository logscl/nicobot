package com.st.nicobot.services;

import java.util.Map;
import java.util.Set;

public interface LeetGreetingService {

    public void init();
    public void finish();

    /**
     * Parse le message, et si nécessaire, ajoute le greeter dans la liste des greeters
     * @param channel le channel sur lequel le greeter se trouve
     * @param nickname son pseudo
     * @param message le message qu'il a envoyé
     */
    public void addGreeter(String channel, String nickname, String message);

    public boolean isLeetHourActive();
    public Map<String, Set<String>> getGreeters();
}
