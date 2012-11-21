package com.st.nicobot.services;

import java.util.List;

import com.st.nicobot.event.AbstractEvent;

public interface HandlingService {

	List<AbstractEvent> getEvents(Class<? extends AbstractEvent> type);
}
