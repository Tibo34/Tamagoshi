package tamagoshi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Utilisateur {

	  public static String saisieClavier(){
	    /*il faut g�rer les exceptions car l'entr�e standard 
	    peut ne pas �tre disponible : le constructeur de la 
	    classe InputStreamReader peut renvoyer une exception.*/
	    try{ 
	      BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
	      return clavier.readLine();
	    }
	    catch(IOException e){
	      e.printStackTrace();
	      System.exit(0);
	      return null;
	    }
	  }
	  
	  public static void afficheEcran(String str) {
		  System.out.println(str);
	  }
	  
	  public static void afficheEcran(Object str) {
		  System.out.println(str);
	  }
	  
	  // une m�thode main juste pour tester 
	  public static void main(String[] args) {
	    String saisie = Utilisateur.saisieClavier();
	    Utilisateur.afficheEcran("la saisie est : "+saisie);
	  }
	

}
