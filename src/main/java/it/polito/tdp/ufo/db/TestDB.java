package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDB {

	public static void main(String[] args) {
		/*
		//definiamo connessione
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=root" ;
		
		try {
			//stabiliamo connessione
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			
				/* VERSIONE VECCHIA 1
				//creiamo statement
				Statement st = conn.createStatement();
				
				//eseguiamo query
				String sql = "SELECT DISTINCT shape FROM sighting WHERE shape <>'' ORDER BY shape";
				
				ResultSet res = st.executeQuery(sql);
				*/
		
		
			/* VERSIONE 2
			//CREIAMO QUERY PARAMETRICHE
			
			//modifichiamo la query precedente, ancora non parametrica ma cambia ordine righe perché usiamo 
			//preparedStatement rispetto a prima
			String sql = "SELECT DISTINCT shape FROM sighting WHERE shape <>'' ORDER BY shape";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet res = st.executeQuery();
			*/
		
		
				/* comune ad entrambe le fasi 1 e 2
				//processiamo risultato
				List<String> formeUfo = new ArrayList<>();
				while (res.next()) {
					
					String forma = res.getString("shape");
					formeUfo.add(forma);
					
				}
				
				System.out.println(formeUfo);
				*/
		
		
			/*
			//nuova query parametrica
			// ? per ogni valore incognito
			String sql2 = "SELECT COUNT(*) AS cnt FROM sighting WHERE shape = ? ";
			
			// non abbiamo finestra per utente quindi facciamo così per ora
			String shapeScelta = "circle";
			
			PreparedStatement st2 = conn.prepareStatement(sql2);
			
			// andiamo a sostituire i valori inseriti al posto dei ?
			// 1 indica il numero di ? che stiamo sostituendo
			st2.setString(1, shapeScelta);
			
			ResultSet res2 = st2.executeQuery();
			
			//sappiamo di avere 1 sola riga nel risultato. first o next sarebbe uguale
			res2.first();
			
			int count = res2.getInt("cnt");
			System.out.println("Ufo di forma "+shapeScelta+" sono "+count);
			*/
		
				
				/*
				//chiudiamo connessione
				conn.close();
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		// VERSIONE 3 CON DAO
		SightingDAO dao = new SightingDAO();
		
		List<String> formeUfo = dao.readShapes();
		
		for (String forma : formeUfo) {
			int count = dao.countByShape(forma);
			System.out.println("Ufo di forma "+forma+" sono "+count);
		}
		
	}

}
