package com.capg.BankWithJDBC.TransactionService;

import com.capg.BankWithJDBC.beans.Transaction;

public interface ITransactionBankService {

	double deposit(int accountNumber1, double amount);

	double withdraw(int accountNumber1, double amount);

	double showBalance(int accountNumber1);

	Transaction fundTransfer(Transaction transaction);

}
