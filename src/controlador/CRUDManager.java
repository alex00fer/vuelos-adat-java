package controlador;

import modelo.datosFichero.FicheroCrud;
import modelo.datosMySQL.MySQLCrud;
import modelo.hibernate.HibernateCrud;

public class CRUDManager {

	public enum MedioCRUD {
		Fichero, 
		MySQL,
		Hibernate,
	}

	public CRUD getCrud(MedioCRUD medio) throws Exception {
		switch (medio) {
		case Fichero:
			return new FicheroCrud();
		case MySQL:
			return new MySQLCrud();
		case Hibernate:
			return new HibernateCrud();
		default:
			throw new Exception("Medio no implementado");
		}
	}

	public void intercambioCrud(MedioCRUD origen, MedioCRUD destino) throws Exception {
		CRUD crudA = getCrud(origen);
		CRUD crudB = getCrud(destino);
		
		crudB.setVuelos(crudA.getVuelos());
	}

}
