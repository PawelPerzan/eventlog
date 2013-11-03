package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event extends BaseEntity {
	
    @Column(name = "name")
	private String name;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "description")
    private String description;
	
    @Column(name = "sns")
	private String snsArn;

    /**
     * 
     * @return the name
     */
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return
	 */
	public String getSnsArn() {
		return snsArn;
	}

	/**
	 * @param snsArn
	 */
	public void setSnsArn(String snsArn) {
		this.snsArn = snsArn;
	}
}
