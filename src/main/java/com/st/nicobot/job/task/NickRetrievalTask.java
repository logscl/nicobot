package com.st.nicobot.job.task;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.utils.NicobotProperty;
import com.st.nicobot.context.annotations.Component;
import com.st.nicobot.services.PropertiesService;
import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Logs on 11-12-14.
 */
@Component
public class NickRetrievalTask extends Task {

    private static final Logger logger = LoggerFactory.getLogger(NickRetrievalTask.class);

    @Inject
    private NicoBot bot;

    @Inject
    private PropertiesService props;


    @Override
    public void run() {
        String originalNickname = props.get(NicobotProperty.BOT_NAME);

        if(!bot.getNick().equals(originalNickname)) {
            logger.info("Will now try to get original nickname back.");
            bot.changeNick(originalNickname);
        }
    }
}
