package com.st.nicobot;

import org.picocontainer.ComponentAdapter;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;
import org.picocontainer.behaviors.Caching;
import org.picocontainer.gems.monitors.Slf4jComponentMonitor;
import org.picocontainer.injectors.AnnotatedFieldInjection;
import org.picocontainer.lifecycle.ReflectionLifecycleStrategy;
import org.picocontainer.monitors.NullComponentMonitor;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.utils.NicobotProperty;
import com.st.nicobot.context.ComponentUtils;
import com.st.nicobot.context.annotations.Component;
import com.st.nicobot.services.PropertiesService;

public class BotMain {

	private static MutablePicoContainer container;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		initContainer();
		
		PropertiesService props = container.getComponent(PropertiesService.class);
		
		NicoBot bot = container.getComponent(NicoBot.class);

		bot.connect(props.get(NicobotProperty.BOT_SERVER));
		bot.joinChannel(props.get(NicobotProperty.BOT_CHAN));
	}

	@SuppressWarnings("serial")
	private static void initContainer() {
		container = new DefaultPicoContainer(
				new Caching().wrap(new AnnotatedFieldInjection()), 
				new ReflectionLifecycleStrategy(new NullComponentMonitor()) {
					@Override
					public boolean isLazy(ComponentAdapter<?> adapter) {
						return true;
					}
				}, 
				(PicoContainer)null, 
				new Slf4jComponentMonitor()
		);
		
		
		ComponentUtils.loadComponents(container, com.st.nicobot.context.ClassLoader.getAllInstantiableClasses());
		ComponentUtils.loadComponents(container, com.st.nicobot.context.ClassLoader.getClassAnnotedWith(Component.class));
		
		ComponentUtils.publishApplicationContext(container);
	
		container.start();
	}
}
