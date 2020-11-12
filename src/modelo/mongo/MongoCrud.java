package modelo.mongo;

import java.util.Collection;

import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import controlador.CRUD;
import controlador.ListaVuelos;
import modelo.Vuelo;

public class MongoCrud implements CRUD {
	
	MongoConnection conn = new MongoConnection();

	@Override
	public ListaVuelos getVuelos() throws Exception {
		
		ListaVuelos vuelos = new ListaVuelos();
		
		MongoCollection<Document> vuelosColl = conn.getColeccionVuelos();
		FindIterable<Document> iterable = vuelosColl.find();
		for (Document doc : iterable) {
			Vuelo vuelo = documentToVuelo(doc);
			vuelos.add(vuelo);
		}
		
		return vuelos;
	}

	@Override
	public void setVuelos(ListaVuelos listaVuelos) throws Exception {

		// borrar todos
		MongoCollection<Document> vuelosColl = conn.getColeccionVuelos();
		Document borrarQuery = new Document(); // empty = delete all
		vuelosColl.deleteMany(borrarQuery);
		
		// insertar vuelos uno a uno
		Collection<Vuelo> vuelos = listaVuelos.toCollection();
		
		for (Vuelo vuelo : vuelos) {
			insertarVuelo(vuelo);
		}
		
	}

	@Override
	public void actualizarVuelo(String codigo, Vuelo vuelo) throws Exception {

		MongoCollection<Document> vuelosColl = conn.getColeccionVuelos();
		
		Document updateQuery = new Document("codigo", codigo);
		Document updateValues = new Document("$set", vueloToDocument(vuelo));
		
		vuelosColl.updateOne(updateQuery, updateValues);
	}

	@Override
	public void insertarVuelo(Vuelo vuelo) throws Exception {
		
		MongoCollection<Document> vuelosColl = conn.getColeccionVuelos();
		
		vuelosColl.insertOne(vueloToDocument(vuelo));
		
	}

	@Override
	public void borrarVuelo(String codigo) throws Exception {
		
		MongoCollection<Document> vuelosColl = conn.getColeccionVuelos();
		
		Document borrarQuery = new Document("codigo", codigo);
		
		vuelosColl.deleteOne(borrarQuery);
		
	}

	@Override
	public void dispose() {
		conn.dispose();
	}
	
	private Vuelo documentToVuelo(Document doc) {
		return new Vuelo(doc.getString("codigo"), doc.getString("origen"), doc.getString("destino"),
					doc.getLong("fecha") + "", doc.getString("hora"), doc.getInteger("plazas_totales") + "",
					doc.getInteger("plazas_disponibles") + "");
	}
	
	private Document vueloToDocument(Vuelo v) {
		return new Document("codigo", v.getCodigo())
					.append("origen", v.getSalida())
					.append("destino", v.getDestino())
					.append("fecha", v.getFecha().getTime())
					.append("hora", v.getHora())
					.append("plazas_totales", v.getPlazas())
					.append("plazas_disponibles", v.getPlazasLibres());
	}

}
