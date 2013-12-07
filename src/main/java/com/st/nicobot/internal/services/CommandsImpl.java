package com.st.nicobot.internal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.picocontainer.MutablePicoContainer;

import com.st.nicobot.bot.cmd.NiCommand;
import com.st.nicobot.context.ApplicationContextAware;
import com.st.nicobot.context.ClassLoader;
import com.st.nicobot.services.Commands;

/**
 * @author Julien
 *
 */
public class CommandsImpl implements Commands, ApplicationContextAware {

	private MutablePicoContainer appCtx;
	
	private NiCommand firstLink;

	public CommandsImpl() {	}
	
	@Override
	public NiCommand getFirstLink() {
		if (firstLink == null) {
			createChain();
		}

		return firstLink;
	}
	
	/**
	 * Crée la chaine de commande et retourne le 1er maillon.
	 * @return
	 */
	private NiCommand createChain() {
		Set<Class<? extends NiCommand>> classes = ClassLoader.getSubTypesOf(NiCommand.class);
		List<NiCommand> cmds = new ArrayList<NiCommand>();
		
		for(Class<? extends NiCommand> clazz : classes) {
			if(!clazz.isAnnotationPresent(Deprecated.class)) {
				cmds.add(appCtx.getComponent(clazz));
			}
		}
		
		for (int i = 1; i < cmds.size(); i++) {
			final NiCommand prev = cmds.get(i-1);
			final NiCommand curr = cmds.get(i);
			
			prev.setNext(curr);
		}
		
		firstLink = cmds.get(0);
		
		return firstLink;
	}
	
	@Override
	public void setApplicationContext(MutablePicoContainer appCtx) {
		this.appCtx = appCtx;
	}
	
	
	
}
