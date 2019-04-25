package com.capg.BankWithJDBC.Exceptions;

public class InvalidaadharException extends Exception {
	public InvalidaadharException()
	{
		System.err.println("aadhar card must have 12 characters");
	}

}
