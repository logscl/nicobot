package com.st.nicobot.behavior;

import com.st.nicobot.NicoBot;
import com.st.nicobot.utils.Chance;
import com.st.nicobot.utils.Option;

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
