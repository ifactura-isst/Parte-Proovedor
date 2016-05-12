package es.upm.dit.isst.subastas.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.appengine.api.users.User;

import es.upm.dit.isst.offers.dao.EMFService;
import es.upm.dit.isst.offers.model.Offer;
import es.upm.dit.isst.subastas.model.Subasta;


public class SubastaDAOImpl implements SubastaDAO {

	private static SubastaDAOImpl instance;

	private SubastaDAOImpl() {
	}

	public static SubastaDAOImpl getInstance() {
		if (instance == null)
			instance = new SubastaDAOImpl();
		return instance;
	}

	@Override
	public List<Subasta> listSubastasByUser(User user) {
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select t from Subasta t where t.user =:user");
		q.setParameter("user", user);
		List<Subasta> subastas = q.getResultList();
		return subastas;
	}

	@Override
	public void add(boolean state, int userMax, int userApuntados, String title, String description, User user, ArrayList<String> customers) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Subasta subasta = new Subasta(state, userMax, userApuntados, title, description, user, customers);
			em.persist(subasta);
			em.close();
		}

	}

	@Override
	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Subasta subasta = em.find(Subasta.class, id);
			em.remove(subasta);
		} finally {
			em.close();
		}
	}

	

	@Override
	public Subasta getSubasta(long subastaId) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Subasta subasta = em.find(Subasta.class, subastaId);
			return subasta;
		} finally {
			em.close();
		}
	}

	@Override
	public void update(long subastaId, boolean state, int userMax, int userApuntados, String title, String description,
			User user, ArrayList<String> customers) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Subasta subasta = em.find(Subasta.class, subastaId);
			subasta.setTitle(title);
			subasta.setDescription(description);
			subasta.setCustomers(customers);
			subasta.setState(state);
			subasta.setuserMax(userMax);
			subasta.setuserApuntados(userApuntados);
			em.merge(subasta);
		} finally {
			em.close();
		}		
	}


	
	

}
