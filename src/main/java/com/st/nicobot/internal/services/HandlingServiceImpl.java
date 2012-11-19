package com.st.nicobot.internal.services;

import java.util.ArrayList;
import java.util.List;

import com.st.nicobot.event.AbstractEvent;
import com.st.nicobot.services.HandlingService;
import com.st.nicobot.utils.ClassLoader;

public class HandlingServiceImpl implements HandlingService {
	
	private List<AbstractEvent> events;
	
	private static HandlingService instance;
	
	private HandlingServiceImpl() {
		init();
	}

	public static HandlingService getInstance() {
		if(instance == null) {
			instance = new HandlingServiceImpl();
		}
		return instance;
	}
	
	private void init() {
		events = ClassLoader.getInstance().getInstancesOfClass(AbstractEvent.class);
	}
	
	@Override
	public List<AbstractEvent> getEvents(Class<? extends AbstractEvent> eventType) {
		List<AbstractEvent> tempEvents = new ArrayList<AbstractEvent>();
		for(AbstractEvent event : events) {
			if(eventType.isAssignableFrom(event.getClass())) {
				tempEvents.add(event);
			}
		}
		return tempEvents;
	}
}
