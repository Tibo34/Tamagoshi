/**
 * 
 */
package tamagoshi.jeu;

import java.util.ArrayList;
import java.util.Random;

import tamagoshi.tamagoshi.GrosJoueur;
import tamagoshi.tamagoshi.GrosMangeur;
import tamagoshi.tamagoshi.Tamagoshi;
import tamagoshi.util.Utilisateur;

/**
 * @author Thibaut Molina
 * @version 1.0
 *
 */
public class TamaGame {
	
	/**
	 * nbTamagoshi nombre de tamagoshi par défault
	 */
	private int nbTamagoshi=4;
	/**
	 * nbTour nombre de tours d'une partie
	 */
	private int nbTour=5;
	/**
	 * ArrayList de tamagoshi au départ 
	 */
	private ArrayList<Tamagoshi> listDepart;
	/**
	 * ArrayList de tamagoshi en vie
	 */
	private ArrayList<Tamagoshi> listInLife;
	/**
	 * Nombre aléatoire
	 */
	private Random rand;

	/**
	 * Jeu par défault avec 4 tamagoshi.
	 */
	public TamaGame() {
		this.listDepart=new ArrayList<Tamagoshi>();
		this.listInLife=new ArrayList<Tamagoshi>();
		this.rand=new Random();
		this.initialisation();
	}

	/**
	 * Créer le jeu et l'initialise avec un nombre de tamagoshi.
	 * @param nb nombre de tamagoshi
	 */
	public TamaGame(int nb) {
		this.listDepart=new ArrayList<Tamagoshi>();
		this.listInLife=new ArrayList<Tamagoshi>();
		this.nbTamagoshi=nb;
		this.rand=new Random();
		this.initialisation();
	}
	
	/**
	 * Créer le jeu et l'initialise avec un nombre de tamagoshi
	 */
	public TamaGame(String[]name) {
		this.listDepart=new ArrayList<Tamagoshi>();
		this.listInLife=new ArrayList<Tamagoshi>();
		this.nbTamagoshi=name.length;
		this.rand=new Random();
		this.initialisation(name);
	}
	
	/**
	 * initialisation initialise le jeu.
	 * @return true si l'initialisation a bien eu lieu et false sinon
	 */
	private boolean initialisation() {
		String name="";
		boolean r=true;
		for(int i=0;i<nbTamagoshi&&r;i++) {
			Utilisateur.afficheEcran("Saisir nom du Tamagoshi n° "+i);
			name=Utilisateur.saisieClavier();			
			r=addList(typeTamagoshi(name));		
		}
		return r;
		
	}
	/**
	 * typeTamagoshi crée un tamagoshi de type aléatoire et le retourne.
	 * @param name nom du tamgoshi
	 * @return Tamagoshi
	 */
	private Tamagoshi typeTamagoshi(String name) {		
		switch(rand.nextInt(4)) {
		case 2:
			return new GrosJoueur(name);
		case 3:
			return new GrosMangeur(name);
		default:
			return new Tamagoshi(name);
		}
	}
	
	/**
	 * initialisation initialise le jeu avec un tableau de nom.
	 * @param array string 
	 * @return true si l'initialisation a bien eu lieu et false sinon
	 */
	private boolean initialisation(String[]name) {
		boolean r=true;		
		for(int i=0;i<nbTamagoshi&&r;i++) {		
			r=addList(typeTamagoshi(name[i]));		
		}	
		return true;		
	}
		
	/**
	 * Ajoute un tamagoshi au deux listes.
	 * @param t le tamagoshi à ajouter
	 * @return true si l'ajout a réussi.
	 */
	private boolean addList(Tamagoshi t) {
		boolean r=true;
		r=listDepart.add(t);
		r=listInLife.add(t);
		return r;
	}	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str="TamaGame [listDepart= \n";
		for (Tamagoshi tamagoshi : listDepart) {
			str+=tamagoshi.getClass()+" "+tamagoshi+"\n";
		}
		for (Tamagoshi tamagoshi : listInLife) {
			str+=tamagoshi.getClass()+" "+tamagoshi+"\n";			
		}
		return str;
	}
	
	/**
	 * Détermine si le jeu est fini.
	 * @return true si oui et false sinon
	 */
	private boolean isEnd() {
		return listInLife.isEmpty()||nbTour==0;
	}
	
	
	/**
	 * Joue une partie.
	 */
	public void play() {		
		while(!this.isEnd()) {
			AllParle(listInLife);
			InlifeChoix(listInLife);
			Utilisateur.afficheEcran("Qui voulez-vous nourrir?");						
			int choice=choiceTamagoshi(listInLife);
			Utilisateur.afficheEcran(choice);
			listInLife.get(choice).mange();
			avanceTour(listInLife);		
			Utilisateur.afficheEcran("nombre de tour restant : "+nbTour);
			nbTour--;
		}
		this.endGame(listDepart);		
	}
	
	/**
	 * Tour de jeu consomme l'énergie des tamagoshi encore en vie.
	 * @param list ArrayList de tamagoshi encore en vie
	 */
	private void avanceTour(ArrayList<Tamagoshi> list) {
		ArrayList<Tamagoshi> removeList=new ArrayList<Tamagoshi>();
		for(Tamagoshi tam : list) {
			if(tam instanceof GrosJoueur) {
				((GrosJoueur) tam).consommeFun();
			}
			if(tam instanceof GrosMangeur) {
				((GrosMangeur)tam).consommeEnergie();
			}
			else {
				tam.consommeEnergie();
			}
			if(!tam.isLife()) {
				removeList.add(tam);
			}
			else {
				tam.vieillir();
			}
		}
		list.removeAll(removeList);		
	}
	
	/**
	 * Propose au joueur de nourrir un tamagoshi.
	 * @param list ArrayList des tamagoshi encore en vie
	 * @return int le choix du joueur
	 */
	private int choiceTamagoshi(ArrayList<Tamagoshi> list) {
		int choice=-1;
		do  {
			try {
				choice=Integer.parseInt(Utilisateur.saisieClavier());
			}
			catch(NumberFormatException e){
				Utilisateur.afficheEcran("ce n'est pas un nombre");
				choice=-1;
			}
		}while(choice<0 && choice>list.size() );
		return choice;
	}
	
	/**
	 * Affiche les tamgoshi encore en vie et leur numéro.
	 * @param list ArrayList de Tamagoshi encore en vie
	 */
	private void InlifeChoix(ArrayList<Tamagoshi> list) {
		String str="";
		for(Tamagoshi tam : list) {
			str+=tam.getName()+"\t"+list.indexOf(tam)+"\t";
		}
		Utilisateur.afficheEcran(str);
	}
	
	/**
	 * Fait parler tous les tamagoshi en vie.
	 * @param list ArrayList tamgoshi en vie.
	 */
	private void AllParle(ArrayList<Tamagoshi> list) {
		for(Tamagoshi tam : list) {
			tam.parle();
		}
	}
	
	
	/**
	 * Affichage de fin du jeu.
	 * @param list ArrayList de tamagoshi.
	 */
	private void endGame(ArrayList<Tamagoshi> list) {
		int score=CalculScore();
		String str="";
		for(Tamagoshi tam : list) {
			str=tam.getName()+" qui était un "+getType(tam);
			if(listInLife.contains(tam)) {
				str+="  a survécu et vous remercie :) ";
			}
			Utilisateur.afficheEcran(str);
		}
		Utilisateur.afficheEcran("Fin du jeu le score est "+score);
	}
	
	/**
	 * Récupère le type d'un tamagoshi et le retourne sous forme de string.
	 * @param tam  Tamagoshi
	 * @return string
	 */
	private String getType(Tamagoshi tam) {
		String str=tam.getClass().toString().substring("class tamagoshi.tamagoshi.".length());
		return str;
	}
	
	
	
	/**
	 * Calcule le score du jeu.
	 * @return  int le score du joueur
	 */
	private int CalculScore() {
		int nb=0;
		for (Tamagoshi tamagoshi : listDepart) {
			nb+=tamagoshi.getAge();
		}
		return nb;
	}
	
	
	
}
