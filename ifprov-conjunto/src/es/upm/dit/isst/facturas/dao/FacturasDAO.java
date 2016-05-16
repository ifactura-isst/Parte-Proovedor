package es.upm.dit.isst.facturas.dao;

import java.util.List;
import com.google.appengine.api.users.User;

import es.upm.dit.isst.facturas.model.Factura;

public interface FacturasDAO {

	public Factura add (String empresa, String startDate, String endDate, Double cuotas, 
			Double consumos, Double sinImpuestos, Double importeTotal, Double datosContratados, 
			Double minutosContratados, String municipio, String provincia, User user);
	
	public List<Factura> getFacturas(User user);
	public List<Factura> getAllFacturas();
	
	public void delete (Long id);

}
