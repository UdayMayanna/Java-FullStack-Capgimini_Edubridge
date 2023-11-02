package gitHub;

import java.util.Scanner;

public class FibonacciNumber {

	public static void main(String[] args) {
		 Scanner sc=new Scanner(System.in);
		 System.out.println("Enter the number:");
		 int num=sc.nextInt();
		 int a=1,b=1,sum=0,i=2;
		 System.out.println(a+"\n"+b+" ");
		 
		 while(i<=num)
		 {
			 sum=a+b;
			 a=b;
			 b=sum;
			 System.out.println(sum+" ");
			 i++;
		 }
sc.close();

	}

}
