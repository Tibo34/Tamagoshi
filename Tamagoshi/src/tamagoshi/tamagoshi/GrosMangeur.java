package tamagoshi.tamagoshi;

import tamagoshi.util.Utilisateur;

/**
 * 
 * @author Thibaut Molina
 *
 */
public class GrosMangeur extends Tamagoshi {

	
	/**
	 * Construteur
	 * @param name string
	 */
	public GrosMangeur(String name) {
		super(name);		
	}
	
	/**
	 * consommeEnergie
	 * @return true si l'opération à réussi et false sinon.
	 */
	public boolean consommeEnergie() {		
		boolean r=false;
		r=super.consommeEnergie();
		r=super.consommeEnergie();
		return r;
	}

	

}
