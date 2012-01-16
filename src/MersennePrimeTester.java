import java.util.Scanner;


public class MersennePrimeTester {
	public MersennePrimeTester(int num) {
		if(num < 2){
			System.out.println("Please enter a number over 2.");
			return;
		}
			
		if(primeTester(num) && primeTester(Math.pow(2, num)-1))
			System.out.println("M-Prime");
		else
			System.out.println("Not M-Prime");
	}

	public static void main (String[] args){
		while(true)
			new MersennePrimeTester(new Scanner(System.in).nextInt());
	}
	
	public boolean primeTester(double num) {
		for(int i = (int) (Math.sqrt(num)); i != 1; i--)
			if(num % i == 0)
				return false;
		
		return true;
			
	}
	
}
