package com.st.nicobot.internal.services;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.utils.Random;
import com.st.nicobot.services.NickChangeService;
import org.joda.time.DateTime;
import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Logs on 11-12-14.
 */
public class NickChangeServiceImpl implements NickChangeService  {

    private static final Logger logger = LoggerFactory.getLogger(NickChangeServiceImpl.class);

    private static int MIN_PERIOD_BETWEEN_NICK_CHANGES = 5;

    @Inject
    private NicoBot bot;

    private String [] nicks = new String[]{
            "Nicoach",	        "Nicobol",	        "Nicocacola",	    "Nicocaine",
            "Nicochonne",	    "Nicocorico",	    "Nicocotier",	    "Nicocu",
            "Nicodepostal", 	"Nicodesource",	    "Nicoefficient",    "NicoffreAOutils",
            "Nicofidis",	    "Nicollishop",	    "Nicoloriage",	    "Nicoloscopie",
            "Nicolruyt",	    "Nicompadre",	    "Nicomparse",	    "Nicomplement",
            "Nicompliquer", 	"Nicomposition",    "Nicomptable",	    "Nicomputer",
            "Niconcept",	    "Niconcubinage",    "tobociN",	        "Niconnard",
            "Niconnexion",  	"Niconoclaste",	    "Niconsanguin",	    "Niconsternant",
            "Niconstipation",	"Niconstrictor",    "Niconstruction",	"Niconvertisseur",
            "Nicopine",         "Nicoprophagie",    "Nicoproprio",	    "Nicopter",
            "Nicopulation", 	"Nicopyright",	    "NicoqueEnPate",    "Nicoquelicot",
            "Nicoran",          "Nicorette",	    "Nicoreus",	        "Nicorona",
            "Nicorrecteur",	    "Nicorrelation",    "Nicorrompu ",	    "Nicorrosif",
            "Nicorruptible",    "Nicortex",	        "Nicosmetique",	    "Nicostacroisier",
            "Nicostar",	        "Nicostaud",	    "Nicotalos",	    "Nicotangente",
            "Nicotedemaille",   "Nicougar",	        "Nicouille",	    "Nicouleur",
            "Nicoumere",	    "Nicoursedehaies",	"Nicovoiturage",	"Nicowgirl",
            "Nicondom"
    };

    private DateTime lastNickChange;

    private Thread changeNickThread;

    @Override
    public void changeNick() {
        if(lastNickChange == null || lastNickChange.plusMinutes(MIN_PERIOD_BETWEEN_NICK_CHANGES).isBefore(DateTime.now())) {
            if (changeNickThread == null || !changeNickThread.isAlive()) {
                initThread();
                changeNickThread.start();
            } else {
                logger.info("nick change is already runninng");
            }
        } else {
            logger.info("Nick was already changed in the last {} minutes",MIN_PERIOD_BETWEEN_NICK_CHANGES);
        }

    }

    private void initThread() {
        changeNickThread = new Thread("ChangeNameThread") {
            public void run() {
                String oldNick = bot.getNick();
                String currentNick = oldNick;

                while(currentNick.equals(oldNick)) try {
                    // Tempo
                    Thread.sleep(3000 + (Random.nextInt(7)*1000));
                    String newNick = nicks[Random.nextInt(nicks.length)];
                    bot.changeNick(newNick);
                    Thread.sleep(2000); // Attente de la confirmation du serveur pour le nouveau nick
                    currentNick = bot.getNick();
                } catch (InterruptedException e) {
                    logger.error("Error in waiting task", e);
                }

                lastNickChange = DateTime.now();
            }
        };
    }
}
