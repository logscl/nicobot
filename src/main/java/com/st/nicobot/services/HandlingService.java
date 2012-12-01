package com.st.nicobot.services;

import java.util.List;

import com.st.nicobot.event.AbstractEvent;

public interface HandlingService {

	<T extends AbstractEvent> List<T> getEvents(Class<T> type);
}
