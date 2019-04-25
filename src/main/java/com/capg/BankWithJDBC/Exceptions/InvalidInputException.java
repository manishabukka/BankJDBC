package com.capg.BankWithJDBC.Exceptions;

public class InvalidInputException  extends Exception{
	public InvalidInputException()
	{
		System.err.println("Invalid Input Credentials...please check and try again ");
	}

}
