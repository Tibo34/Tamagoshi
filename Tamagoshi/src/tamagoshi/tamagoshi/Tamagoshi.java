/**
 * 
 */
package tamagoshi.tamagoshi;

import java.util.Random;

import tamagoshi.util.Utilisateur;

/**
 * @author Thibaut
 *
 */
 public class Tamagoshi {
	
	 /**
	  * age int
	  */
	private int age;
	/**
	 * maxEnergy int
	 */
	private int maxEnergy;
	/**
	 * energy int
	 */
	private int energy;
	/**
	 * name string
	 */
	private String name;
	/**
	 * lifeTime int
	 */
	private static int lifeTime =10;
	/**
	 * rand Random
	 */
	private Random rand;
	/**
	 * fun int
	 */
	private int fun;
	/**
	 * maxFun int
	 */
	private int maxFun;
	

	/**
	 * @param name string nom du tamagoshi
	 */
	public Tamagoshi(String name) {
		super();
		this.age = 0;
		this.rand=new Random();
		this.maxEnergy =5+rand.nextInt(9-5);
		this.energy = 3+rand.nextInt(7-3);
		this.fun=3+rand.nextInt(7-3);
		this.maxFun=5+rand.nextInt(9-5);
		this.name = name;
	}
	
	/**
	 * parle fait parlé le tamagoshi.
	 * @return true si le tamagoshi est heureux et false sinon	 
	 */
	public boolean parle() {
		if(energy>4&&fun>4) {
			Utilisateur.afficheEcran(name+" est heureux");
			return true;
		}
		else {
			if(energy<=4) {
				Utilisateur.afficheEcran(name+" est affamé "+energy);
			}
			if(fun<=4) {
				Utilisateur.afficheEcran(name+" s'ennuie à mourrir ! "+fun);
			}
			return false;
		}		
	}
	
	/**
	 * consomeEnergie décrémente l'énergie du tamagoshi
	 * @return true si l'énergie du tamagoshi reste supérieur à 0 et false sinon
	 */
	public boolean consommeEnergie() {
		energy--;
		if(energy<=0) {
			Utilisateur.afficheEcran(name+" est KO");
			return false;
		}
		return true;
	}
	
	/**
	 * consomme un point de fun.
	 * @return true si réussi et false sinon.
	 */
	public boolean consommeFun() {
		fun--;
		if(fun<=0) {
			Utilisateur.afficheEcran(name+" est KO");
			return false;
		}
		return true;
	}
	
	
	
	
	/**
	 * mange fait manger le tamagoshi en ajoutant entre 1 et 3 à l'énergie.
	 * @return true si le tamagoshi a bien mangé et false sinon
	 */
	public boolean mange() {
		if(energy<maxEnergy) {
			energy+=1+rand.nextInt(3-1);
			Utilisateur.afficheEcran(name+" est content, il a bien mangé!"+energy);
			return true;
		}
		else {
			Utilisateur.afficheEcran(name+"  n'a pas faim !.");
			return false;
		}
	}
	
	public boolean fun() {
		if(fun<maxFun) {
			fun+=1+rand.nextInt(3-1);
			Utilisateur.afficheEcran(name+" est content, il a bien jouer!"+fun);
			return true;
		}
		else {
			Utilisateur.afficheEcran(name+"  laissez moi tranquille je bouquine !.");
			return false;
		}
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/** 
	 * @return le nom du tamagoshi
	 */
	public String getName() {
		return name;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tamagoshi [age=" + age + ", maxEnergy=" + maxEnergy + ", energy=" + energy + ", name=" + name + "]";
	}
	
	/**
	 * Détermine si le tamagoshi est en vie
	 * @return true si la tamgoshi est en vie et false sinon
	 */
	public boolean isLife() {
		return energy>0&&fun>0;
	}
	
	/**
	 * vieillir augmente l'age du tamagoshi
	 */
	public void vieillir() {
		age++;		
	}

	public static int getLifeTime() {
		return lifeTime;
	}

	public static void setLifeTime(int lifeTime) {
		Tamagoshi.lifeTime = lifeTime;
	}
	
	

	
		
	
	
	

}
