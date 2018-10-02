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
	
	private int age;
	private int maxEnergy;
	private int energy;
	private String name;
	private static int lifeTime =10;
	private Random rand;

	/**
	 * @param age
	 * @param maxEnergy
	 * @param energy
	 * @param name
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
	 * 
	 * @return 
	 * >4 heureux sinon affamé
	 * 
	 */
	public boolean parle() {
		if(energy>4) {
			Utilisateur.afficheEcran(name+" est heureux");
			return true;
		}
		else {
			Utilisateur.afficheEcran(name+" est affamé ");
			return false;
		}		
	}
	
	public boolean consommeEnergie() {
		energy--;
		if(energy<=0) {
			Utilisateur.afficheEcran(name+" est KO");
			return false;
		}
		return true;
	}
	
	
	
	/**
	 * @return
	 */
	public boolean mange() {
		if(energy<maxEnergy) {
			energy+=1+rand.nextInt(3-1);
			Utilisateur.afficheEcran(name+" est content, il a bien mangé!");
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
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the maxEnergy
	 */
	public int getMaxEnergy() {
		return maxEnergy;
	}

	/**
	 * @param maxEnergy the maxEnergy to set
	 */
	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	/**
	 * @return the energy
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * @param energy the energy to set
	 */
	public void setEnergy(int energy) {
		this.energy = energy;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lifeTime
	 */
	public static int getLifeTime() {
		return lifeTime;
	}

	/**
	 * @param lifeTime the lifeTime to set
	 */
	public static void setLifeTime(int lifeTime) {
		Tamagoshi.lifeTime = lifeTime;
	}

	/**
	 * 
	 */
	public Tamagoshi() {
		// TODO Auto-generated constructor stub
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tamagoshi [age=" + age + ", maxEnergy=" + maxEnergy + ", energy=" + energy + ", name=" + name + "]";
	}
	
	public boolean isLife() {
		return energy>0;
	}
	
	public void vieillir() {
		age++;		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tamagoshi t1=new Tamagoshi("Jojo");
		Utilisateur.afficheEcran(t1);
		t1.parle();
		t1.mange();
		int i=0;
		while(t1.isLife()){
			Utilisateur.afficheEcran("tour :"+i);
			t1.consommeEnergie();
			i++;
		}	
		
	
	}
	

}
