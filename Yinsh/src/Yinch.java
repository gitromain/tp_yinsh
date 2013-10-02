import java.util.Random;

import exceptions.*;

public class Yinch {

	private int[][] jeu;
	private int[][] marker;

	public Yinch() {
		jeu = new int[11][11];
		marker = new int[11][11];
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

	public int put_ring(char colonne, int ligne, color couleur)
			throws GrilleException, RingCouleurException, RingIntersecException {
		int c = 0;
		if (couleur == old)
			throw new RingCouleurException();
		if (couleur == color.BLACK) {
			c = 1;
			old = color.BLACK;
		} else {
			c = 2;
			old = color.WHITE;
		}
		if (colonne == 'A' && (ligne < 2 || ligne > 5))
			throw new GrilleException();
		if (jeu[(int) (colonne - 'A')][ligne - 1] != 0)
			throw new RingIntersecException();
		return jeu[(int) (colonne - 'A')][ligne - 1] = c;

	}

	public int etat_init() {
		int cpt = 0;
		for (int i = 0; i < jeu.length; i++) {
			for (int j = 0; j < jeu[i].length; j++) {
				if (jeu[i][j] != 0) {
					cpt++;
				}
			}
		}
		return cpt;
	}

	public boolean is_initialized() {
		int cpt = etat_init();
		if (cpt == 10) {
			return true;
		} else {
			return false;
		}

	}

	public int put_marker(char colonne, int ligne, color couleur)
			throws MarkerException {
		int c = 0;
		if (couleur == color.BLACK) {
			c = 1;
			old = color.BLACK;
		} else {
			c = 2;
			old = color.WHITE;
		}
		if (jeu[(int) (colonne - 'A')][ligne - 1] != c) {
			throw new MarkerException();
		} else {
			return marker[(int) (colonne - 'A')][ligne - 1] = c;
		}
	}

	public int move_ring(char col_now, int lig_now, char col_next, int lig_next)
			throws MoveRingException, PassageRingException {
		int c = 0; // rÃ©cupÃ©ration de la couleur.
		c = jeu[(int) (col_now - 'A')][lig_now - 1];
		if (jeu[(int) (col_next - 'A')][lig_next - 1] != 0) {
			throw new MoveRingException();
		} 
		
		int cpt=0;
		/* On va traiter les 6 cas de déplacement de l'anneau */
		/* 1er cas : déplacement en haut => colonne de départ identiques et lignes départ inférieure a arrivée */
		if((int) (col_now - 'A')==(int) (col_next - 'A') && (int) (lig_now - 1)<(int) (lig_next - 1) ){
		
		}
			
		/* 2ème cas : déplacement en haut à droite => colonne de départ inférieur a arrivée et ligne départ inférieure à arrivée */
		if((int) (col_now - 'A')<(int) (col_next - 'A') && (int) (lig_now - 1)<(int) (lig_next - 1) ){
			int j=(int) (lig_now - 1);
			for (int i = (int) (col_now - 'A'); i <= (int) (col_next - 'A'); i++) { //on ne parcourt que la colonne souhaité
				if(j!=(int) (lig_next - 1)){
					if(jeu[i][j]!=0){
						cpt++;
					}
				}
				j++;
			}	
			
			if(cpt != 1){// Je pense que la boucle va compter l'année col_now lig_now du coup ca en fera un au départ.
				throw new PassageRingException();
			}
			else{
				
			}
		}
		
		/* 3ème cas : déplacement en haut à gauche => colonne de départ supérieur à arrivée et ligne départ inférieur à arrivée */
		if((int) (col_now - 'A')>(int) (col_next - 'A') && (int) (lig_now - 1)<(int) (lig_next - 1) ){
			/* instruction */
		}
		
		/* 4ème cas : déplacement en bas => colonne de départ identiques et lignes départ supérieur a arrivée */
		if((int) (col_now - 'A')==(int) (col_next - 'A') && (int) (lig_now - 1)>(int) (lig_next - 1) ){
			/* instruction */
		}
		
		/* 5ème cas : déplacement en bas à droite => colonne de départ inférieur a arrivée et ligne départ supérieur à arrivée */
		if((int) (col_now - 'A')<(int) (col_next - 'A') && (int) (lig_now - 1)>(int) (lig_next - 1) ){
			/* instruction */
		}
		
		/* 6ème cas : déplacement en bas à gauche => colonne de départ supérieur a arrivée et ligne départ supérieur à arrivée */
		if((int) (col_now - 'A')>(int) (col_next - 'A') && (int) (lig_now - 1)>(int) (lig_next - 1) ){
			/* instruction */
		}
		
		jeu[(int) (col_now - 'A')][lig_now - 1] = 0;
		return jeu[(int) (col_next - 'A')][lig_next - 1] = c;
		
	}

	static Random rand = new Random();

	static int pRand(int mod) {
		return Math.abs(rand.nextInt()) % mod;
	}

	public static void main(String[] args) {

	}

}
