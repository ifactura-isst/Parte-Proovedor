package es.upm.dit.isst.facturas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.google.appengine.api.users.User;
import es.upm.dit.isst.facturas.model.Factura;


public class FacturasDAOImpl implements FacturasDAO {
	
	private static FacturasDAOImpl instance;
	
	private FacturasDAOImpl() {
	}
	
	public static FacturasDAOImpl getInstance() {
		if (instance == null) {
			instance = new FacturasDAOImpl();
		}
		return instance;
	}

	@Override
	public Factura add(String nombre, String apellidos, String tipo, String empresa,
			Long importe, String municipio, String provincia, User user) {
		EntityManager em = EMFService.get().createEntityManager();
		Factura factura = new Factura(nombre, apellidos, tipo, empresa, importe, municipio, provincia, user);
		em.persist(factura);
		em.close();
		return factura;
	}

	@Override
	public List<Factura> getFacturas() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Factura t");
		List<Factura> facturas = q.getResultList();
		em.close();
		return facturas;
	}
	

}
