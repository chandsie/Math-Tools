import java.util.Scanner;
public class RootApproximator {
	public static double AppRoot(int n) {
		if (n > 0)
			return 1 + 1/(1 + AppRoot(n-1));
		else
			return 1;
	}
	
	public static void main(String[] args) {
		System.out.print("Square Root Approximater\n\n\n");
		while(true) {
			System.out.print("Please enter a precision factor:");
			System.out.println("The approximate root is: " + AppRoot(new Scanner(System.in).nextInt()));
		}
	}
}
