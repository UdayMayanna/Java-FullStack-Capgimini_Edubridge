package gitHub;

public class SumOfEvenAndOdd {
public static void main(String[] args) {
	int sumOfEven=0,SumOfOdd=0;
	for(int i=1;i<=100;i++)
	{
		if(i%2==0)
		{
			sumOfEven=sumOfEven+i;
		}
		else
		{
			SumOfOdd=SumOfOdd+i;	
		}
	}
System.out.println("Sum of odds:"+SumOfOdd);
System.out.println("Sum of Evens:"+sumOfEven);
	}

}
