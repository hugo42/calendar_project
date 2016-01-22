package calendar.business;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Entité Hibernate Day mappée en base
 */
@Entity
public class Day {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	
	@Column
	private Date dayDate;
	
	@OneToOne
	@JoinColumn(name = "purchase")
	private Purchase purchase;
	
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

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	
	public String getTextDate(){
		
		return new SimpleDateFormat("EEEE d MMMM yyyy", Locale.FRENCH).format(this.dayDate);
	}
}
