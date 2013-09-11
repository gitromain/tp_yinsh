package exceptions;

public class RingIntersecException extends Exception {
	
public RingIntersecException(){
		
	}
	
	public String toString(){
		return "ERREUR -> Deux anneaux sur la même case";
	}
}
