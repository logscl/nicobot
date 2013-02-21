package com.st.nicobot.event.handler;

import com.st.nicobot.event.MessageEvent;
import com.st.nicobot.utils.Chance;
import com.st.nicobot.utils.Option;
import com.st.nicobot.utils.Random;

/**
 * @author jlamby
 *
 */
public abstract class ConditionalMessageEvent implements MessageEvent, Chance {

	/**
	 * L'implementation de la condition pour activer cet event. 
	 * 
	 * @param option
	 * 		Un objet contenant les differentes options sur lesquelles on peut faire le test.		
	 * @return 
	 * 		Doit retourner <code>true</code> si il faut activer cet event. <code>False</code> sinon.
	 */
	public abstract boolean testImpl(Option option);
	
	/**
	 * Le test.
	 * @param opt
	 * 		Les options
	 * @return
	 */
	final boolean testCondition(Option opt) {
		if (testImpl(opt)) {
			int chance = Random.MAX_CHANCE - Random.nextInt();
			
			if (chance < getChance()) {
				return true;
			}
		}
		
		return false;
	}

}
