package modelo.datosMySQL;

import java.sql.*;
import java.util.Collection;
import controlador.CRUD;
import controlador.ListaVuelos;
import modelo.Vuelo;

public class MySQLCrud implements CRUD {

	MySQLConnection dbConn;

	public MySQLCrud() {
		dbConn = new MySQLConnection();
	}

	@Override
	public ListaVuelos getVuelos() throws Exception {
		ListaVuelos vuelos = new ListaVuelos();
		String query = "SELECT * FROM vuelos";
		Connection conn = dbConn.getConnection();
		if (conn != null) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next()) {
				Vuelo vuelo = new Vuelo(rset.getString("codigo"), rset.getString("origen"), rset.getString("destino"),
						rset.getDate("fecha"), rset.getString("hora"), rset.getInt("plazas"),
						rset.getInt("plazasLibres"));
				vuelos.add(vuelo);
			}
		}

		return vuelos;
	}

	@Override
	public void setVuelos(ListaVuelos listaVuelos) throws Exception {
		Connection conn = dbConn.getConnection();
		if (conn != null) {

			Collection<Vuelo> vuelos = listaVuelos.toCollection();

			borrarVuelos();

			for (Vuelo vuelo : vuelos) {

				String query = " insert into vuelos (codigo, origen, destino, fecha, hora, plazas, plazasLibres)"
						+ " values (?, ?, ?, ?, ?, ?, ?)";

				// create the mysql insert preparedstatement
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				java.sql.Date sqlDate = new java.sql.Date(vuelo.getFecha().getTime());
				preparedStmt.setString(1, vuelo.getCodigo());
				preparedStmt.setString(2, vuelo.getSalida());
				preparedStmt.setString(3, vuelo.getDestino());
				preparedStmt.setDate(4, sqlDate);
				preparedStmt.setString(5, vuelo.getHora());
				preparedStmt.setInt(6, vuelo.getPlazas());
				preparedStmt.setInt(7, vuelo.getPlazasLibres());

				preparedStmt.execute();

			}
		}
	}

	@Override
	public void actualizarVuelo(String codigo, Vuelo vuelo) throws Exception {
		Connection conn = dbConn.getConnection();
		if (conn != null) {

			String query = "UPDATE vuelos SET " + "codigo = ?, origen = ?, destino = ?, fecha = ?, "
					+ "hora = ?, plazas = ?, plazasLibres = ? " + "WHERE codigo = ?";

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			java.sql.Date sqlDate = new java.sql.Date(vuelo.getFecha().getTime());
			preparedStmt.setString(1, vuelo.getCodigo());
			preparedStmt.setString(2, vuelo.getSalida());
			preparedStmt.setString(3, vuelo.getDestino());
			preparedStmt.setDate(4, sqlDate);
			preparedStmt.setString(5, vuelo.getHora());
			preparedStmt.setInt(6, vuelo.getPlazas());
			preparedStmt.setInt(7, vuelo.getPlazasLibres());
			preparedStmt.setString(8, codigo); // codigo vuelo original para el WHERE

			preparedStmt.execute();
		}
	}

	@Override
	public void insertarVuelo(Vuelo vuelo) throws Exception {
		Connection conn = dbConn.getConnection();
		if (conn != null) {

			String query = " insert into vuelos (codigo, origen, destino, fecha, hora, plazas, plazasLibres)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			java.sql.Date sqlDate = new java.sql.Date(vuelo.getFecha().getTime());
			preparedStmt.setString(1, vuelo.getCodigo());
			preparedStmt.setString(2, vuelo.getSalida());
			preparedStmt.setString(3, vuelo.getDestino());
			preparedStmt.setDate(4, sqlDate);
			preparedStmt.setString(5, vuelo.getHora());
			preparedStmt.setInt(6, vuelo.getPlazas());
			preparedStmt.setInt(7, vuelo.getPlazasLibres());

			preparedStmt.execute();
		}
	}

	@Override
	public void borrarVuelo(String codigo) throws Exception {
		Connection conn = dbConn.getConnection();
		if (conn != null) {

			String query = "DELETE FROM vuelos WHERE codigo = ?";

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, codigo);
			preparedStmt.execute();

		}
	}

	public void borrarVuelos() throws Exception {
		String query = "DELETE FROM vuelos";
		Connection conn = dbConn.getConnection();
		if (conn != null) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.execute();
		}
	}

	@Override
	public void dispose() {
		// Nothing to do here
	}

}
