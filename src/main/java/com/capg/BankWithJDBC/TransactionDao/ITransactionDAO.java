package com.capg.BankWithJDBC.TransactionDao;

import com.capg.BankWithJDBC.beans.Transaction;

public interface ITransactionDAO {

	

	double deposit(int accountNumber1, double amount);

	double withdraw(int accountNumber1, double amount);

	double balance(int accountNumber1);

	Transaction fundTransfer(Transaction transaction);

} 
