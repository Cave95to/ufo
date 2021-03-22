package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SightingDAO {
	

	/** metodo che effettua una query, la connessione può generare SQLException
	 * @return lista di shapes contenute nel db
	 */
	public List<String> readShapes() {
		Connection conn;
		try {
			// ci connettiamo tramite la nuova classe che rischia eccezione
			conn = DBConnect.getConnection();
			
			//query come versione 2
			String sql = "SELECT DISTINCT shape FROM sighting WHERE shape <>'' ORDER BY shape";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			//processiamo risultato
			List<String> formeUfo = new ArrayList<>();
			while (res.next()) {
				
				String forma = res.getString("shape");
				formeUfo.add(forma);
				
			}
			
			conn.close();
			return formeUfo;
			
		} catch (SQLException e) {
			// aggiungiamo messaggio errore per spiegare quale query
			throw new RuntimeException("Database error in readShapes", e);
			
		}
		
	}
	
	
	/** metodo che effettua una query, la connessione può generare SQLException
	 * @return int, totale avvistamenti della shape di input
	 */
	public int countByShape(String shape) {
		
		Connection conn;
		try {
			
			// ci connettiamo tramite la nuova classe che rischia eccezione
			conn = DBConnect.getConnection();
			
			//query come versione 2
			String sql2 = "SELECT COUNT(*) AS cnt FROM sighting WHERE shape = ? ";
			PreparedStatement st2 = conn.prepareStatement(sql2);
			
			// andiamo a sostituire i valori inseriti al posto dei ?
			// 1 indica il numero di ? che stiamo sostituendo
			st2.setString(1, shape);
			
			ResultSet res2 = st2.executeQuery();
			
			//sappiamo di avere 1 sola riga nel risultato. first o next sarebbe uguale
			res2.first();
			
			int count = res2.getInt("cnt");
			
			conn.close();
			return count;
			
		} catch(SQLException e) {
			// aggiungiamo messaggio errore per spiegare quale query
			throw new RuntimeException("Database error in countByShape", e);
		
		}
	}
}
