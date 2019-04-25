package com.capg.BankWithJDBC.MenuDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.capg.BankWithJDBC.beans.Customer;


public class MenuDAOImpl implements IMenuDAO {
	Scanner sc = new Scanner(System.in);
	Customer customer=new Customer();
	int acc=0,accId=0;

	public int registration(Customer customer) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "MANISHA", "oracle123");
			PreparedStatement ps = con.prepareStatement("insert into customer_details values(acc_seq.nextval,?,?,?,?,?,?,?,?,?)");
			
			ps.setString(1,customer.getFirstName());
			ps.setString(2,customer.getLastName());
			ps.setString(3,customer.getEmailId());
			ps.setString(4,customer.getPassword());
			ps.setString(5,customer.getPancardNo());
			ps.setString(6,customer.getAadharNo());
			ps.setString(7,customer.getAddress());
			ps.setInt(8,customer.getMobileNo());
			ps.setDouble(9, customer.getBalance());

			int i = ps.executeUpdate();



			if (i == 1) {
				System.out.println("done");

			} else {
				System.out.println("could not insert data");
			}
			PreparedStatement ps1 = con.prepareStatement("select * from customer_details");
			
			ResultSet rs = ps1.executeQuery();
			while(rs.next()) {
				
				if(rs.getInt(1)>acc)
					acc=rs.getInt(1);
				



			} 

			con.close();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return acc;
	}

	

	@Override
	public boolean Login(int account, String password) {
		int i=0;
		customer.setAccountNo(account);
		customer.setPassword(password);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "MANISHA", "oracle123");

			PreparedStatement ps = con.prepareStatement("select * from customer_details where account_no=? and password=?");
			ps.setInt(1, customer.getAccountNo());
			ps.setString(2, customer.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("login successful..."+rs.getString(2));
				customer.setAccountNo(rs.getInt(1));
				customer.setBalance(rs.getInt(10));
				i++;



			} else {
				System.out.println("invalid details");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} if(i==0)return false;
		else return true;
	}

	

	

	
} 
