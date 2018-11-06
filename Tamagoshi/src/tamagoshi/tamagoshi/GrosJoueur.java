package tamagoshi.tamagoshi;

import tamagoshi.util.Utilisateur;

/**
 * 
 * @author Thibaut
 *
 */
public class GrosJoueur extends Tamagoshi {

	/**
	 * Constructeur
	 * @param name string
	 */
	public GrosJoueur(String name) {
		super(name);		
	}
	
	/**
	 * Consomme l'energie du tamgoshi
	 * @return true si opération réussi et false sinon.
	 */
	public boolean consommeFun() {
		boolean r=false;
		r=super.consommeEnergie();
		r=super.consommeEnergie();
		return r;
	}
	

}
