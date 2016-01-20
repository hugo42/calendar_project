package calendar.business;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Picture extends Feature {

	@Column
	private String source;
	
	public Picture(){
		super.setPrice(2);
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
