package es.upm.dit.isst.subastas.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.users.User;

import es.upm.dit.isst.subastas.model.Subasta;

public interface SubastaDAO {

	public void remove(long id);

	List<Subasta> listSubastasByUser(User user);
	
	List<Subasta> readSubastas();

	public void add(boolean state, int userMax, int userApuntados, String title, String description, User user, ArrayList<String> customers);

	public void update(long id, boolean state, int userMax, int userApuntados, String title, String description, User user, ArrayList<String> customers);

	Subasta getSubasta(long subastaId);
	
}