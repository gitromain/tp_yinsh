import java.util.ArrayList;
import java.util.Iterator;

import exceptions.*;
import junit.framework.TestCase;

public class TestYinch extends TestCase {
	public TestYinch(String name) {
		super(name);
	}

	public void test_couleur() { // test la couleur de d�part de l'anneau
		Yinch y = new Yinch();
		assertTrue(y.current_color() == Yinch.color.WHITE
				|| y.current_color() == Yinch.color.BLACK);
	}

	public void test_ring() throws GrilleException, RingCouleurException,
			RingIntersecException { /*
									 * test si un anneau est placé dans la
									 * grille
									 */
		Yinch y = new Yinch();
		assertTrue(y.put_ring('A', 2, Yinch.color.WHITE) == 2);
		assertTrue(y.put_ring('A', 3, Yinch.color.BLACK) == 1);
	}

	public void test_initial() { // test si la grille est vide
		Yinch y = new Yinch();
		assertTrue(y.etat_init() == 0);

	}

	public void test_un_ring() throws GrilleException, RingCouleurException,
			RingIntersecException { /*
									 * test si après insertion d'un anneau, il
									 * y'a un anneau sur le grille
									 */
		Yinch y = new Yinch();
		assertTrue(y.put_ring('A', 3, Yinch.color.BLACK) == 1);
		assertTrue(y.etat_init() == 1);
	}

	public void test_exception_coordonnees() throws GrilleException,
			RingCouleurException, RingIntersecException {
		Yinch y = new Yinch();
		try {
			y.put_ring('A', 1, Yinch.color.BLACK);
			assertTrue(false);
		} catch (GrilleException e) {
			assertTrue(true);
		}
	}

	public void test_meme_couleur() throws GrilleException,
			RingCouleurException, RingIntersecException {
		Yinch y = new Yinch();
		try {
			y.put_ring('A', 3, Yinch.color.BLACK);
			y.put_ring('A', 4, Yinch.color.BLACK);
			assertTrue(false);
		} catch (RingCouleurException e) {
			assertTrue(true);
		}
	}

	public void test_intersection() throws GrilleException,
			RingCouleurException, RingIntersecException {
		Yinch y = new Yinch();
		try {
			y.put_ring('A', 3, Yinch.color.BLACK);
			y.put_ring('A', 3, Yinch.color.WHITE);
			assertTrue(false);
		} catch (RingIntersecException e) {
			assertTrue(true);
		}
	}

	public void test_is_initialized() throws GrilleException,
			RingCouleurException, RingIntersecException {
		Yinch y = new Yinch();
		try {
			y.put_ring('A', 3, Yinch.color.BLACK);
			y.put_ring('A', 4, Yinch.color.WHITE);
			y.put_ring('B', 3, Yinch.color.BLACK);
			y.put_ring('B', 4, Yinch.color.WHITE);
			y.put_ring('C', 3, Yinch.color.BLACK);
			y.put_ring('C', 4, Yinch.color.WHITE);
			y.put_ring('D', 3, Yinch.color.BLACK);
			y.put_ring('D', 4, Yinch.color.WHITE);
			y.put_ring('E', 3, Yinch.color.BLACK);
			y.put_ring('E', 4, Yinch.color.WHITE);
			assertTrue(y.is_initialized() == true);
		} catch (RingIntersecException e) {
			assertTrue(true);
		}
	}

	public void test_put_marker() throws MarkerException {
		Yinch y = new Yinch();
		try {
			assertTrue(y.put_marker('D', 2, Yinch.color.BLACK) == 1);
		} catch (MarkerException e) {
			assertTrue(true);
		}

	}

	public void test_move_ring() throws GrilleException, RingCouleurException,
			RingIntersecException, MoveRingException, MarkerException,
			PassageRingException {
		Yinch y = new Yinch();

		y.put_ring('D', 6, Yinch.color.WHITE);

		try {
			y.put_ring('D', 2, Yinch.color.BLACK);
			y.put_marker('D', 2, Yinch.color.BLACK);
			assertTrue(y.move_ring('D', 2, 'D', 6) == 1);
		} catch (MoveRingException e) {
			assertTrue(true);
		}

	}

	public void test_passage_ring() throws GrilleException,
			RingCouleurException, RingIntersecException, MoveRingException,
			PassageRingException, MarkerException {
		Yinch y = new Yinch();
		try {
			y.put_ring('D', 2, Yinch.color.BLACK);
			y.put_marker('D', 2, Yinch.color.BLACK);
			assertTrue(y.move_ring('D', 2, 'I', 7) == 1);
		} catch (PassageRingException e) {
			assertTrue(true);
		}

	}

	
	public void test_possible_move() throws MarkerException, GrilleException, RingCouleurException, RingIntersecException{
		Yinch y = new Yinch();
		y.put_ring('E', 9, Yinch.color.BLACK);
		y.put_marker('E', 9, Yinch.color.BLACK);
		assertTrue(y.possible_move('E',4) == Yinch.list_move );
		
	}
	
	public void test_blitz_mode() throws GrilleException, RingCouleurException, RingIntersecException, MarkerException {
		Yinch y = new Yinch();
		y.put_ring('E', 5, Yinch.color.BLACK);
		y.put_marker('F', 6, Yinch.color.BLACK);
		y.put_marker('G', 7, Yinch.color.BLACK);
		y.put_marker('H', 8, Yinch.color.BLACK);
		y.put_marker('I', 9, Yinch.color.BLACK);
		y.put_marker('J', 10, Yinch.color.BLACK);
		assertTrue(y.end_game_blitz() == true );
		
	}

}
