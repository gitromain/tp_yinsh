package exceptions;

public class RingException extends Exception {
	
	
	public RingException(){
		
	}
	
	public String toString(){
		return "ERREUR -> Deux même couleurs consécutives";
	}
}

