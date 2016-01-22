package calendar.business;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Entit� Hibernate Picture mapp�e en base
 * Etend Feature
 */
@Entity
public class Picture extends Feature {

	@Column
	private String source;
	
	public Picture(){
		super.setPrice(5);
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
