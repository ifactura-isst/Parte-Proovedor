package es.upm.dit.isst.subastas.model;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

import com.google.appengine.api.users.User;

@Entity
public class Subasta implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean state;
	private int userMax;
	private int userApuntados;
	private String title;
	private String description;
	private User user;
	private ArrayList<String> customers;
	
	public Subasta(boolean state, int userMax, int userApuntados, String title, String description, User user, ArrayList<String> customers) {
		this.state = state;
		this.userMax = userMax;
		this.userApuntados = userApuntados;
		this.title = title;
		this.description = description;
		this.user = user;
		this.customers = customers;
		
	}

	public Long getId() {
		return id;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
	public boolean getState() {
		return state;
	}
	
	public int getuserMax() {
		return userMax;
	}
	public void setuserMax(int userMax){
		this.userMax = userMax;
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
	
	public ArrayList<String> getUsuariosApuntados() {
		return customers;
	}

	public void setCustomers(ArrayList<String> customers) {
		this.customers = customers;
	}
	
	public int getuserApuntados() {
		return userApuntados;
	}
	public void setuserApuntados(int userApuntados){
		this.userApuntados = userApuntados;
	}
	
	

} 