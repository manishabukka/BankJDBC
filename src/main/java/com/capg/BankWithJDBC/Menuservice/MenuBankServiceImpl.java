package com.capg.BankWithJDBC.Menuservice;

import com.capg.BankWithJDBC.MenuDao.IMenuDAO;
import com.capg.BankWithJDBC.MenuDao.MenuDAOImpl;
import com.capg.BankWithJDBC.TransactionDao.ITransactionDAO;
import com.capg.BankWithJDBC.TransactionDao.TransactionDAOImpl;
import com.capg.BankWithJDBC.beans.Customer;
import com.capg.BankWithJDBC.beans.Transaction;

public class MenuBankServiceImpl implements IMenuBankService {
		
		Customer customer=new Customer();
		IMenuDAO iMenuDAO = new MenuDAOImpl();
		ITransactionDAO iTransactionDAO=new TransactionDAOImpl();
		double a=0;

		public boolean emailValidation(String s) {
			if (s.contains("@")) {
				return true;
			} else {
				return false;
			}
		}

		public boolean aadharValidation(String a) {

			if (a.length() == 12)
				return true;
			else
				return false;
		}
		public boolean panValidation(String b) {
			if (b.length() == 10)
				return true;
			else
				return false;
		}

		

		@Override
		public boolean Login(int accountNumber1, String password) {
			return iMenuDAO.Login(accountNumber1, password);
			
		}

		@Override
		public int registration(Customer customer2) {
			return iMenuDAO.registration(customer2);
			
		}
		

		

		
		

		

		



	} 

