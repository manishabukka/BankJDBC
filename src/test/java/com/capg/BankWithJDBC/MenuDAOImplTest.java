package com.capg.BankWithJDBC;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.capg.BankWithJDBC.MenuDao.MenuDAOImpl;
import com.capg.BankWithJDBC.beans.Customer;

class MenuDAOImplTest {

	static  Customer customer=new Customer();
	MenuDAOImpl m= new MenuDAOImpl();
	int acc=0;
	boolean result=true;
//@Test
public void testCustomerDetails() {
	customer.setFirstName("manishaaaaaa");
	customer.setLastName("bukka");
	customer.setEmailId("manisssha@gmail.com");
	customer.setPassword("123");
	customer.setPancardNo("1234567");
	customer.setAadharNo("123450089098");
	customer.setAddress("hudacolony");
	customer.setMobileNo(779494672);
	customer.setBalance(0);
	acc=m.registration(customer);
	assertEquals(1012, acc);
}
@Test
public void testLogin()
{
	
	assertEquals(true, m.Login(1002,"456"));
}
}
