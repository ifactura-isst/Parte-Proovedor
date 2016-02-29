package es.upm.dit.isst.offers.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.users.User;

import es.upm.dit.isst.offers.model.Offer;

public interface OfferDAO {

	public void remove(long id);

	List<Offer> listOffersByUser(User user);

	public void add (String title, String description, User user, int price, String service);

	public void update(long id, String title, String description, User user, int price, String service);

	Offer getOffer(long offerId);
	
}