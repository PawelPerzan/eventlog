package services;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import domain.AuditLog;

public interface AuditService {

	Collection<AuditLog> findAll() throws DataAccessException;
	
	void saveAuditLog(AuditLog audit) throws DataAccessException;
}
