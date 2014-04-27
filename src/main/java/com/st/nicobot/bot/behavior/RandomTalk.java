package com.st.nicobot.bot.behavior;

import org.picocontainer.annotations.Inject;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.utils.Option;
import com.st.nicobot.bot.utils.Random;
import com.st.nicobot.services.Messages;

/**
 * @author Julien
 *
 */
public class RandomTalk implements NiConduct {

	@Inject
	private Messages messages;
	
	public RandomTalk() {	}
	
	@Override
	public int getChance() {
		return 50;
	}

	@Override
	public void behave(NicoBot nicobot, Option opts) {
		nicobot.sendChannelMessage(opts.channel, getRandomTalk());
	}
	
	protected String getRandomTalk() {
		int rndIdx = Random.nextInt(RandomTalkName.values().length);
		
		String msg = "";
		/*
		switch(rndIdx) {
			
			case 0 :  	
				msg = messages.getOtherMessage(RandomTalkName.RIVERSIDE.name().toLowerCase());
				break;
			case 1 : 
				msg = messages.getOtherMessage(RandomTalkName.VELO.name().toLowerCase());
				break;
			case 2 :
				msg = messages.getOtherMessage(RandomTalkName.TOPSIDE.name().toLowerCase());
				break;
			case 3 :
				msg = messages.getOtherMessage(RandomTalkName.BIATCH.name().toLowerCase());
				break;
			case 4 :
				msg = messages.getOtherMessage(RandomTalkName.ENSOMME.name().toLowerCase());
				break;
			case 5 :
				msg = messages.getOtherMessage(RandomTalkName.NICONTROLEUR.name().toLowerCase());
				break;
		}
		*/
		
		//http://stackoverflow.com/questions/609860/convert-from-enum-ordinal-to-enum-type :
		//Note that every call to values() returns a newly cloned array, so you may want to cache the array if it's going to be called in a critical region of code.
		//Si c'est vraiment puant, l'ancienne méthode est gardée en commentaire.
		msg = messages.getOtherMessage(RandomTalkName.values()[rndIdx].name().toLowerCase());
		
		return msg;
	}
	
	enum RandomTalkName {
		RIVERSIDE,
		VELO,
		TOPSIDE,
		BIATCH,
		ENSOMME,
		NICONTROLEUR,
		DURMAISJUSTE,
		CHEPATSE,
		STANDARD,
		WOUHOU,
		TESQUI,
		ANECDOTE
	}
	
}