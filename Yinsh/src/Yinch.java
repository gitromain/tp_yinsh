import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import exceptions.*;

public class Yinch {

	private int[][] jeu; // zone de jeu des anneaux
	private int[][] marker; // zone de jeu des marqeurs
	static ArrayList<String> list_move; // liste des déplacements possible
	private boolean mode; // 0 ) mode normal, 1 = mode Blitz

	public Yinch() {
		jeu = new int[11][11];
		marker = new int[11][11];
		mode = (Boolean) null;
	}

	/* permet de choisir le mode de jeu (blitz ou normal) */
	public boolean game_mode() {
		int test_buffer = 0;
		while (test_buffer == 0) {
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(
					System.in));
			String line = "";
			System.out
					.println("Choisissez le mode de jeu : 0 = normal / 1 = blitz");
			try {
				line = keyboard.readLine();
			} catch (IOException e) {
				System.out.println("err");
			}
			/* test pour forcer l'utilisateur a entrer 1 ou 0 */
			if (line != "0" || line != "1") {
				System.out.println("Les seuls choix possibles sont 0 ou 1 !");
			} else {
				test_buffer = 1;
				if (line == "1") {
					mode = true;
				} else {
					mode = false;
				}
			}
		}
		return mode;

	}

	/*
	 * test si le plateu marker possède un alignement de 5 marker de même
	 * couleur, retourne vrai si c'est le cas
	 */
	public boolean end_game_blitz() {
		boolean end = false;
		int cpt_black = 0;
		int cpt_white = 0;
		int cpt_align = 1;
		/*
		 * on test si le tableau des markers comporte au moins 5 markers de la
		 * même couleur
		 */
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (marker[i][j] == 1) {
					cpt_black += 1;
				}
				if (marker[i][j] == 2) {
					cpt_white += 1;
				}
			}
		}
		if (cpt_black < 5 || cpt_white < 5) {
			return end = false;
		}

		/*
		 * dans le cas où il y'a au moins 5 markers de la même couleur sur le
		 * plateau il faut tester s'il y'a un alignement
		 */
		/*
		 * ici je vais uniquement traité le cas pour la 9eme histoire, c'est à
		 * dire l'alignement des markers noirs de la figure 5
		 */
		if (cpt_black >= 5) {
			for (int i = 0; i < 11; i++) {
				for (int j = 0; j < 11; j++) {
					if (marker[i][j] == 1) {
						/*
						 * Vue que l'on est dans le cas de la figure 5, on
						 * regarde si es cases en haut a droite contiennent des
						 * markers
						 */
						int a=i+1;
						int b=j+1;
						
						while(marker[a][b]==1){
							a++;
							b++;
							cpt_align++;
						}
					}
					if(cpt_align >= 5){
						return end=true;
					}

				}
			}
		}

		return end;

	}

	public void end_game_normal() {

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

		int cpt = 0;
		/* On va traiter les 6 cas de déplacement de l'anneau */
		/*
		 * 1er cas : déplacement en haut => colonne de départ identiques et
		 * lignes départ inférieure a arrivée
		 */
		if ((int) (col_now - 'A') == (int) (col_next - 'A')
				&& (int) (lig_now - 1) < (int) (lig_next - 1)) {

		}

		/*
		 * 2ème cas : déplacement en haut à droite => colonne de départ
		 * inférieur a arrivée et ligne départ inférieure à arrivée
		 */
		if ((int) (col_now - 'A') < (int) (col_next - 'A')
				&& (int) (lig_now - 1) < (int) (lig_next - 1)) {
			int j = (int) (lig_now - 1);
			for (int i = (int) (col_now - 'A'); i <= (int) (col_next - 'A'); i++) {
				if (j != (int) (lig_next - 1)) {
					if (jeu[i][j] != 0) {
						cpt++;
					}
				}
				j++;
			}

			if (cpt != 1) {// La boucle va compter col_now, lig_now du coup ca
							// fera un ring au départ.
				throw new PassageRingException();
			} else {

			}
		}

		/*
		 * 3ème cas : déplacement en haut à gauche => colonne de départ
		 * supérieur à arrivée et ligne départ inférieur à arrivée
		 */
		if ((int) (col_now - 'A') > (int) (col_next - 'A')
				&& (int) (lig_now - 1) < (int) (lig_next - 1)) {
			/* instruction */
		}

		/*
		 * 4ème cas : déplacement en bas => colonne de départ identiques et
		 * lignes départ supérieur a arrivée
		 */
		if ((int) (col_now - 'A') == (int) (col_next - 'A')
				&& (int) (lig_now - 1) > (int) (lig_next - 1)) {
			/* instruction */
		}

		/*
		 * 5ème cas : déplacement en bas à droite => colonne de départ inférieur
		 * a arrivée et ligne départ supérieur à arrivée
		 */
		if ((int) (col_now - 'A') < (int) (col_next - 'A')
				&& (int) (lig_now - 1) > (int) (lig_next - 1)) {
			/* instruction */
		}

		/*
		 * 6ème cas : déplacement en bas à gauche => colonne de départ supérieur
		 * a arrivée et ligne départ supérieur à arrivée
		 */
		if ((int) (col_now - 'A') > (int) (col_next - 'A')
				&& (int) (lig_now - 1) > (int) (lig_next - 1)) {
			/* instruction */
		}

		jeu[(int) (col_now - 'A')][lig_now - 1] = 0;
		return jeu[(int) (col_next - 'A')][lig_next - 1] = c;

	}

	/*
	 * Fonction qui remplie et renvoie une liste des coordonnées de déplacement
	 * possible pour les coordonnées passés en paramètre
	 */
	public ArrayList<String> possible_move(char colonne, int ligne) {
		list_move = new ArrayList<String>();

		// Move haut
		int col = (int) (colonne - 'A');
		for (int i = col; i == col; i++) {
			for (int j = (int) (ligne); j < 11; j++) { // Si les cases sont
														// vides alors on ajoute
														// la coordonnée à la
														// liste
				if (jeu[i][j] == 0 && marker[i][j] == 0 && j != ligne) {
					list_move.add("(" + colonne + "," + Integer.toString(j)
							+ ")");
				}
				if (jeu[i][j] != 0) { // S'il y'a une anneau, on sort de la
										// boucle
					break;
				}
			}
		}

		// Move haut droit

		// Move haut gauche

		// Move bas

		// Move bas droit

		// Move bas gauche

		Iterator<String> it = list_move.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		return list_move;
	}

	static Random rand = new Random();

	static int pRand(int mod) {
		return Math.abs(rand.nextInt()) % mod;
	}

	public static void main(String[] args) {

	}

}
