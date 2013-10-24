import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import exceptions.*;

public class Yinch {

	private int[][] m_jeuYinch; // zone de m_jeuYinch des anneaux
	private int[][] m_markerYinch; // zone de m_jeuYinch des marqeurs
	static ArrayList<String> s_listMove; // liste des d�placements possible
	private boolean m_modeYinsh; // 0 ) m_modeYinsh normal, 1 = m_modeYinsh Blitz

	public Yinch() {
		m_jeuYinch = new int[11][11];
		m_markerYinch = new int[11][11];
		m_modeYinsh = (Boolean) null;
	}

	/* permet de choisir le m_modeYinsh de m_jeuYinch (blitz ou normal) */
	public boolean isGameMode() {
		int testBuffer = 0;
		while (testBuffer == 0) {
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(
					System.in));
			String line = "";
			System.out
					.println("Choisissez le m_modeYinsh de m_jeuYinch : 0 = normal / 1 = blitz");
			try {
				line = keyboard.readLine();
			} catch (IOException e) {
				System.out.println("err");
			}
			/* test pour forcer l'utilisateur a entrer 1 ou 0 */
			if (!line.equals("0") && !line.equals("1")) {
				System.out.println("Les seuls choix possibles sont 0 ou 1 !");
			} else {
				testBuffer = 1;
                m_modeYinsh = line.equals("1");
			}
		}
		return m_modeYinsh;

	}

	/*
	 * test si le plateu m_markerYinch poss�de un alignement de 5 m_markerYinch de m�me
	 * couleur, retourne vrai si c'est le cas
	 */
	public boolean isEndGameBlitz() {
		boolean end = false;
		int cptBlack = 0;
		int cptWhite = 0;
		int cptAlign = 1;
		/*
		 * on test si le tableau des markers comporte au moins 5 markers de la
		 * m�me couleur
		 */
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (m_markerYinch[i][j] == 1) {
					cptBlack += 1;
				}
				if (m_markerYinch[i][j] == 2) {
					cptWhite += 1;
				}
			}
		}
		if (cptBlack < 5 || cptWhite < 5) {
			 end = false;
		}

		/*
		 * dans le cas o� il y'a au moins 5 markers de la m�me couleur sur le
		 * plateau il faut tester s'il y'a un alignement
		 */
		/*
		 * ici je vais uniquement trait� le cas pour la 9eme histoire, c'est �
		 * dire l'alignement des markers noirs de la figure 5
		 */
		if (cptBlack >= 5) {
			for (int i = 0; i < 11; i++) {
				for (int j = 0; j < 11; j++) {
					if (m_markerYinch[i][j] == 1) {
						/*
						 * Vue que l'on est dans le cas de la figure 5, on
						 * regarde si es cases en haut a droite contiennent des
						 * markers
						 */
						int a=i+1;
						int loopB=j+1;
						
						while(m_markerYinch[a][loopB]==1){
							a++;
							loopB++;
							cptAlign++;
						}
					}
					if(cptAlign >= 5){
						 end=true;
					}

				}
			}
		}

		return end;

	}

	public void endGameNormal() {

	}

	public enum PlayerColor {
		BLACK, WHITE
	}

    PlayerColor m_oldColorYinch = null;

	public PlayerColor currentColor() {
		int setColor = pRand(2);
		if (setColor == 1) {
			System.out.println("La couleur noire commence");
			return PlayerColor.BLACK;
		} else {
			System.out.println("La couleur blanche commence");
			return PlayerColor.WHITE;
		}

	}

	public int putRing(char colonne, int ligne, PlayerColor couleur)
			throws GrilleException, RingCouleurException, RingIntersecException {
		int couleurC;
		if (couleur == m_oldColorYinch)
			throw new RingCouleurException();
		if (couleur == PlayerColor.BLACK) {
			couleurC = 1;
			m_oldColorYinch = PlayerColor.BLACK;
		} else {
			couleurC = 2;
			m_oldColorYinch = PlayerColor.WHITE;
		}
		if (colonne == 'A' && (ligne < 2 || ligne > 5))
			throw new GrilleException();
		if (m_jeuYinch[(int) (colonne - 'A')][ligne - 1] != 0)
			throw new RingIntersecException();
		return m_jeuYinch[(int) (colonne - 'A')][ligne - 1] = couleurC;

	}

	public int etatInit() {
		int cpt = 0;
		for (int i = 0; i < m_jeuYinch.length; i++) {
			for (int j = 0; j < m_jeuYinch[i].length; j++) {
				if (m_jeuYinch[i][j] != 0) {
					cpt++;
				}
			}
		}
		return cpt;
	}

	public boolean isInitialized() {
		int cpt = etatInit();
        return cpt == 10;

	}

	public int putMarker(char colonne, int ligne, PlayerColor couleur)
			throws MarkerException {
		int couleurC ;
		if (couleur == PlayerColor.BLACK) {
			couleurC = 1;
			m_oldColorYinch = PlayerColor.BLACK;
		} else {
			couleurC = 2;
			m_oldColorYinch = PlayerColor.WHITE;
		}
		if (m_jeuYinch[(int) (colonne - 'A')][ligne - 1] != couleurC) {
			throw new MarkerException();
		} else {
			return m_markerYinch[(int) (colonne - 'A')][ligne - 1] = couleurC;
		}
	}

    public int moveRing(char colNow, int ligNow, char colNext, int ligNext)
			throws MoveRingException, PassageRingException {
		int couleurC; // récupération de la couleur.
		couleurC = m_jeuYinch[(int) (colNow - 'A')][ligNow - 1];
		if (m_jeuYinch[(int) (colNext - 'A')][ligNext - 1] != 0) {
			throw new MoveRingException();
		}

		int cpt = 0;
		/* On va traiter les 6 cas de d�placement de l'anneau */
		/*
		 * 1er cas : d�placement en haut => colonne de d�part identiques et
		 * lignes d�part inf�rieure a arriv�e
		 */
		if ((int) (colNow - 'A') == (int) (colNext - 'A')
				&& (int) (ligNow - 1) < (int) (ligNext - 1)) {

		}

		/*
		 * 2�me cas : d�placement en haut � droite => colonne de d�part
		 * inf�rieur a arriv�e et ligne d�part inf�rieure � arriv�e
		 */
		if ((int) (colNow - 'A') < (int) (colNext - 'A')
				&& (int) (ligNow - 1) < (int) (ligNext - 1)) {
			int j = (int) (ligNow - 1);
			for (int i = (int) (colNow - 'A'); i <= (int) (colNext - 'A'); i++) {
				if (j != (int) (ligNext - 1)) {
					if (m_jeuYinch[i][j] != 0) {
						cpt++;
					}
				}
				j++;
			}

			if (cpt != 1) {// La boucle va compter colNow, ligNow du coup ca
							// fera un ring au d�part.
				throw new PassageRingException();
			} else {

			}
		}

		/*
		 * 3�me cas : d�placement en haut � gauche => colonne de d�part
		 * sup�rieur � arriv�e et ligne d�part inf�rieur � arriv�e
		 */
		if ((int) (colNow - 'A') > (int) (colNext - 'A')
				&& (int) (ligNow - 1) < (int) (ligNext - 1)) {
			/* instruction */
		}

		/*
		 * 4�me cas : d�placement en bas => colonne de d�part identiques et
		 * lignes d�part sup�rieur a arriv�e
		 */
		if ((int) (colNow - 'A') == (int) (colNext - 'A')
				&& (int) (ligNow - 1) > (int) (ligNext - 1)) {
			/* instruction */
		}

		/*
		 * 5�me cas : d�placement en bas � droite => colonne de d�part inf�rieur
		 * a arriv�e et ligne d�part sup�rieur � arriv�e
		 */
		if ((int) (colNow - 'A') < (int) (colNext - 'A')
				&& (int) (ligNow - 1) > (int) (ligNext - 1)) {
			/* instruction */
		}

		/*
		 * 6�me cas : d�placement en bas � gauche => colonne de d�part sup�rieur
		 * a arriv�e et ligne d�part sup�rieur � arriv�e
		 */
		if ((int) (colNow - 'A') > (int) (colNext - 'A')
				&& (int) (ligNow - 1) > (int) (ligNext - 1)) {
			/* instruction */
		}

		m_jeuYinch[(int) (colNow - 'A')][ligNow - 1] = 0;
		return m_jeuYinch[(int) (colNext - 'A')][ligNext - 1] = couleurC;

	}

	/*
	 * Fonction qui remplie et renvoie une liste des coordonn�es de d�placement
	 * possible pour les coordonn�es pass�s en param�tre
	 */
	public ArrayList<String> possibleMove(char colonne, int ligne) {
		s_listMove = new ArrayList<String>();

		// Move haut
		int col = (int) (colonne - 'A');
		for (int i = col; i == col; i++) {
			for (int j = (int) (ligne); j < 11; j++) { // Si les cases sont
														// vides alors on ajoute
														// la coordonn�e � la
														// liste
				if (m_jeuYinch[i][j] == 0 && m_markerYinch[i][j] == 0 && j != ligne) {
					s_listMove.add("(" + colonne + "," + Integer.toString(j)
                            + ")");
				}
				if (m_jeuYinch[i][j] != 0) { // S'il y'a une anneau, on sort de la
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

		Iterator<String> it = s_listMove.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		return s_listMove;
	}

	static Random s_randYinch = new Random();

	static int pRand(int mod) {
		return Math.abs(s_randYinch.nextInt()) % mod;
	}

	public static void main(String[] args) {

	}

}
