package com.capg.BankWithJDBC.Exceptions;

public class InvalidPanNoException extends Exception {
	public InvalidPanNoException()
	{
		System.err.println("pancard number must have 10 characters ");
	}

}
