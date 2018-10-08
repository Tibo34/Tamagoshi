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
	private int nbTour=5;
	private ArrayList<Tamagoshi> listDepart;
	private ArrayList<Tamagoshi> listInLife;


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
	
	/**
	 * initialisation initialise le jeu
	 * @return true si l'initialisation a bien eu lieu et false sinon
	 */
	public boolean initialisation() {
		String str="";		
		for(int i=0;i<nbTamagoshi;i++) {
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
	public boolean isEnd() {
		return listInLife.isEmpty()||nbTour==0;
	}
	
	
	/**
	 * Joue une partie
	 */
	public void play() {		
		while(!this.isEnd()) {
			for(Tamagoshi tamagoshi : listDepart) {
				if(listInLife.contains(tamagoshi)) {
					tamagoshi.parle();
				}				
			}
			Utilisateur.afficheEcran("Qui voulez-vous nourrir?");
			for(Tamagoshi tamagoshi : listDepart) {
				if(listInLife.contains(tamagoshi)) {
					Utilisateur.afficheEcran(tamagoshi.getName()+" "+listInLife.indexOf(tamagoshi));
				}				
			}
			int choix=-1;
			while(choix<0||choix>listInLife.size()) {
				try {
				choix=Integer.parseInt(Utilisateur.saisieClavier());
				}
				catch (NumberFormatException e) {
					Utilisateur.afficheEcran("ce n'est pas un nombre");
				}
			}
			listInLife.get(choix).mange();
			for (Tamagoshi tamagoshi : listDepart) {
				if(listInLife.contains(tamagoshi)) {
					tamagoshi.consommeEnergie();
					if(tamagoshi.isLife()) {
						tamagoshi.vieillir();
					}
					else {
						listInLife.remove(tamagoshi);
					}
				}
			}
			Utilisateur.afficheEcran("nombre de tour restant : "+nbTour);
			nbTour--;
		}
		this.endGame();
		
	}
	
	
	/**
	 * Affichage de fin du jeu
	 */
	public void endGame() {
		int score=CalculScore();
		Utilisateur.afficheEcran("Fin du jeu le score est "+score);
	}
	
	
	/**
	 * Calcule le score
	 * @return le score du joueur
	 */
	private int CalculScore() {
		int nb=0;
		for (Tamagoshi tamagoshi : listDepart) {
			nb+=tamagoshi.getAge();
		}
		return nb;
	}

}
