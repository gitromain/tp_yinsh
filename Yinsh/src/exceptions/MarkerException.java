package exceptions;

public class MarkerException extends Exception {

	public MarkerException(){
		
	}
	
	public String toString(){
		return "ERREUR -> Pas d'anneaux de votre couleur sur cette intersection";
	}
}
