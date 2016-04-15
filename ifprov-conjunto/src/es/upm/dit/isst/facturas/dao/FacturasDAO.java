package es.upm.dit.isst.facturas.dao;

import java.util.List;
import com.google.appengine.api.users.User;
import es.upm.dit.isst.facturas.model.Factura;

public interface FacturasDAO {
	
	public Factura add (String nombre, String apellidos, String tipo, String empresa,
			Long importe, String municipio, String provincia, User user);
	
	public List<Factura> getFacturas ();
	
	//public void delete (Long id);

}
