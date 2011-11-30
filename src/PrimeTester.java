import java.io.IOException;
import java.util.Scanner;


public class PrimeTester {
		
	public static void main (String [] args) {
		new PrimeTester(new Scanner(System.in).nextDouble());
		
	}
	
	public PrimeTester(double num) {
		boolean prime = true;
		for(int i = (int) (Math.sqrt(num)); i != 1 && prime; i--){
			if(num % i == 0){
				System.out.println("not prime.");
				prime = false;
			}	
		}
		
		if(prime)
			System.out.println("prime");
			
	}
}
