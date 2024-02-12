package myBankPro.packge;

import java.sql.SQLException;
import java.util.Scanner;

public class MainBankApp {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Wel-Come to My Banking App");
		System.out.println("--------------------------------");
		System.out.println("1)Login(Only if already have Account)\n2)Registration (For new Account)");
		
		System.out.println("-->Enter your Choice from Above(1/2)");
		
		int ch=sc.nextInt();
		switch(ch)
		{
		case 1:
			BankOperations.loginToAccount();
			break;
		case 2:
			BankOperations.registration();
			break;
		case 3:
			System.exit(0);
		default :
			System.out.println("You Entered Wrong Choice.....\nPlease Enter Correct Choice");
			break;
		}
		
	}

}
