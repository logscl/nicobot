package com.st.nicobot.internal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.st.nicobot.NicoBot;
import com.st.nicobot.behavior.NiConduct;
import com.st.nicobot.context.ApplicationContext;
import com.st.nicobot.context.ApplicationContextAware;
import com.st.nicobot.services.BehaviorsService;
import com.st.nicobot.utils.ClassLoader;
import com.st.nicobot.utils.Option;

/**
 * @author Julien
 *
 */
public class BehaviorsServiceImpl implements BehaviorsService, ApplicationContextAware {

	private static final int MAX_CHANCE = 1001;
	
	private ApplicationContext appCtx;
	
	private List<NiConduct> behaviors;
	
	private Random random;
	
	public BehaviorsServiceImpl() {	}
	
	public void start() {
		random = com.st.nicobot.utils.Random.getInstance();
	}
	
	@Override
	public void randomBehave(NicoBot nicobot, Option opts) {
		List<NiConduct> chosenBehaviors = new ArrayList<NiConduct>();
		
		int chance = MAX_CHANCE - random.nextInt(MAX_CHANCE);

		// On construit une liste des differents NiConduct qui sont accessibles pour cette proba
		for (NiConduct behavior : getBehaviors()){
			if (chance < behavior.getChance() ) {
				chosenBehaviors.add(behavior);
			}
		}
		
		// Et si on a au moins 1 NiConduct, on en determine 1 seul parmis la liste et on l'exec
		if (! chosenBehaviors.isEmpty()){
			int idx = random.nextInt(chosenBehaviors.size());
			
			NiConduct chosenOne = chosenBehaviors.get(idx);
			chosenOne.behave(nicobot, opts);
		}
	}
	
	@Override
	public void setApplicationContext(ApplicationContext appCtx) {
		this.appCtx = appCtx;
	}
	
	public List<NiConduct> getBehaviors() {
		if (behaviors == null) {
			behaviors = new ArrayList<NiConduct>();
			
			Set<Class<? extends NiConduct>> niConductClasses = ClassLoader.getSubTypesOf(NiConduct.class);
			
			for(Class<? extends NiConduct> c : niConductClasses) {
				NiConduct niConduct = appCtx.getPicoContainer().getComponent(c);
				behaviors.add(niConduct);
			}
		}
		
		return behaviors;
	}

}
