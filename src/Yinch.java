import java.util.Random;
import exceptions.*;

public class Yinch {

	private int[][] jeu;

	public Yinch() {
		jeu = new int[11][11];
	}

	public enum color {
		BLACK, WHITE
	};
	
	color old = null;

	
	public color current_color() {
		int setColor = pRand(2);
		if (setColor == 1) {
			System.out.println("La couleur noire commence");
			return color.BLACK;
		} else {
			System.out.println("La couleur blanche commence");
			return color.WHITE;
		}

	}

	public int put_ring(char colonne, int ligne, color couleur) throws GrilleException, RingCouleurException, RingIntersecException{
		int c =0;
		if(couleur == old)throw new RingCouleurException();
		if(couleur==color.BLACK){
			c=1;
			old = color.BLACK;
		}
		else{
			c=2;
			old = color.WHITE;
		}
		if(colonne=='A' && (ligne <2 || ligne>5))throw new GrilleException();
		if(jeu[(int) (colonne - 'A')][ligne - 1] != 0)throw new RingIntersecException();
		return jeu[(int) (colonne - 'A')][ligne - 1] = c;

	}

	public int etat_init() {
		int cpt = 0;
		for (int i = 0; i < jeu.length; i++) {
			for (int j = 0; j < jeu[i].length; j++) {
				if (jeu[i][j] != 0) {
					cpt ++;
				}
			}
		}
		return cpt;
	}
	

	
	static Random rand = new Random();

	static int pRand(int mod) {
		return Math.abs(rand.nextInt()) % mod;
	}

	public static void main(String[] args) {

	}

}
