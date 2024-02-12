package myBankPro.packge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;



public class BankOperations {

	private static Connection conn;
	private static Scanner sc;
	private static ResultSet rs;
	private static PreparedStatement pst;

	private static int userId;
	private static String userName;
	private static String password;
	private static String email;
	private static String mobile;
	private static String city;
	private static String query;
	private static String acc_no;
	private static double balance;
	private static int check;
    private static int ch;
	public static void loginToAccount() throws ClassNotFoundException, SQLException {
		sc=new Scanner(System.in);
		System.out.println("----------------------------------Login Page-------------------------------------");
		System.out.println("Enter Your Email");
		email=sc.next();
		System.out.println("Enter Your Password:");
		password=sc.next();
		
		conn = DatabaseConnection.makeConnection();
		query = "select * from BankUserData where userEmail=? and password=?";
		pst=conn.prepareStatement(query);
		pst.setString(1, email);
		pst.setString(2, password);
		
		rs=pst.executeQuery();
		if(rs.next())
		{
			System.out.println("Logged in Successfully.....");
			userId=rs.getInt(1);
			for(;;) {
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println("1)Creadit Amount\n2)Debit Amount\n3)Balance Enquiry\n4)Transaction History\n5)Profile\n6)Update Account\n7)Delete Account\n8)Log out");
			System.out.println("Enter Your Choice:");
		
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			String dateString = formatter.format(date);
			
			ch=sc.nextInt();
			switch (ch) {
			case 1:
				System.out.println("------------------------------------Creadit Balance-------------------------------");
				query = "select Balance from BankUserData where userId=?";
				 pst=conn.prepareStatement(query);
				 pst.setInt(1, userId);
				 rs=pst.executeQuery();
				 while(rs.next()) {
			       balance=rs.getDouble("Balance");
				 }
				 System.out.println("Enter Amount to Credit:");
				 double creadit=sc.nextDouble();
				 if (creadit<=0)
				 {
					 System.out.println("Enter Valid Amount to Credit");
				 }
				 else {
				 balance=balance+creadit;
				 query = "insert into BankBalance values(?,?,?,?)";
				 pst = conn.prepareStatement(query);
				 pst.setInt(1, userId);
				 pst.setDouble(2, creadit);
				 pst.setString(3, dateString);
				 pst.setString(4, "creadited");
				 check = pst.executeUpdate();
				 if(check>0)
				 {
					 query = "update BankUserData set Balance=? where userId=?";
					 
					 pst=conn.prepareStatement(query);
					 pst.setDouble(1, balance);
					 pst.setInt(2, userId);
					 check =pst.executeUpdate();
					 if(check>0)
					 {
					 System.out.println(creadit+".Rs creadited in your account");
					 }
				 }
				 else {
					System.out.println("Error Occured During Operation");
				}
				 }
				 break;
				 
			case 2:
				System.out.println("------------------------------------Debit Balance-------------------------------");
				query = "select Balance from BankUserData where userId=?";
				 pst=conn.prepareStatement(query);
				 pst.setInt(1, userId);
				 rs=pst.executeQuery();
				 while(rs.next()) {
			       balance=rs.getDouble("Balance");
				 }
				 System.out.println("Enter Amount to Debit:");
				 double debit=sc.nextDouble();
				 if(debit<=0)
				 {
					 System.out.println("Enter Valid Amount to Debit");
				 }
				 else {
				 if(debit>balance)
				 {
					 System.out.println("Insufficient Balance..");
					 break;
				 }
				 
				 balance=balance-debit;
				 query = "insert into BankBalance values(?,?,?,?)";
				 pst = conn.prepareStatement(query);
				 pst.setInt(1, userId);
				 pst.setDouble(2, debit);
				 pst.setString(3, dateString);
				 pst.setString(4, "Debited");
				 check = pst.executeUpdate();
				 if(check>0)
				 {
					 query = "update BankUserData set Balance=? where userId=?";
					 
					 pst=conn.prepareStatement(query);
					 pst.setDouble(1, balance);
					 pst.setInt(2, userId);
					 check =pst.executeUpdate();
					 if(check>0)
					 {
					 System.out.println(debit+".Rs debited from your account");
					 }
				 }
				 
				 else {
					System.out.println("Error Occured During Operation");
				}
				 }
				 break;
				 
			case 3:
				System.out.println("----------------------------------Balance Enquiery--------------------------------------");
				query = "select Balance from BankUserData where userId=?";
				pst=conn.prepareStatement(query);
				pst.setInt(1, userId);
				rs=pst.executeQuery();
				System.out.println("Your Balance is:");
				while(rs.next())
				{
					System.out.println(rs.getDouble("Balance")+" Rs.  "+dateString);
					
				}
				break;
			case 4:
				System.out.println("----------------------------------Transaction Page-------------------------------------");
			    query = "select * from BankBalance where id=?";
				 pst=conn.prepareStatement(query);
				 pst.setInt(1, userId);
				 rs=pst.executeQuery();
				 System.out.println("Amount\t\tDate Of Transaction\tOperation");
				 System.out.println("---------------------------------------------------------------------------------------");
				 while(rs.next())
				 {
					 System.out.println(rs.getDouble(2)+"\t\t"+rs.getDate(3)+"\t\t"+rs.getString(4));
				 }
				break;
				
			case 5:
				System.out.println("------------------------------------------Profile------------------------------------------");
				query = "select * from BankUserData where userId=?";
				pst=conn.prepareStatement(query);
				pst.setInt(1, userId);
				rs = pst.executeQuery();
				while(rs.next())
				{
				  System.out.println("Account No:"+rs.getString("acc_no"));
				  System.out.println("Account Holder Name: "+rs.getString("userName"));	
				  System.out.println("Account Holder Email: "+rs.getString("userEmail"));
				  System.out.println("Account Holder password: "+rs.getString("password"));
				  System.out.println("Account Holder Mobile No: "+rs.getString("userMobile"));
				  System.out.println("Account Holder city: "+rs.getString("city"));
				  System.out.println("Account Balance: "+rs.getString("Balance"));
				  System.out.println("Account Created Date: "+rs.getString("createdDate"));
				  System.out.println("Account Last Updated Date: "+rs.getString("lastUpdated"));
				}
				break;
			case 6:
				System.out.println("------------------------------------Update Profile------------------------------------");
				System.out.println("1)Update User Name\n2)Update Email\n3)Update Mobile No\n4)Update city\n5)Exit-");
				System.out.println("Enter your choice");
				ch=sc.nextInt();   
				switch (ch) {
				case 1:
					System.out.println("Enter New User Name:");
					userName=sc.nextLine();
					query = "update BankUserData set userName=? where userId=?";
					pst=conn.prepareStatement(query);
					pst.setString(1, userName);
					pst.setInt(2, userId);
					check =pst.executeUpdate();
					if(check>0)
					{
						System.out.println("User Name is updated successfully");
					}
					else {
					System.out.println("Failed to update User Name");
					}
					break;
               case 2:
            	   System.out.println("Enter New User Email:");
					email=sc.nextLine();
					query = "update BankUserData set userEmail=? where userId=?";
					pst=conn.prepareStatement(query);
					pst.setString(1, email);
					pst.setInt(2, userId);
					check =pst.executeUpdate();
					if(check>0)
					{
						System.out.println("User Email is updated successfully");
					}
					else {
					System.out.println("Failed to update User Email");
					}
					break;
               case 3:
            	   System.out.println("Enter New User Mobile no:");
					mobile=sc.nextLine();
					query = "update BankUserData set userMobile=? where userId=?";
					pst=conn.prepareStatement(query);
					pst.setString(1, mobile);
					pst.setInt(2, userId);
					check =pst.executeUpdate();
					if(check>0)
					{
						System.out.println("User Mobile no is updated successfully");
					}
					else {
					System.out.println("Failed to update User Mobile no");
					}
					break;
               case 4:
            	   System.out.println("Enter New User city:");
					city=sc.nextLine();
					query = "update BankUserData set city=? where userId=?";
					pst=conn.prepareStatement(query);
					pst.setString(1, city);
					pst.setInt(2, userId);
					check =pst.executeUpdate();
					if(check>0)
					{
						System.out.println("User city is updated successfully");
					}
					else {
					System.out.println("Failed to update User city");
					}
            	    break;
               case 5:
            	   continue;
				default:
					break;
				}
				break;
			case 7:
				System.out.println("------------------------------------Daelete Account-----------------------------------");
				System.out.println("Do your really want to delete you account");
				System.out.println("For yes press y\nFor no press n");
				char choice=sc.next().charAt(0);
				boolean wantToContinue=true;
				if(choice == 'N' || choice=='n')
				{
					wantToContinue=false;
				}
				if(wantToContinue==true)
				{
					query = "delete from BankUserData where userId=? and userEmail=? and password=?";
					System.out.println("Enter Your user id:");
					userId=sc.nextInt();
					System.out.println("Enter Your registerd Email:");
					email=sc.next();
					System.out.println("Enter Your Password");
					password=sc.next();
					
					pst=conn.prepareStatement(query);
					pst.setInt(1, userId);
					pst.setString(2, email);
					pst.setString(3, password);
					
					check =pst.executeUpdate();
					if(check>0)
					{
						query = "delete from BankBalance where id=?";
						pst = conn.prepareStatement(query);
						pst.setInt(1, userId);
						check = pst.executeUpdate();
						if(check>0)
						{
							System.out.println("Account deleted Successfully");
						}
					}
					else {
						System.out.println("Error occured during deleting Account..");
						System.out.println("Please check provided credentials again...");
					}
				}
				break;
			case 8:
				System.out.println("Exiting......");
				registration();
			default:
				System.out.println("You Entered wrong Choice");
				break;
			}
			}
		}
		else {
			System.out.println("Invalid username or password....");
			System.out.println("Or user record might not be present..");
		}
		
	}

	public static void registration() throws ClassNotFoundException, SQLException {
		sc = new Scanner(System.in);
		System.out.println("----------------------------------Registration Page-------------------------------------");
		System.out.println("Enter Your Name:");
		userName = sc.nextLine().toUpperCase();

		System.out.println("Enter Your Account No:");
		acc_no = sc.next();
		
		System.out.println("Enter Your Password:");
		password = sc.next();

		System.out.println("Enter Your Email:");
		email = sc.next();

		System.out.println("Enter Your Moblie No:");
		mobile = sc.next();

		System.out.println("Enter Your City Name");
		city = sc.next();

	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dateString = formatter.format(date);

		conn = DatabaseConnection.makeConnection();

		query = "select * from BankUserData where userName=? and userEmail=?";

		pst = conn.prepareStatement(query);
		pst.setString(1, userName);
		pst.setString(2, email);
		rs = pst.executeQuery();
		if (rs.next()) {
			System.out.println("User is Already Present with this Email Id and Name");
		} else {
			query = "insert into BankUserData (acc_no,userName,password,userEmail,userMobile,city,createdDate,lastUpdated) values(?,?,?,?,?,?,?,?)";

			pst = conn.prepareStatement(query);

			pst.setString(1, acc_no);
			pst.setString(2, userName);
			pst.setString(3, password);
			pst.setString(4, email);
			pst.setString(5, mobile);
			pst.setString(6, city);
			pst.setString(7, dateString);
			pst.setString(8, dateString);

			check = pst.executeUpdate();
			if (check > 0) {
				
				System.out.println("Account is created successfully!!!");
				System.out.println();
				System.out.println("Your email will be your username");
				loginToAccount();
				
			} else {
				System.out.println("Error Occured While Creating Account");
			}
			
			
		}

	}

}
