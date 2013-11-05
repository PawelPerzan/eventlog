package services;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import domain.Event;

public interface EventService {
	Collection<Event> findAllEvents() throws DataAccessException;
	
	Collection<Event> findEvents(String lastName) throws DataAccessException;
	
	void saveEvent(Event event) throws DataAccessException;
	
	Event loadEvent(int id);
}
