/**
 * 
 */
package tamagoshi;

import java.util.Random;

/**
 * @author Thibaut
 *
 */
class Tamagoshi {
	
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tamagoshi t1=new Tamagoshi("Jojo");
		System.out.println(t1);
		Random r1=new Random();
		System.out.println(5+r1.nextInt(9-5));
		System.out.println(3+r1.nextInt(7-3));
	
	}

}
