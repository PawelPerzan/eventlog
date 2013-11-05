package dao;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import domain.Event;

public interface EventDAO {
	public Collection<Event> findAll();
	
	Collection<Event> findEvents(String lastName) throws DataAccessException;

	Event loadEvent(int id) throws DataAccessException;

	void saveEvent(Event event) throws DataAccessException;
}
