package com.capg.BankWithJDBC.TransactionDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.capg.BankWithJDBC.beans.Customer;
import com.capg.BankWithJDBC.beans.Transaction;


public class TransactionDAOImpl implements ITransactionDAO {
	Scanner sc = new Scanner(System.in);
	Customer customer1 = new Customer();
	int transactionId=0;
	double bal=0;

	

	public Transaction fundTransfer(Transaction transaction) {
		int count = 0;
		int amt = transaction.getAmountTransfered();
		double fromBal = 0, toBal = 0;
				int fromAcc = 0, toAcc = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "MANISHA", "oracle123");
			PreparedStatement ps = con.prepareStatement("select * from customer_details where account_no=? ");
			ps.setLong(1, transaction.getFromAccountNo());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				fromAcc = rs.getInt(1);
				fromBal = rs.getInt(10);
			}


			PreparedStatement ps1 = con.prepareStatement("select * from customer_details where account_no=? ");
			ps1.setLong(1, transaction.getToAccountNo());
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				toAcc = rs1.getInt(1);
				toBal = rs1.getInt(10);
			}
			if (amt < fromBal) {
				fromBal = fromBal - amt;
				toBal = toBal + amt;
				PreparedStatement ps2 = con.prepareStatement("insert into transaction_details values(trans_seq.nextval,?,?,?)");
				ps2.setInt(1, fromAcc);
				ps2.setInt(2, toAcc);
				ps2.setInt(3, amt);
				int x = ps2.executeUpdate();
				if (x == 1) {
					PreparedStatement ps3 = con.prepareStatement("update customer_details set balance= ? where account_no=?");
					ps3.setDouble(1, fromBal);
					ps3.setInt(2, fromAcc);
					ps3.executeUpdate();
					customer1.setBalance(fromBal);
					System.out.println(customer1.getBalance());
					PreparedStatement ps4 = con.prepareStatement("update customer_details set balance= ? where account_no=?");
					ps4.setDouble(1, toBal);
					ps4.setInt(2, toAcc);
					ps4.executeUpdate();
					
					transaction.setAmountTransfered(amt);
					transaction.setFromAccountNo(fromAcc);
					transaction.setToAccountNo(toAcc);
					PreparedStatement ps9 = con.prepareStatement("select * from transaction_details where from_account_no=?");
					ps9.setInt(1, fromAcc);
					ResultSet rs4=ps9.executeQuery();
					while(rs4.next())
					{
						
							//if(rs4.getInt(1)>transactionId)
								//transactionId=rs4.getInt(1);
						
						 transaction.setTransactionId(rs4.getInt(1));
					}


				}
				count++;
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if(count!=0)
		return transaction;
		else 
			return null;
	}

	@Override
	public double deposit(int acc, double amt) {
		int i=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "MANISHA", "oracle123");
			PreparedStatement ps1 = con.prepareStatement("select * from customer_details where account_no=?");
			ps1.setInt(1, acc);
			ResultSet rs = ps1.executeQuery();
			if (rs.next()) {
				
				bal=rs.getDouble(10);

			} 
			bal = (int) (amt + bal);
			
			PreparedStatement ps = con.prepareStatement("update customer_details set balance=? where account_no=?");
			ps.setDouble(1, bal);
			ps.setInt(2, acc);
			 i = ps.executeUpdate();
			// System.out.println(customer.getBalance());
			// System.out.println(adar);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}if (i == 1) {

			System.out.println("done");
			return bal;
		} else {

			System.out.println("error");
			return 0;
		}
	}

	@Override
	public double withdraw(int acc, double amt) {
		int i=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "MANISHA", "oracle123");
			PreparedStatement ps1 = con.prepareStatement("select * from customer_details where account_no=?");
			ps1.setInt(1, acc);
			ResultSet rs = ps1.executeQuery();
			if (rs.next()) {
				
				bal=rs.getDouble(10);

			} 
			if (amt < bal) {
				bal = (int) (bal - amt);
				PreparedStatement ps = con.prepareStatement("update customer_details set balance=? where account_no=?");
				ps.setDouble(1, bal);
				ps.setInt(2, acc);
				 i = ps.executeUpdate();
				System.out.println();

			} 
				
			 else {
				System.out.println("insufficient amount to withdraw");
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		}
			
		if (i == 1) {

			System.out.println("done");
			return bal;
		} else {

			System.out.println("error");
			return 0;
		}
	}

	@Override
	public double balance(int acc) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "MANISHA", "oracle123");
			PreparedStatement ps1 = con.prepareStatement("select * from customer_details where account_no=?");
			ps1.setInt(1, acc);
			ResultSet rs = ps1.executeQuery();
			if (rs.next()) {
				
				bal=rs.getDouble(10);

			} 
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return bal;
		

			
	}
}

