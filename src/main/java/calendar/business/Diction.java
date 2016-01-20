package calendar.business;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Diction extends Feature {

	@Column
	private String content;

	public Diction(){
		super.setPrice(2);
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
