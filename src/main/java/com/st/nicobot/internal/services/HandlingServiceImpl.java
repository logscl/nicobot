package com.st.nicobot.internal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.st.nicobot.context.ApplicationContext;
import com.st.nicobot.context.ApplicationContextAware;
import com.st.nicobot.event.AbstractEvent;
import com.st.nicobot.services.HandlingService;
import com.st.nicobot.utils.ClassLoader;

public class HandlingServiceImpl implements HandlingService, ApplicationContextAware {
	
	private ApplicationContext appCtx;
	
	private List<AbstractEvent> events;
	
	public HandlingServiceImpl() {	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractEvent> List<T> getEvents(Class<T> eventType) {
		List<T> tempEvents = new ArrayList<T>();
		
		for(AbstractEvent event : getEvents()) {
			if(eventType.isAssignableFrom(event.getClass())) {
				tempEvents.add((T) event);
			}
		}
		
		return tempEvents;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext appCtx) {
		this.appCtx = appCtx;
	}

	private List<AbstractEvent> getEvents() {
		if (events == null) {
			events = new ArrayList<AbstractEvent>();
			
			Set<Class<? extends AbstractEvent>> abstractEvents = ClassLoader.getSubTypesOf(AbstractEvent.class);
			
			for (Class<? extends AbstractEvent> clazz : abstractEvents) {
				if (!clazz.isInterface()) {
					AbstractEvent evt = appCtx.getPicoContainer().getComponent(clazz);
					events.add(evt);
				}
			}
		}
		
		return events;
	}
	
}
