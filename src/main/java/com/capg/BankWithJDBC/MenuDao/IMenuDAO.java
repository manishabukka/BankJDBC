package com.capg.BankWithJDBC.MenuDao;

import com.capg.BankWithJDBC.beans.Customer;

public interface IMenuDAO {

	
	boolean Login(int accountNumber1, String password);

	int registration(Customer customer2);



} 