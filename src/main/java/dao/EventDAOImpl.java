package dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import domain.Event;

@Repository
public class EventDAOImpl implements EventDAO {

    @PersistenceContext
    private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Event> findAll() {
		return this.em.createQuery("SELECT event FROM Event event ORDER BY event.id").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Event> findEvents(String name) throws DataAccessException {
		Query query = this.em.createQuery("SELECT DISTINCT event FROM Event event WHERE event.name LIKE :name");
		query.setParameter("name", "%" + name + "%");
	    return query.getResultList();
	}
	
	@Override
	public void saveEvent(Event event) throws DataAccessException {
		if (event.getId() == null) {
			this.em.persist(event);
		} else {
		  this.em.merge(event);
		}
	}

	@Override
	public Event loadEvent(int id) throws DataAccessException {
		return this.em.find(Event.class, id);
	}
}
