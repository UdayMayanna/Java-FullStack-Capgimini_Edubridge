package gitHub;

public class AmstrongNumber {

	public static void main(String[] args) {
		for (int num = 1; num <= 9; num++)
		{
			double sum = 0;
			int rem = 0;
			int temp = num;
			String len = String.valueOf(num);
			int pow = len.length();
			while (num > 0) {
				rem = num % 10;
				sum = sum + Math.pow(rem, pow);
				num = num / 10;
			}
			if (temp == sum) {
				System.out.println("Number is amstrong number " + temp);
			} 
			
		}
	}
}
