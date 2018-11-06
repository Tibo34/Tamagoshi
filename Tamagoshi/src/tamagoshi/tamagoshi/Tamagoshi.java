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
	 * @param name string nom du tamagoshi
	 */
	public Tamagoshi(String name) {
		super();
		this.age = 0;
		this.rand=new Random();
		this.maxEnergy =5+rand.nextInt(9-5);
		this.energy = 3+rand.nextInt(7-3);
		this.name = name;
	}
	
	/**
	 * parle fait parl� le tamagoshi.
	 * @return true si le tamagoshi est heureux et false sinon	 
	 */
	public boolean parle() {
		if(energy>4) {
			Utilisateur.afficheEcran(name+" est heureux");
			return true;
		}
		else {
			Utilisateur.afficheEcran(name+" est affam� "+energy);
			return false;
		}		
	}
	
	/**
	 * consomeEnergie d�cr�mente l'�nergie du tamagoshi
	 * @return true si l'�nergie du tamagoshi reste sup�rieur � 0 et false sinon
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
	 * mange fait manger le tamagoshi en ajoutant entre 1 et 3 � l'�nergie.
	 * @return true si le tamagoshi a bien mang� et false sinon
	 */
	public boolean mange() {
		if(energy<maxEnergy) {
			energy+=1+rand.nextInt(3-1);
			Utilisateur.afficheEcran(name+" est content, il a bien mang�!"+energy);
			return true;
		}
		else {
			Utilisateur.afficheEcran(name+"  n'a pas faim !.");
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
	 * D�termine si le tamagoshi est en vie
	 * @return true si la tamgoshi est en vie et false sinon
	 */
	public boolean isLife() {
		return energy>0;
	}
	
	/**
	 * vieillir augmente l'age du tamagoshi
	 */
	public void vieillir() {
		age++;		
	}
	
	

	
		
	
	
	

}
