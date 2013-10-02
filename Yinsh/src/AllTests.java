import junit.framework.*;


public class AllTests {

	public static Test yinch(){
		TestSuite yinch = new TestSuite("test");
		
		yinch.addTest(new TestSuite(TestYinch.class));
		return yinch;
		
	}
	public static void main(String[] args) {
		junit.textui.TestRunner.run(AllTests.yinch());
	}

}
 