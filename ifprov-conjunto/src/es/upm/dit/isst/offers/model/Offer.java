package es.upm.dit.isst.offers.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.users.User;

@Entity
public class Offer implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private User user;
	private int price;
	private String service;
	
	public Offer(String title, String description, User user, int price, String service) {
		this.title = title;
		this.description = description;
		this.user = user;
		this.price = price;
		this.service = service;
	}

	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}


} 