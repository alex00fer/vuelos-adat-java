package modelo.hibernate;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import controlador.CRUD;
import controlador.ListaVuelos;
import modelo.Vuelo;

public class HibernateCrud implements CRUD {

	private HibernateConnection conn;

	public HibernateCrud() {
		conn = new HibernateConnection();
	}

	@Override
	public ListaVuelos getVuelos() throws Exception {

		Session session = conn.getSession();
		
		@SuppressWarnings("unchecked")
		List<Vuelo> result = session.createQuery("from Vuelo").list();
		
		ListaVuelos vuelos = new ListaVuelos();
		
		for (Vuelo vuelo : result) {
			vuelos.add(vuelo);
		}
		
		return vuelos;

	}

	@Override
	public void setVuelos(ListaVuelos listaVuelos) throws Exception {

		Session session = conn.getSession();
		
		
		
		Transaction transaction = session.beginTransaction();
		
		// delete everything first
		session.createQuery("DELETE FROM Vuelo").executeUpdate();
		
		// then add all the new flights from source
		Collection<Vuelo> vuelos = listaVuelos.toCollection();

		for (Vuelo vuelo : vuelos) {
			session.save(vuelo);
		}
		
		transaction.commit();

	}

	@Override
	public void actualizarVuelo(String codigo, Vuelo vuelo) throws Exception {
		
		if (vuelo.getCodigo() == codigo) {
			Session session = conn.getSession();
			Transaction transaction = session.beginTransaction();
			
			// Como se preserva el objeto vuelo original no hace falta hacer
			// una nueva busqueda. Aún así lo dejo aquí por si fuese
			// necesario cambiarlo en el futuro.
			//
			// Vuelo vueloUpdate = (Vuelo)session.load(Vuelo.class, codigo);
			// vueloUpdate.copyFrom(vuelo);
			
			session.update(vuelo);
			
			transaction.commit();
		}
		else {
			// Hibernate no soporta que se modifique el codigo/id
			// primero borramos el vuelo con el código antiguo
			borrarVuelo(codigo);
			// y después insertamos el nuevo vuelo
			insertarVuelo(vuelo);
		}

	}

	@Override
	public void insertarVuelo(Vuelo vuelo) throws Exception {
		
		Session session = conn.getSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(vuelo);
		
		transaction.commit();

	}

	@Override
	public void borrarVuelo(String codigo) throws Exception {

		Session session = conn.getSession();
		Transaction transaction = session.beginTransaction();
		
		Vuelo vueloBorrar = (Vuelo)session.load(Vuelo.class, codigo);
		session.delete(vueloBorrar);
		
		transaction.commit();

	}

	@Override
	public void dispose() {
		
		conn.dispose();

	}

}
