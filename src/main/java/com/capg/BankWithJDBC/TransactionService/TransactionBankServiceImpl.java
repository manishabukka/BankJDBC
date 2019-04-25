package com.capg.BankWithJDBC.TransactionService;

import com.capg.BankWithJDBC.TransactionDao.ITransactionDAO;
import com.capg.BankWithJDBC.TransactionDao.TransactionDAOImpl;
import com.capg.BankWithJDBC.beans.Transaction;

public class TransactionBankServiceImpl implements ITransactionBankService {
	ITransactionDAO iTransactionDAO =new TransactionDAOImpl();

	@Override
	public double deposit(int accountNumber1, double amount) {
		return iTransactionDAO.deposit(accountNumber1, amount);
		
	}

	@Override
	public double withdraw(int accountNumber1, double amount) {
		return iTransactionDAO.withdraw(accountNumber1, amount);
		
	}

	@Override
	public double showBalance(int accountNumber1) {
		return iTransactionDAO.balance(accountNumber1);
		
	}

	@Override
	public Transaction fundTransfer(Transaction transaction) {
		return iTransactionDAO.fundTransfer(transaction);
		
	}

}
