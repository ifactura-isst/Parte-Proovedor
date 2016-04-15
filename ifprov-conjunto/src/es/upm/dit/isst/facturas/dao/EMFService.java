package es.upm.dit.isst.facturas.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFService {
	
	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("transactions-optional");

	public EMFService() {
	}
	
	public static EntityManagerFactory get() {
		return emfInstance;
	}

}