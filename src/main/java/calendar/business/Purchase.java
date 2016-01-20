package calendar.business;

import java.util.Date;

public class Purchase {
	
	private Integer id;
	private User user;
	private Day day;
	
	public Purchase(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}
	
}
