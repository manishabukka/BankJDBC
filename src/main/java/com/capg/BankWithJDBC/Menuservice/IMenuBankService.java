package com.capg.BankWithJDBC.Menuservice;

import com.capg.BankWithJDBC.beans.Customer;
import com.capg.BankWithJDBC.beans.Transaction;

public interface IMenuBankService {
	Customer customer =new Customer();
Transaction transaction=new Transaction();


	 
	
	boolean Login(int accountNumber1, String password);




	int registration(Customer customer2); 


}  
