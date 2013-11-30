package dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import domain.AuditLog;

@Repository
public class AuditDAOImpl implements AuditDAO {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<AuditLog> findAll() {
		return this.em.createQuery("SELECT auditlog FROM AuditLog auditlog ORDER BY auditlog.id").getResultList();
	}

	@Override
	public void saveAuditLog(AuditLog auditLog) throws DataAccessException {
		if (auditLog.getId() == null) {
			this.em.persist(auditLog);
		} else {
		  this.em.merge(auditLog);
		}
	}
}
