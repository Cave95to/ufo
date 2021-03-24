package it.polito.tdp.ufo.model;

import java.util.List;

import it.polito.tdp.ufo.db.SightingDAO;

public class Model {

		private List<String> formeUfo = null;
		
		/** metodo che chiama SEMPRE dao per avere le forme ufo (a meno che non le abbia già salvate)
		 * 
		 * @return lista di forme ufo
		 */
		public List<String> getFormeUFO() {
			// se non abbiamo già la lista 
			if (this.formeUfo==null) {
				
				SightingDAO dao = new SightingDAO() ;
				this.formeUfo = dao.readShapes() ;
				
			}
			
			return this.formeUfo;
		}

		/** metodo che chiama SEMPRE dao per avere numero avvistamenti data una forma
		 * 
		 * @return  num avvistamenti di una forma
		 */
		public int getCountByForma(String forma) {
			
			SightingDAO dao = new SightingDAO();
			
			return dao.countByShape(forma) ;
		}
		
	
}
