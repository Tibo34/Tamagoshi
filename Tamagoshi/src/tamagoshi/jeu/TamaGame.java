/**
 * 
 */
package tamagoshi.jeu;

import java.util.ArrayList;
import tamagoshi.tamagoshi.Tamagoshi;
import tamagoshi.util.Utilisateur;

/**
 * @author Thibaut
 *
 */
public class TamaGame {
	
	private int nbTamagoshi=4;
	private int nbTour=5;
	private ArrayList<Tamagoshi> listDepart;
	private ArrayList<Tamagoshi> listInLife;

	/**
	 * Jeu par défault avec 4 tamagoshi
	 */
	public TamaGame() {
		this.listDepart=new ArrayList<Tamagoshi>();
		this.listInLife=new ArrayList<Tamagoshi>();
		this.initialisation();
	}

	/**
	 * Créer le jeu et l'initialise avec un nombre de tamagoshi
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
	private boolean initialisation() {
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
	
	private boolean isEnd() {
		return listInLife.isEmpty()||nbTour==0;
	}
	
	
	/**
	 * Joue une partie
	 */
	public void play() {		
		while(!this.isEnd()) {
			AllParle(listInLife);			
			Utilisateur.afficheEcran("Qui voulez-vous nourrir?");
			InlifeChoix(listInLife);			
			int choice=choiceTamagoshi(listInLife);			
			listInLife.get(choice).mange();
			avanceTour(listInLife);		
			Utilisateur.afficheEcran("nombre de tour restant : "+nbTour);
			nbTour--;
		}
		this.endGame();		
	}
	
	private void avanceTour(ArrayList<Tamagoshi> list) {
		ArrayList<Tamagoshi> removeList=new ArrayList<Tamagoshi>();
		for(Tamagoshi tam : list) {
			tam.consommeEnergie();
			if(!tam.isLife()) {
				removeList.add(tam);
			}
			else {
				tam.vieillir();
			}
		}
		list.removeAll(removeList);		
	}
	
	
	private int choiceTamagoshi(ArrayList<Tamagoshi> list) {
		int choice=-1;
		while(choice<-1||choice>list.size()) {
			try {
				choice=Integer.parseInt(Utilisateur.saisieClavier());
			}
			catch(NumberFormatException e){
				Utilisateur.afficheEcran("ce n'est pas un nombre");
				choice=-1;
			}
		}
		return choice;
	}
	
	private void InlifeChoix(ArrayList<Tamagoshi> list) {
		String str="";
		for(Tamagoshi tam : list) {
			str+=tam.getName()+" "+list.indexOf(tam)+"\t";
		}
		Utilisateur.afficheEcran(str);
	}
	
	private void AllParle(ArrayList<Tamagoshi> list) {
		for(Tamagoshi tam : list) {
			tam.parle();
		}
	}
	
	
	/**
	 * Affichage de fin du jeu
	 */
	private void endGame() {
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
