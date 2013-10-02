package exceptions;

public class RingCouleurException extends Exception {
	
	
	public RingCouleurException(){
		
	}
	
	public String toString(){
		return "ERREUR -> Deux même couleurs consécutives";
	}
}

