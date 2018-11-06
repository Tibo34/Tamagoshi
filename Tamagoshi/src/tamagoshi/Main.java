package tamagoshi;

import tamagoshi.jeu.TamaGame;


public class Main {

	public static void main(String[]args) {
		String name[]= {"Riri","Fifi","Loulou","Lili"};		
		TamaGame jeu=new TamaGame(name);	
		jeu.play();
	}

}
