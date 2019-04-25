package com.capg.BankWithJDBC.Exceptions;

public class WithdrawException extends Exception {
	public WithdrawException()
	{
		System.err.println("Withdraw failed");
	}

}
