package com.capg.BankWithJDBC.Exceptions;

public class InvalidEmailException extends Exception {
	public InvalidEmailException()
	{
		System.err.println("email must contain @ ");
	}

}
