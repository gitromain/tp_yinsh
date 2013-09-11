import exceptions.*;
import junit.framework.TestCase;

public class TestYinch extends TestCase {
	public TestYinch(String name) {
		super(name);
	}

	public void test_couleur() { // test la couleur de départ de l'anneau
		Yinch y = new Yinch();
		assertTrue(y.current_color() == Yinch.color.WHITE || y.current_color() == Yinch.color.BLACK);
	}

	public void test_ring() throws GrilleException, RingException { // test si
																	// un anneau
																	// est
		// placé dans la grille
		Yinch y = new Yinch();
		assertTrue(y.put_ring('A', 2, Yinch.color.WHITE) == 2);
		assertTrue(y.put_ring('A', 3, Yinch.color.BLACK) == 1);
	}

	public void test_initial() { // test si la grille est vide
		Yinch y = new Yinch();
		assertTrue(y.etat_init() == 0);

	}

	public void test_un_ring() throws GrilleException, RingException { // test
																		// si
																		// après
		// insertion d'un
		// anneau, il y'a un
		// anneau sur le grille
		Yinch y = new Yinch();
		assertTrue(y.put_ring('A', 3, Yinch.color.BLACK) == 1);
		assertTrue(y.etat_init() == 1);
	}

	public void test_exception_coordonnees() throws GrilleException,
			RingException {
		Yinch y = new Yinch();
		try {
			y.put_ring('A', 1, Yinch.color.BLACK);
			assertTrue(false);
		} catch (GrilleException e) {
			assertTrue(true);
		}
	}

	public void test_meme_couleur() throws GrilleException, RingException {
		Yinch y = new Yinch();
		try {
			y.put_ring('A', 3, Yinch.color.BLACK);
			y.put_ring('A', 4, Yinch.color.BLACK);
			assertTrue(false);
		} catch (RingException e) {
			assertTrue(true);
		}
	}

}
