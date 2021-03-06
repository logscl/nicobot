package com.st.nicobot.bot.cmd;

import org.picocontainer.annotations.Inject;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.utils.Option;
import com.st.nicobot.services.Messages;
import com.st.nicobot.services.UsersService;

@Deprecated
public class ShowAdmins extends NiCommand {
	
	private static final String COMMAND = "showadmins";
	private static final String FORMAT = "showadmins";
	private static final String DESC = "Affiche les personnes pouvant administrer nicobot";
	
	@Inject
	private UsersService users;
	
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
		nicobot.sendNotice(opts.sender, "Voici les admins enregistrés : "+users.getUsers());
	}

}
