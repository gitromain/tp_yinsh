import exceptions.*;
import junit.framework.TestCase;

public class TestYinch extends TestCase {
	public TestYinch(String name) {
		super(name);
	}

	public void testCouleur() { // test la couleur de d�part de l'anneau
		Yinch y = new Yinch();
		assertTrue(y.currentColor() == Yinch.PlayerColor.WHITE
				|| y.currentColor() == Yinch.PlayerColor.BLACK);
	}

	public void testRing() throws GrilleException, RingCouleurException,
			RingIntersecException { /*
									 * test si un anneau est placé dans la
									 * grille
									 */
		Yinch y = new Yinch();
		assertTrue(y.putRing('A', 2, Yinch.PlayerColor.WHITE) == 2);
		assertTrue(y.putRing('A', 3, Yinch.PlayerColor.BLACK) == 1);
	}

	public void testInitial() { // test si la grille est vide
		Yinch y = new Yinch();
		assertTrue(y.etatInit() == 0);

	}

	public void testUnRing() throws GrilleException, RingCouleurException,
			RingIntersecException { /*
									 * test si après insertion d'un anneau, il
									 * y'a un anneau sur le grille
									 */
		Yinch y = new Yinch();
		assertTrue(y.putRing('A', 3, Yinch.PlayerColor.BLACK) == 1);
		assertTrue(y.etatInit() == 1);
	}

	public void testExceptionCoordonnees() throws GrilleException,
			RingCouleurException, RingIntersecException {
		Yinch y = new Yinch();
		try {
			y.putRing('A', 1, Yinch.PlayerColor.BLACK);
			assertTrue(false);
		} catch (GrilleException e) {
			assertTrue(true);
		}
	}

	public void testMemeCouleur() throws GrilleException,
			RingCouleurException, RingIntersecException {
		Yinch y = new Yinch();
		try {
			y.putRing('A', 3, Yinch.PlayerColor.BLACK);
			y.putRing('A', 4, Yinch.PlayerColor.BLACK);
			assertTrue(false);
		} catch (RingCouleurException e) {
			assertTrue(true);
		}
	}

	public void testIntersection() throws GrilleException,
			RingCouleurException, RingIntersecException {
		Yinch y = new Yinch();
		try {
			y.putRing('A', 3, Yinch.PlayerColor.BLACK);
			y.putRing('A', 3, Yinch.PlayerColor.WHITE);
			assertTrue(false);
		} catch (RingIntersecException e) {
			assertTrue(true);
		}
	}

	public void testIsInitialized() throws GrilleException,
			RingCouleurException, RingIntersecException {
		Yinch y = new Yinch();
		try {
			y.putRing('A', 3, Yinch.PlayerColor.BLACK);
			y.putRing('A', 4, Yinch.PlayerColor.WHITE);
			y.putRing('B', 3, Yinch.PlayerColor.BLACK);
			y.putRing('B', 4, Yinch.PlayerColor.WHITE);
			y.putRing('C', 3, Yinch.PlayerColor.BLACK);
			y.putRing('C', 4, Yinch.PlayerColor.WHITE);
			y.putRing('D', 3, Yinch.PlayerColor.BLACK);
			y.putRing('D', 4, Yinch.PlayerColor.WHITE);
			y.putRing('E', 3, Yinch.PlayerColor.BLACK);
			y.putRing('E', 4, Yinch.PlayerColor.WHITE);
			assertTrue(y.isInitialized());
		} catch (RingIntersecException e) {
			assertTrue(true);
		}
	}

	public void testPutMarker() throws MarkerException {
		Yinch y = new Yinch();
		try {
			assertTrue(y.putMarker('D', 2, Yinch.PlayerColor.BLACK) == 1);
		} catch (MarkerException e) {
			assertTrue(true);
		}

	}

	public void testMoveRing() throws GrilleException, RingCouleurException,
			RingIntersecException, MoveRingException, MarkerException,
			PassageRingException {
		Yinch y = new Yinch();

		y.putRing('D', 6, Yinch.PlayerColor.WHITE);

		try {
			y.putRing('D', 2, Yinch.PlayerColor.BLACK);
			y.putMarker('D', 2, Yinch.PlayerColor.BLACK);
			assertTrue(y.moveRing('D', 2, 'D', 6) == 1);
		} catch (MoveRingException e) {
			assertTrue(true);
		}

	}

	public void testPassageRing() throws GrilleException,
			RingCouleurException, RingIntersecException, MoveRingException,
			PassageRingException, MarkerException {
		Yinch y = new Yinch();
		try {
			y.putRing('D', 2, Yinch.PlayerColor.BLACK);
			y.putMarker('D', 2, Yinch.PlayerColor.BLACK);
			assertTrue(y.moveRing('D', 2, 'I', 7) == 1);
		} catch (PassageRingException e) {
			assertTrue(true);
		}

	}

	
	public void testPossibleMove() throws MarkerException, GrilleException, RingCouleurException, RingIntersecException{
		Yinch y = new Yinch();
		y.putRing('E', 9, Yinch.PlayerColor.BLACK);
		y.putMarker('E', 9, Yinch.PlayerColor.BLACK);
		assertTrue(y.possibleMove('E', 4) == Yinch.s_listMove);
		
	}
	
	public void testBlitzMode() throws GrilleException, RingCouleurException, RingIntersecException, MarkerException {
		Yinch y = new Yinch();
		y.putRing('E', 5, Yinch.PlayerColor.BLACK);
		y.putMarker('F', 6, Yinch.PlayerColor.BLACK);
		y.putMarker('G', 7, Yinch.PlayerColor.BLACK);
		y.putMarker('H', 8, Yinch.PlayerColor.BLACK);
		y.putMarker('I', 9, Yinch.PlayerColor.BLACK);
		y.putMarker('J', 10, Yinch.PlayerColor.BLACK);
		assertTrue(y.isEndGameBlitz());
		
	}

}
