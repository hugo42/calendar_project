package calendar.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Entite Hibernate Purchase mappée en base
 */
@Entity
public class Purchase {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "guest")
    private Guest guest;
	
	@OneToOne
	@JoinColumn(name = "day")
	private Day day;
	
	@OneToOne
	@JoinColumn(name = "feature")
	private Feature feature;
	
	public Purchase(){
		
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Guest getGuest() {
		return this.guest;
	}

	public void setGuest(Guest guest) {
		guest.addPurchase(this);
		this.guest = guest;
	}

	public Day getDay() {
		return this.day;
	}

	public void setDay(Day day) {
		day.setPurchase(this);
		this.day = day;
	}
	
	public Feature getFeature() {
		return this.feature;
	}

	public void setFeature(Feature feature) {
		feature.setPurchase(this);
		this.feature = feature;
	}
	
}
