package modelo.datosFichero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;
import controlador.ListaVuelos;
import modelo.Vuelo;

public class FicheroParse {

	private String path;

	public FicheroParse(String path) {
		this.path = path;
	}

	public ListaVuelos cargarVuelos() {
		ListaVuelos listaVuelos = new ListaVuelos();

		// Leer fichero
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// Leer vuelo
				Vuelo v = leerVuelo(line);
				if (v != null)
					listaVuelos.add(v);
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("No se pudo leer el fichero: " + path);
		}

		return listaVuelos;
	}

	private Vuelo leerVuelo(String line) {
		// Lee un vuelo de una linea de texto con valores
		// separados por comas
		Vuelo vuelo = null;
		try {
			String[] v = line.split(",");
			vuelo = new Vuelo(v[0], v[1], v[2], v[3], v[4], v[5], v[6]);

		} catch (Exception e) {
			System.err.println("Linea inválida en fichero: " + line);
		}

		return vuelo;
	}
	
	
	public void escribirVuelos(ListaVuelos listaVuelos) {

		try {
			
			File fout = new File(path);
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			Collection<Vuelo> arrayVuelos = listaVuelos.toCollection();

			for (Vuelo vuelo : arrayVuelos) {
				bw.write(textoVuelo(vuelo));
				bw.newLine();
			}

			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			System.err.println("No se pudo escribir el fichero: " + path);
		}
	}
	
	private String textoVuelo(Vuelo v) 
	{
		// Convierte un objeto vuelo a una linea de texto
		// separando los valores con comas
		String[] values = {v.getCodigo(), v.getSalida(), 
				v.getDestino(), v.getFecha().getTime() + "", 
				v.getHora(), v.getPlazas() + "", v.getPlazasLibres() + ""};
		return String.join(",", values);
	}

}
