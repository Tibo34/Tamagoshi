/**
 * 
 */
package tamagoshi.jeu;

import java.util.ArrayList;
import tamagoshi.tamagoshi.Tamagoshi;
import tamagoshi.util.Utilisateur;



/**
 * @author thibaut
 *
 */
public class TamaGame {
	
	private int nbTamagoshi=4;
	private ArrayList<Tamagoshi> listDepart;
	private ArrayList<Tamagoshi> listInLife;
	private int score=0;

	/**
	 * 
	 */
	public TamaGame() {
		this.listDepart=new ArrayList<Tamagoshi>();
		this.listInLife=new ArrayList<Tamagoshi>();
		this.initialisation();
	}

	/**
	 * 
	 */
	public TamaGame(int nb) {
		this.listDepart=new ArrayList<Tamagoshi>();
		this.listInLife=new ArrayList<Tamagoshi>();
		this.nbTamagoshi=nb;
		this.initialisation();
	}
	
	public boolean initialisation() {
		String str="";		
		for(int i=0;i<nbTamagoshi-1;i++) {
			Utilisateur.afficheEcran("Saisir nom du Tamagoshi");
			str=Utilisateur.saisieClavier();
			Tamagoshi t=new Tamagoshi(str);
			addList(t);
			Utilisateur.afficheEcran(nbTamagoshi-i+" restant Tamagoshi");
		}		
		if(listDepart.isEmpty()||listInLife.isEmpty()) {
			return false;
		}
		return true;
		
	}
		

	private void addList(Tamagoshi t) {
		listDepart.add(t);
		listInLife.add(t);		
	}
	
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str="TamaGame [listDepart= ";
		for (Tamagoshi tamagoshi : listDepart) {
			str+=tamagoshi+"\n";
		}
		for (Tamagoshi tamagoshi : listInLife) {
			str+=tamagoshi+"\n";			
		}
		return str;
	}
	public boolean fini() {
		return listInLife.isEmpty();
	}
	
	public void play() {
		String choix="";
		while(!this.fini()) {
			for(Tamagoshi tamagoshi : listInLife) {
				choix="";
				tamagoshi.parle();
				while(!(choix.equals("oui")||choix.equals("non"))) {
					choix=Utilisateur.saisieClavier();
				}
				if(choix.equals("oui")){
					tamagoshi.mange();
				}
				tamagoshi.consommeEnergie();
				if(!tamagoshi.isLife()){
					listInLife.remove(tamagoshi);
				}
				else {
					score++;
				}
			}
		}
		Utilisateur.afficheEcran("Fin du jeu");
		Utilisateur.afficheEcran("le score est "+score);
	}
		
	


	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
