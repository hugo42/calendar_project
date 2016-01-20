package calendar.business;

import java.util.Date;

public class Day {

	private Integer id;
	private Date dayDate;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDayDate() {
		return dayDate;
	}

	public void setDayDate(Date dayDate) {
		this.dayDate = dayDate;
	}

	public Day(){
		
	}
}
