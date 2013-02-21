package com.st.nicobot.cmd;

import java.util.Arrays;
import java.util.List;

import org.jibble.pircbot.User;
import org.picocontainer.annotations.Inject;

import com.st.nicobot.NicoBot;
import com.st.nicobot.services.Messages;
import com.st.nicobot.utils.Option;
import com.st.nicobot.utils.Random;

public class RandomKick extends NiCommand {

	private static final String COMMAND = "randomkick";
	private static final String FORMAT = "randomkick <#channel>";
	private static final String DESC = "Kick quelqu'un du chan spécifié aléatoirement (Sauf Q et lui-même !)";
	
	@Inject
	private Messages messages;
	
	@Override
	public String getCommandName() {
		return COMMAND;
	}

	@Override
	public String getDescription() {
		return DESC;
	}

	@Override
	public String getFormat() {
		return FORMAT;
	}

	@Override
	public boolean isAdminRequired() {
		return true;
	}

	@Override
	protected void doCommand(NicoBot nicobot, String command, String[] args, Option opts) {
		if(args.length < 1 || !args[0].startsWith("#")) {
			nicobot.sendNotice(opts.sender, messages.getOtherMessage("kickError"));
			return;
		}
		
		String victim = selectRandomUser(nicobot, args[0]).getNick();

		nicobot.sendMessage(args[0], String.format(messages.getOtherMessage("kickInit"), opts.sender));
		nicobot.kick(args[0], victim, messages.getOtherMessage("kickReason"));
		
		if(victim.equals(opts.sender)) {
			nicobot.sendMessage(args[0], messages.getOtherMessage("kickLose"));
		} else {
			nicobot.sendMessage(args[0], messages.getOtherMessage("kickWin"));
		}
		
	}

	/**
	 * Retourne un utilisateur au hasard parmi la liste.
	 * Filtre certains utilisateurs, comme Q ou Nicobot.
	 * @param bot le nicobot
	 * @param channel le chan à vider
	 * @return un utilisateur parmi la liste.
	 */
	private User selectRandomUser(NicoBot bot, String channel) {
		List<String> protectedUsers = Arrays.asList("Q", bot.getName());
		User[] usrs = bot.getUsers(channel);
		int rdmUser = Random.nextInt(usrs.length);
		
		while(protectedUsers.contains(usrs[rdmUser].getNick())) {
			rdmUser = Random.nextInt(usrs.length);
		}
		
		return usrs[rdmUser];
	}
}
