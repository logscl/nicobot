/**
 * 
 */
package com.st.nicobot.behavior;

import com.st.nicobot.NicoBot;
import com.st.nicobot.internal.services.MessagesImpl;
import com.st.nicobot.utils.Option;
import com.st.nicobot.utils.Random;

/**
 * @author Julien
 *
 */
public class RandomTalk implements NiConduct {

	@Override
	public int getChance() {
		return 50;
	}

	@Override
	public void behave(NicoBot nicobot, Option opts) {
		nicobot.sendMessage(opts.channel, getRandomTalk());
	}
	
	protected String getRandomTalk() {
		int rndIdx = Random.getInstance().nextInt(RandomTalkName.values().length);
		
		String msg = "";
		
		switch(rndIdx) {
			case 0 :  	
				msg = MessagesImpl.getInstance().getOtherMessage(RandomTalkName.RIVERSIDE.name().toLowerCase());
				break;
			case 1 : 
				msg = MessagesImpl.getInstance().getOtherMessage(RandomTalkName.VELO.name().toLowerCase());
				break;
			case 2 :
				msg = MessagesImpl.getInstance().getOtherMessage(RandomTalkName.TOPSIDE.name().toLowerCase());
				break;
			case 3 :
				msg = MessagesImpl.getInstance().getOtherMessage(RandomTalkName.BIATCH.name().toLowerCase());
				break;
			case 4 :
				msg = MessagesImpl.getInstance().getOtherMessage(RandomTalkName.ENSOMME.name().toLowerCase());
				break;
			case 5 :
				msg = MessagesImpl.getInstance().getOtherMessage(RandomTalkName.NICONTROLEUR.name().toLowerCase());
				break;
		}
		
		return msg;
	}
	
	enum RandomTalkName {
		RIVERSIDE,
		VELO,
		TOPSIDE,
		BIATCH,
		ENSOMME,
		NICONTROLEUR
	}
	
}