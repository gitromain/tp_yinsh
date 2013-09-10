import java.util.Random;


public class Yinch {
	
	public enum color {BLACK, WHITE};
	
	public color current_color(){
		int setColor = pRand(2);
		if(setColor == 1){
			System.out.println(setColor);
			System.out.println("La couleur noire commence");
			return color.BLACK;	
		}
		else{
			System.out.println(setColor);
			System.out.println("La couleur blanche commence");
			return color.WHITE;
		}
		
		
	}
	
	static Random rand = new Random();

	static int pRand(int mod) {
		return Math.abs(rand.nextInt()) % mod;
	}
	
	public static void main(String[] args) {
		
	}

}
