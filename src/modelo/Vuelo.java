package modelo;

import java.util.Date;
import app.AppUtils;

public class Vuelo {
	
	private String codigo, salida, destino;
	private Date fecha;
	private String hora;
	private int plazas, plazasLibres;
	
	public Vuelo(String codigo, String salida, String destino, Date fecha, String hora, int plazas, int plazasLibres) {
		this.codigo = codigo;
		this.salida = salida;
		this.destino = destino;
		this.fecha = fecha;
		this.hora = hora;
		this.plazas = plazas;
		this.plazasLibres = plazasLibres;
	}
	
	public Vuelo(String codigo, String salida, String destino, String fecha, String hora, String plazas, String plazasLibres) {
		this.codigo = codigo;
		this.salida = salida;
		this.destino = destino;
		this.fecha = new Date(Long.parseLong(fecha));
		this.hora = hora;
		this.plazas = Integer.parseInt(plazas);
		this.plazasLibres = Integer.parseInt(plazasLibres);
	}
	
	public boolean contains (String filter) 
	{
		filter = filter.toLowerCase();
		
		if (codigo.toLowerCase().contains(filter))
			return true;
		if (salida.toLowerCase().contains(filter))
			return true;
		if (destino.toLowerCase().contains(filter))
			return true;
		if (hora.toLowerCase().contains(filter))
			return true;
		if (String.valueOf(plazas).contains(filter))
			return true;
		if (String.valueOf(plazasLibres).contains(filter))
			return true;
		if (AppUtils.dateToString(fecha).contains(filter))
			return true;
		// no matches:
		return false;
	}

	public static Vuelo vueloEjemplo() 
	{
		return new Vuelo("IB653", "Madrid", "Paris", new Date(), "06:00", 200, 150);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public int getPlazasLibres() {
		return plazasLibres;
	}

	public void setPlazasLibres(int plazasLibres) {
		this.plazasLibres = plazasLibres;
	}

}
