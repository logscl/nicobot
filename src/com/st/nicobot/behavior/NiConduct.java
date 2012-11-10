/**
 * 
 */
package com.st.nicobot.behavior;

import com.st.nicobot.NicoBot;
import com.st.nicobot.utils.Option;

/**
 * @author Julien
 *
 */
public interface NiConduct {

	
	/*
	 * En utilisant les ‰ on beneficie d'une granularité d'activation plus fine tout en evitant de travailler avec de vils double/float !
	 * 100% 	=> 1		1000‰ 	=> 100%
	 * 10%		=> 0.1		100‰ 	=> 10%
	 * 0.1%		=> 0.001	1‰ 		=> 0.1%
	 */
	/**
	 * <p>Retourne le taux d'activation pour ce comportement (en ‰).</p>
	 * @return
	 */
	public int getChance();
	
	/**
	 * Le comportement que nicobot doit adopter pour ce {@code NiConduct}.
	 * @param nicobot
	 * 		Le nicobot
	 * @param opts
	 * 		Les options
	 */
	public void behave(NicoBot nicobot, Option opts);
}
