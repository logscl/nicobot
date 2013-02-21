package com.st.nicobot.bot.behavior;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.utils.Chance;
import com.st.nicobot.bot.utils.Option;

/**
 * @author Julien
 *
 */
public interface NiConduct extends Chance {

    /**
     * Le comportement que nicobot doit adopter pour ce {@code NiConduct}.
     * @param nicobot
     * 		Le nicobot
     * @param opts
     * 		Les options
     */
    public void behave(NicoBot nicobot, Option opts);
}
