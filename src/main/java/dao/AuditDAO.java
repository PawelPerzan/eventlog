package dao;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import domain.AuditLog;

public interface AuditDAO {

	public Collection<AuditLog> findAll();
	
	void saveAuditLog(AuditLog auditLog) throws DataAccessException;
}
