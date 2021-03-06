package com.st.nicobot.internal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.picocontainer.MutablePicoContainer;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.behavior.NiConduct;
import com.st.nicobot.bot.utils.Option;
import com.st.nicobot.bot.utils.Random;
import com.st.nicobot.context.ApplicationContextAware;
import com.st.nicobot.context.ClassLoader;
import com.st.nicobot.services.BehaviorsService;

/**
 * @author Julien
 *
 */
public class BehaviorsServiceImpl implements BehaviorsService, ApplicationContextAware {

    private MutablePicoContainer appCtx;

    private List<NiConduct> behaviors;

    public BehaviorsServiceImpl() {	}

    @Override
    public void randomBehave(NicoBot nicobot, Option opts) {
        List<NiConduct> chosenBehaviors = new ArrayList<NiConduct>();

        int chance = Random.MAX_CHANCE - Random.nextInt();

        // On construit une liste des differents NiConduct qui sont accessibles pour cette proba
        for (NiConduct behavior : getBehaviors()){
            if (chance < behavior.getChance() ) {
                chosenBehaviors.add(behavior);
            }
        }

        // Et si on a au moins 1 NiConduct, on en determine 1 seul parmis la liste et on l'exec
        if (! chosenBehaviors.isEmpty()){
            int idx = Random.nextInt(chosenBehaviors.size());

            NiConduct chosenOne = chosenBehaviors.get(idx);
            chosenOne.behave(nicobot, opts);
        }
    }

    @Override
    public void setApplicationContext(MutablePicoContainer appCtx) {
        this.appCtx = appCtx;
    }

    public List<NiConduct> getBehaviors() {
        if (behaviors == null) {
            behaviors = new ArrayList<NiConduct>();

            Set<Class<? extends NiConduct>> niConductClasses = ClassLoader.getSubTypesOf(NiConduct.class);

            for(Class<? extends NiConduct> c : niConductClasses) {
                NiConduct niConduct = appCtx.getComponent(c);
                behaviors.add(niConduct);
            }
        }

        return behaviors;
    }

}
