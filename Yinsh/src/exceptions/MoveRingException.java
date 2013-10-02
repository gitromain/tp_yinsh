package exceptions;

public class MoveRingException extends Exception {

	public MoveRingException(){
		
	}
	
	public String toString(){
		return "ERREUR -> Déplacement impossible, un anneau est déjà présent.";
	}

}
