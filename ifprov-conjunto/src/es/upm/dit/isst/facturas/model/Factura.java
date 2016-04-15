package es.upm.dit.isst.facturas.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.users.User;

@Entity
public class Factura implements Serializable {

	private static final long serialVersionUID = 01L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private String apellidos;
	private String tipo;
	private String empresa;
	//private String startDate;
	//private String endDate;
	private Long importe;
	private String municipio;
	private String provincia;
	private User user;
	
	/**
	 * Constructor
	 */
	public Factura(String nombre, String apellidos, String tipo, String empresa,
			Long importe, String municipio, String provincia, User user) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.tipo = tipo;
		this.empresa = empresa;
		this.importe = importe;
		this.municipio = municipio;
		this.provincia = provincia;
		this.user = user;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/*public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}*/
	public Long getImporte() {
		return importe;
	}
	public void setImporte(Long importe) {
		this.importe = importe;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public User getUser(){
		return user;
	}


	@Override
	public String toString() {
		return "Factura [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", tipo=" + tipo + ", empresa="
				+ empresa + ", importe=" + importe
				+ ", municipio=" + municipio + ", provincia=" + provincia + "]";
	}
	
	
}
