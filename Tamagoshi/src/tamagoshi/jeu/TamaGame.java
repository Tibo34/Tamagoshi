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
	 * Arraylist de nom
	 */
	private ArrayList<String> TamaName;

	/**
	 * Jeu par défault avec 4 tamagoshi.
	 */
	public TamaGame() {
		this.listDepart=new ArrayList<Tamagoshi>();
		this.listInLife=new ArrayList<Tamagoshi>();
		this.TamaName=new ArrayList<String>();
		this.rand=new Random();		
	}

	/**
	 * Créer le jeu et l'initialise avec un nombre de tamagoshi.
	 * @param nb nombre de tamagoshi
	 */
	public TamaGame(int nb) {
		this();		
		this.nbTamagoshi=nb;	
	}
	
	/**
	 * Créer le jeu et l'initialise avec un nombre de tamagoshi
	 */
	public TamaGame(String[]name) {
		this(name.length);
		AddName(name);
	}
	
	private void AddName(String[]name) {
		for(String n : name) {
			this.TamaName.add(n);
		}
	}
	/**
	 * initialisation initialise le jeu.
	 * @return true si l'initialisation a bien eu lieu et false sinon
	 */
	private boolean initialisation() {
		boolean r=false;
		if(TamaName.size()==0) {
			r=NameTamagoshi();
		}
		for(String n : TamaName) {
			r=addList(typeTamagoshi(n));	
		}		
		return r;		
	}
	
	
	private boolean NameTamagoshi() {
		int i=0;
		for(i=0;i<nbTamagoshi;i++) {
			Utilisateur.afficheEcran("Saisir nom du Tamagoshi n° "+i);
			TamaName.add(Utilisateur.saisieClavier());		
		}
		return i==nbTamagoshi;
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
		this.initialisation();
		while(!this.isEnd()) {
			AllParle();			
			ChoiceManger();			
			ChoiceFun();
			avanceTour();		
			Utilisateur.afficheEcran("nombre de tour restant : "+nbTour);
			nbTour--;
		}
		this.endGame();		
	}
	
	private void ChoiceManger() {
		InlifeChoix();
		Utilisateur.afficheEcran("Qui voulez-vous nourrir?");
		listInLife.get(choiceTamagoshi()).mange();
	}
	
	private void ChoiceFun() {
		InlifeChoix();
		Utilisateur.afficheEcran("Avec qui voulez-vous jouer?");						
		listInLife.get(choiceTamagoshi()).fun();
	}
	
	/**
	 * Tour de jeu consomme l'énergie des tamagoshi encore en vie.
	 * @param list ArrayList de tamagoshi encore en vie
	 */
	private void avanceTour() {
		ArrayList<Tamagoshi> removeList=new ArrayList<Tamagoshi>();
		for(Tamagoshi tam : listInLife) {			
			 tam.consommeFun();			
			 tam.consommeEnergie();		
			if(!tam.isLife()) {
				removeList.add(tam);
			}
			else {
				tam.vieillir();
			}
		}
		Utilisateur.afficheEcran("nombre de tama à enlever : "+removeList.size());
		Utilisateur.afficheEcran("nombre de tama : "+listInLife.size());
		listInLife.removeAll(removeList);
		Utilisateur.afficheEcran("nombre de tama : "+listInLife.size());
	}
	
	/**
	 * Propose au joueur de nourrir un tamagoshi.
	 * @return int le choix du joueur
	 */
	private int choiceTamagoshi() {
		int choice=-1;
		do  {
			try {
				choice=Integer.parseInt(Utilisateur.saisieClavier());
			}
			catch(NumberFormatException e){
				Utilisateur.afficheEcran("ce n'est pas un nombre");
				choice=-1;
			}
		}while(choice<0 && choice>listInLife.size() );
		return choice;
	}
	
	/**
	 * Affiche les tamgoshi encore en vie et leur numéro.	
	 */
	private void InlifeChoix() {
		String str="";
		for(Tamagoshi tam : listInLife) {
			str+=tam.getName()+"\t"+listInLife.indexOf(tam)+"\t";
		}
		Utilisateur.afficheEcran(str);
	}
	
	/**
	 * Fait parler tous les tamagoshi en vie.
	 */
	private void AllParle() {
		for(Tamagoshi tam : listInLife) {
			tam.parle();
		}
	}
	
	
	/**
	 * Affichage de fin du jeu.
	 * @param list ArrayList de tamagoshi.
	 */
	private void endGame() {
		double score=CalculScore();
		String str="";
		for(Tamagoshi tam : listDepart) {
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
	 * @return  double le score du joueur
	 */
	private double CalculScore() {
		int nb=0;
		int max=Tamagoshi.getLifeTime()*listDepart.size();
		for (Tamagoshi tamagoshi : listDepart) {
			if(listInLife.contains(tamagoshi)) {
				nb+=Tamagoshi.getLifeTime();
			}
			else {
				nb+=tamagoshi.getAge();
			}
		}
		return nb/max*100;
	}
	
	
	
}
