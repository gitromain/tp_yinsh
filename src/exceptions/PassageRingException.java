package exceptions;

public class PassageRingException extends Exception {
	public PassageRingException() {

	}

	public String toString() {
		return "ERREUR -> Déplacement immpossible, un anneaux bloque le chemin.";
	}

}
