import junit.framework.TestCase;

public class TestYinch extends TestCase{
	public TestYinch(String name){
		super(name);
	}
	
	
	public void test_couleur(){
		Yinch y = new Yinch();
		assertTrue(y.current_color() == Yinch.color.WHITE || y.current_color() == Yinch.color.BLACK);
	}
}
