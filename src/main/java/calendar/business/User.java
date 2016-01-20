package calendar.business;

import java.util.ArrayList;

public class User {
	
	private Integer id;
	private String name;
	private String email;
	private String password;
	private ArrayList<Purchase> purchases;
	
	public User(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Purchase> getPurchases() {
		return purchases;
	}

	public void addPurchase(Purchase purchase) {
		this.purchases.add(purchase);
	}

}
