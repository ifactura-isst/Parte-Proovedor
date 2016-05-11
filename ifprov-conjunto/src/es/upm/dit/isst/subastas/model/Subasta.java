package es.upm.dit.isst.subastas.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
		this.title = title;
		this.description = description;
		this.user = user;
		this.state = state;
		this.userMax = userMax;
		this.customers = customers;
		this.userApuntados = userApuntados;
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

	public void setState(boolean state) {
		this.state = state;
	}
	
	public boolean getState() {
		return state;
	}
	
	public ArrayList<String> getUsuariosApuntados() {
		return customers;
	}

	public void setUsuariosApuntados(ArrayList<String> customers) {
		this.customers = customers;
	}
	
	public int getNumeroUsuarios() {
		return userApuntados;
	}
	public void setNumeroUsuarios(int userApuntados){
		this.userApuntados = userApuntados;
	}
	public int getUsuariosMax() {
		return userMax;
	}
	public void setUsuariosMax(int userMax){
		this.userMax = userMax;
	}
	

} 