package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDB {

	public static void main(String[] args) {
		
		//definiamo connessione
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=root" ;
		
		try {
			//stabiliamo connessione
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			//creiamo statement
			Statement st = conn.createStatement();
			
			//eseguiamo query
			String sql = "SELECT DISTINCT shape FROM sighting WHERE shape <>'' ORDER BY shape";
			
			ResultSet res = st.executeQuery(sql);
			
			//processiamo risultato
			List<String> formeUfo = new ArrayList<>();
			while (res.next()) {
				
				String forma = res.getString("shape");
				formeUfo.add(forma);
				
			}
			
			System.out.println(formeUfo);
			
			//chiudiamo connessione
			conn.close();
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
