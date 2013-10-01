package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event extends BaseEntity {
	
    @Column(name = "name")
	private String name;
	
    @Column(name = "sns")
	private String snsArn;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSnsArn() {
		return snsArn;
	}

	public void setSnsArn(String snsArn) {
		this.snsArn = snsArn;
	}
}
