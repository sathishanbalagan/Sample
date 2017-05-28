package javathreads.examples.ch02.example1;

public class Factorial {
	public static void main(String args[]){
		int n = Integer.parseInt("4");
		System.out.print(n + "! is ");
		int fact = 1;
		while (n > 1)
			fact *= n--;
		System.out.println(fact);
	}
}
