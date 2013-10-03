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
	
	
	/*retourne la couleur du marker dont les coord sont passés en paramètre*/
	public int color_marker(char colonne, int ligne){
		return marker[(int) (colonne - 'A')][ligne - 1];
	}
	
	
	/*vide la case passer en parametre (sert pour les tests)w*/
	public int remove_ring(char colonne, int ligne){
		return jeu[(int) (colonne - 'A')][ligne - 1]=0;
	}
	

	public int move_ring(char col_now, int lig_now, char col_next, int lig_next)
			throws MoveRingException, PassageRingException {
		int c = 0; // récupération de la couleur.
		c = jeu[(int) (col_now - 'A')][lig_now - 1];
		if (jeu[(int) (col_next - 'A')][lig_next - 1] != 0) {
			throw new MoveRingException();
		} 
		
		int cpt=0;
		/* On va traiter les 6 cas de d�placement de l'anneau */
		/* 1er cas : d�placement en haut => colonne de d�part identiques et lignes d�part inf�rieure a arriv�e */
		if((int) (col_now - 'A')==(int) (col_next - 'A') && (int) (lig_now - 1)<(int) (lig_next - 1) ){
			int j=(int) (col_now - 'A');
			for (int i = (int) (lig_now - 1); i <= (int) (lig_next - 1); i++) { //on ne parcourt que la colonne souhait�
				if(j==(int) (col_now - 'A')){
					if(marker[i][j]!=0){
						int couleur=marker[i][j];
							if(couleur==1){
								marker[i][j]=2;
							}
							else if(couleur==2){
								marker[i][j]=1;
							}
					}
					if(jeu[i][j]!=0){
						cpt++;
					}
				}	
			}
		if(cpt != 1){
			throw new PassageRingException();
		}
		else{
			
		}
		}
			
		/* 2�me cas : d�placement en haut � droite => colonne de d�part inf�rieur a arriv�e et ligne d�part inf�rieure � arriv�e */
		if((int) (col_now - 'A')<(int) (col_next - 'A') && (int) (lig_now - 1)<(int) (lig_next - 1) ){
			int j2=(int) (lig_now - 1);
			for (int i = (int) (col_now - 'A'); i <= (int) (col_next - 'A'); i++) { //on ne parcourt que la colonne souhait�

					if(marker[i][j2]!=0){
						int couleur=marker[i][j2];
							if(couleur==1){
								marker[i][j2]=2;
							}
							else if(couleur==2){
								marker[i][j2]=1;
							}
					}
					if(jeu[i][j2]!=0){
						cpt++;
					}

				j2++;
			}	
			
			if(cpt != 1){
				throw new PassageRingException();
			}
			else{
				
			}
		}
		
		/* 3�me cas : d�placement en haut � gauche => colonne de d�part sup�rieur � arriv�e et ligne d�part inf�rieur � arriv�e */
		if((int) (col_now - 'A')>(int) (col_next - 'A') && (int) (lig_now - 1)<(int) (lig_next - 1) ){
			/* instruction */
		}
		
		/* 4�me cas : d�placement en bas => colonne de d�part identiques et lignes d�part sup�rieur a arriv�e */
		if((int) (col_now - 'A')==(int) (col_next - 'A') && (int) (lig_now - 1)>(int) (lig_next - 1) ){
			/* instruction */
		}
		
		/* 5�me cas : d�placement en bas � droite => colonne de d�part inf�rieur a arriv�e et ligne d�part sup�rieur � arriv�e */
		if((int) (col_now - 'A')<(int) (col_next - 'A') && (int) (lig_now - 1)>(int) (lig_next - 1) ){
			/* instruction */
		}
		
		/* 6�me cas : d�placement en bas � gauche => colonne de d�part sup�rieur a arriv�e et ligne d�part sup�rieur � arriv�e */
		if((int) (col_now - 'A')>(int) (col_next - 'A') && (int) (lig_now - 1)>(int) (lig_next - 1) ){
			/* instruction */
		}
		
		jeu[(int) (col_now - 'A')][lig_now - 1] = 0;
		return jeu[(int) (col_next - 'A')][lig_next - 1] = c;
		
	}

    public int[][] remove_row(char col_now, int lig_now, char col_next, int lig_next){
        int cpt=0;

		/* 2�me cas : d�placement en haut � droite => colonne de d�part inf�rieur a arriv�e et ligne d�part inf�rieure � arriv�e */
        if((int) (col_now - 'A')<(int) (col_next - 'A') && (int) (lig_now - 1)<(int) (lig_next - 1) ){
            int j=(int) (lig_now - 1);
            for (int i = (int) (col_now - 'A'); i <= (int) (col_next - 'A'); i++) { //on ne parcourt que la colonne souhait�

                marker[i][j]=0 ;
                j++;
            }


    }
        return marker;
    }

	static Random rand = new Random();

	static int pRand(int mod) {
		return Math.abs(rand.nextInt()) % mod;
	}


}
