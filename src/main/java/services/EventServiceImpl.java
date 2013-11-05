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

	/**
	 * @param eventDao
	 */
    @Autowired
    public EventServiceImpl(EventDAO eventDao) {
        this.eventDao = eventDao;
    }

    /*
     * (non-Javadoc)
     * @see services.EventService#findAllEvents()
     */
	@Override
	public Collection<Event> findAllEvents() throws DataAccessException {
		return eventDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * @see services.EventService#findEvents(java.lang.String)
	 */
	@Override
	public Collection<Event> findEvents(String name) throws DataAccessException {
		return eventDao.findEvents(name);
	}

	/*
	 * (non-Javadoc)
	 * @see services.EventService#saveEvent(domain.Event)
	 */
	@Override
	@Transactional
	public void saveEvent(Event event) throws DataAccessException {
		eventDao.saveEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * @see services.EventService#loadEvent(int)
	 */
	@Override
	@Transactional
	public Event loadEvent(int id) {
		return eventDao.loadEvent(id);
	}
}
