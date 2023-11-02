package gitHub;

import java.util.Scanner;

public class FactorialNumbers {

	public static void main(String[] args) {
		 Scanner sc=new Scanner(System.in);
		System.out.println("Enter a number:");
		int num=sc.nextInt();
		int fact=1;
	   for(int i=num;i>=1;i--)
	   {
		   fact=fact*i;
	   }
	   System.out.println("The factorial of "+num+" is:"+fact);
	   sc.close();
	}

}
