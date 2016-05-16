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
	
	//TODO Generar los Id's de manera incremental, no aleatorios
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	private String empresa;
	private String startDate;
	private String endDate;
	private Double cuotas;
	private Double consumos;
	private Double sinImpuestos;
	private Double importeTotal;
	private Double datosContratados;
	private Double minutosContratados;
	private String municipio;
	private String provincia;
	private User user;
	
	/**
	 * Constructor
	 */
	public Factura(String empresa, String startDate, String endDate, Double cuotas,
			Double consumos, Double sinImpuestos, Double importeTotal, 
			Double datosContratados, Double minutosContratados, 
			String municipio, String provincia, User user) {
		this.empresa = empresa;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cuotas = cuotas;
		this.consumos = consumos;
		this.sinImpuestos = sinImpuestos;
		this.importeTotal = importeTotal;
		this.datosContratados = datosContratados;
		this.minutosContratados = minutosContratados;
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

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getStartDate() {
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
	}

	public Double getCuotas() {
		return cuotas;
	}

	public void setCuotas(Double cuotas) {
		this.cuotas = cuotas;
	}

	public Double getConsumos() {
		return consumos;
	}

	public void setConsumos(Double consumos) {
		this.consumos = consumos;
	}

	public Double getSinImpuestos() {
		return sinImpuestos;
	}

	public void setSinImpuestos(Double sinImpuestos) {
		this.sinImpuestos = sinImpuestos;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Double getDatosContratados() {
		return datosContratados;
	}

	public void setDatosContratados(Double datosContratados) {
		this.datosContratados = datosContratados;
	}

	public Double getMinutosContratados() {
		return minutosContratados;
	}

	public void setMinutosContratados(Double minutosContratados) {
		this.minutosContratados = minutosContratados;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", empresa=" + empresa + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", cuotas=" + cuotas + ", consumos=" + consumos + ", sinImpuestos=" + sinImpuestos + ", importeTotal=" + importeTotal + ", datosContratados=" + datosContratados + ", minutosContratados="
				+ minutosContratados + ", municipio=" + municipio + ", provincia=" + provincia + ", user=" + user + "]";
	}

	
	
}
