package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "auditlog")
public class AuditLog extends BaseEntity {
	
    @Column(name = "objectid")
	private String objectId;
	
    @Column(name = "userid")
	private String userId;
	
    @Column(name = "successind")
	private String successInd;			// success Indicator
	
    @Column(name = "auditdate")
	private String auditDate;
	
    @Column(name = "auditmessage")
	private String auditMessage;		// display, edit, add
    
	/**
	 * @return the objectId
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the successInd
	 */
	public String isSuccessInd() {
		return successInd;
	}

	/**
	 * @param successInd the successInd to set
	 */
	public void setSuccessInd(String successInd) {
		this.successInd = successInd;
	}

	/**
	 * @return the auditDate
	 */
	public String getAuditDate() {
		return auditDate;
	}

	/**
	 * @param auditDate the auditDate to set
	 */
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	/**
	 * @return the auditMessage
	 */
	public String getAuditMessage() {
		return auditMessage;
	}

	/**
	 * @param auditMessage the auditMessage to set
	 */
	public void setAuditMessage(String auditMessage) {
		this.auditMessage = auditMessage;
	}
}
