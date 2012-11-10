/**
 * 
 */
package com.st.nicobot.internal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.st.nicobot.NicoBot;
import com.st.nicobot.behavior.NiConduct;
import com.st.nicobot.services.BehaviorsService;
import com.st.nicobot.utils.Option;

/**
 * @author Julien
 *
 */
public class BehaviorsServiceImpl implements BehaviorsService {

	private static final int MAX_CHANCE = 1001;
	
	private List<NiConduct> behaviors;
	
	private Random random;
	
	private static BehaviorsService instance;
	
	private static String behaviorsPackage = "com.st.nicobot.behavior";
	
	private BehaviorsServiceImpl() { }
	
	public static BehaviorsService getInstance() {
		if (instance == null) {
			instance = new BehaviorsServiceImpl();
			((BehaviorsServiceImpl)instance).init();
		}
		
		return instance;
	}
	
	public void init() {
		random = com.st.nicobot.utils.Random.getInstance();
		behaviors = new ArrayList<NiConduct>();
		
		Reflections reflex = new Reflections(new ConfigurationBuilder()
	        .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(behaviorsPackage)))
	        .setUrls(ClasspathHelper.forPackage(behaviorsPackage))
	        .setScanners(new SubTypesScanner(), new ResourcesScanner()));
		
		Set<Class<? extends NiConduct>> classes = reflex.getSubTypesOf(NiConduct.class);
		
		for(Class<? extends NiConduct> clazz : classes) {
			try {
				NiConduct c = (NiConduct)clazz.newInstance();
				behaviors.add(c);
			} catch (Exception e) {
				System.out.println("Impossibler d'instancier la classe "+clazz+", exception : "+e.getMessage());
			}
		}
	}
	
	@Override
	public List<NiConduct> getBehaviors() {
		return behaviors;
	}
	
	@Override
	public void randomBehave(NicoBot nicobot, Option opts) {
		List<NiConduct> chosenBehaviors = new ArrayList<NiConduct>();
		
		int chance = MAX_CHANCE - random.nextInt(MAX_CHANCE);

		// On construit une liste des differents NiConduct qui sont accessibles pour cette proba
		for (NiConduct behavior : behaviors){
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
}
