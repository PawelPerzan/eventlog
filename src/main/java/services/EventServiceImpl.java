package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.EventDAO;
import domain.Event;

@Service
public class EventServiceImpl implements EventService {

	private EventDAO eventDao;

    @Autowired
    public EventServiceImpl(EventDAO eventDao) {
        this.eventDao = eventDao;
    }

	@Override
	public Collection<Event> findAllEvents() throws DataAccessException {
		return eventDao.findAll();
	}

	@Override
	public Collection<Event> findEvents(String name) throws DataAccessException {
		return eventDao.findEvents(name);
	}

	@Override
	@Transactional
	public void saveEvent(Event event) throws DataAccessException {
		eventDao.saveEvent(event);
	}
}
