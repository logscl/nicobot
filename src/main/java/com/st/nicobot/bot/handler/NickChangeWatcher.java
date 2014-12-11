package com.st.nicobot.bot.handler;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.event.NickChangeEvent;
import com.st.nicobot.bot.utils.Random;
import com.st.nicobot.services.NickChangeService;
import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Logs on 10-12-14.
 */
public class NickChangeWatcher implements NickChangeEvent {

    @Inject
    private NickChangeService nickChangeService;

    @Inject
    private NicoBot bot;


    @Override
    public void onNickChange(String oldNick, String login, String hostname, String newNick) {

        if(newNick.startsWith("nico") && !login.endsWith(bot.getLogin())) {
            nickChangeService.changeNick();
        }
    }
}
