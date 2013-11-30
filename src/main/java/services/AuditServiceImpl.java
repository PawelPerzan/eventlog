package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AuditDAO;
import domain.AuditLog;

@Service
public class AuditServiceImpl implements AuditService {
	private AuditDAO auditDao;

	/**
	 * @param eventDao
	 */
    @Autowired
    public AuditServiceImpl(AuditDAO auditDao) {
        this.auditDao = auditDao;
    }

	@Override
	public Collection<AuditLog> findAll() throws DataAccessException {
		return auditDao.findAll();
	}

	@Override
	@Transactional
	public void saveAuditLog(AuditLog audit) throws DataAccessException {
		auditDao.saveAuditLog(audit);	
	}
}
