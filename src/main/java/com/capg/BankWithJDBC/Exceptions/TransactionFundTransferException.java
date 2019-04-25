package com.capg.BankWithJDBC.Exceptions;

public class TransactionFundTransferException extends Exception {
	public TransactionFundTransferException()
	{
		System.err.println("Fund Transfer failed");
	}

}
