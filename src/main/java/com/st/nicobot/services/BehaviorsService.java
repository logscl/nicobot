package com.st.nicobot.services;

import com.st.nicobot.NicoBot;
import com.st.nicobot.utils.Option;

/**
 * @author Julien
 *
 */
public interface BehaviorsService {

	/**
	 * Genere un nombre aleatoire qui va etre la probablité de déclencher un {@code NiConduct}.
	 * @param nicobot
	 * 		Le nicobot
	 * @param opts
	 * 		Les options
	 */
	public void randomBehave(NicoBot nicobot, Option opts);

}
