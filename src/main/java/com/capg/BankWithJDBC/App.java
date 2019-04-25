package com.capg.BankWithJDBC;

import java.util.Scanner;

import com.capg.BankWithJDBC.Exceptions.InvalidEmailException;
import com.capg.BankWithJDBC.Exceptions.InvalidInputException;
import com.capg.BankWithJDBC.Exceptions.InvalidPanNoException;
import com.capg.BankWithJDBC.Exceptions.InvalidaadharException;
import com.capg.BankWithJDBC.Exceptions.TransactionFundTransferException;
import com.capg.BankWithJDBC.Exceptions.WithdrawException;
import com.capg.BankWithJDBC.Menuservice.MenuBankServiceImpl;
import com.capg.BankWithJDBC.TransactionService.ITransactionBankService;
import com.capg.BankWithJDBC.TransactionService.TransactionBankServiceImpl;
import com.capg.BankWithJDBC.Menuservice.IMenuBankService;
import com.capg.BankWithJDBC.beans.Customer;
import com.capg.BankWithJDBC.beans.Transaction;

public class App 
{
	static Scanner sc = new Scanner(System.in);

	 static IMenuBankService iMenuBankService = new MenuBankServiceImpl();
	 static MenuBankServiceImpl bankServiceImpl= new MenuBankServiceImpl();
	 static ITransactionBankService iTransactionBankService=new TransactionBankServiceImpl();
	 static  Customer customer=new Customer();
	 static  Customer customer1=new Customer();
	 static  Customer customer2=new Customer();
	 static Transaction transaction =new Transaction();
	


    public static void main( String[] args )
    {

		 boolean i=true;
		 double balance=0,amt=0;
		 int ch1=0,acc=0;
		 double bal=0;
		 System.out.println("===Welcome to Bank===");
		 do {
			 
       System.out.println("enter 1 for Registration 2 for login  3 for Exit");
       int ch= sc.nextInt();
       switch(ch)
       {
       case 1:System.out.println("--Please enter your details--");
    	   System.out.println("enter firstname");
    	   customer.setFirstName(sc.next());
    	   System.out.println("enter last name");
    	   customer.setLastName(sc.next());
    	   System.out.println("enter email address");
    	   String s= sc.next();
    	   i=bankServiceImpl.emailValidation(s);
    	   if(i==true)
    	   {
    		   customer.setEmailId(s);
    		   System.out.println("enter password");
        	   customer.setPassword(sc.next());
        	   System.out.println("enter pancard_no");
        	   String c=sc.next();
        	   i=bankServiceImpl.panValidation(c);
        	   if(i==true) {
        		   customer.setPancardNo(c);
        		   System.out.println("enter aadhar_no");
            	   String a=sc.next();
                   i=bankServiceImpl.aadharValidation(a);
            	   if(i==true) {
            	   customer.setAadharNo(a);
            	   System.out.println("enter address");
            	   customer.setAddress(sc.next());
            	   System.out.println("enter mobile no");
            	   customer.setMobileNo(sc.nextInt());
            	   customer.setBalance(0);

            	   acc=iMenuBankService.registration(customer);
            	   System.out.println("Your account number is "+acc);
            	   if(acc==0)
            	   {
            		   try {
        				throw new InvalidInputException();
        			} catch (InvalidInputException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
            	   }
            	   } else {
            		   try {
           				throw new InvalidaadharException();
           			} catch (InvalidaadharException e) {
           				// TODO Auto-generated catch block
           				e.printStackTrace();
           			}
               	   }
            	   }else {
            		   try {
       					throw new InvalidPanNoException();
       				} catch (InvalidPanNoException e) {
       					// TODO Auto-generated catch block
       					e.printStackTrace();
       				}
               	   }
    	   }
    	   else {
    		   try {
				throw new InvalidEmailException();
			} catch (InvalidEmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	   }
    	   
        	   
    	   
    	   
    	  

    	   
    	   break;
       case 2:
    	   System.out.println("Enter Account Number: ");
       	int accountNumber1=sc.nextInt();
       	System.out.println("Enter password: ");
       	String password=sc.next();
       	if(iMenuBankService.Login(accountNumber1, password)) {
   			
   			int choice=0;
       		while(choice!=1) {
       			System.out.println("Enter your choice: ");
       			System.out.println("1.Deposit \n 2.Withdraw \n 3.Show Balance \n 4.Fund Transfer \n 5.Return to Home Menu \n 6.Exit");

       			int ch2=sc.nextInt();
       			double amount=0;

    			switch(ch2) {
    			case 1 : System.out.println("Enter the amount: ");
    			amount=sc.nextDouble();
    			balance=iTransactionBankService.deposit(accountNumber1, amount);
    			System.out.println("Your balance is "+balance);
    			break;

    			case 2 : System.out.println("Enter the amount: ");
    			amount=sc.nextDouble();
    			balance=iTransactionBankService.withdraw(accountNumber1, amount);
    			if(customer!=null)
    			System.out.println("Your balance is "+balance);
    			else {
    				try {
    					throw new WithdrawException();
    				}catch(WithdrawException e) {

    				}
    			}
    				break;
    			
    				case 3 : balance=iTransactionBankService.showBalance(accountNumber1);
    			System.out.println("Your balance is "+balance);
    				break;
    				case 4 : System.out.println("Enter the account number to which you want to transfer: ");
    				transaction.setToAccountNo(sc.nextInt());
    				System.out.println("Enter the amount to which you want to transfer: ");
    				transaction.setAmountTransfered(sc.nextInt());
        			transaction.setFromAccountNo(accountNumber1);
        			transaction=iTransactionBankService.fundTransfer(transaction);

        			if(transaction!=null)
    				{
    				System.out.println("-----printing transaction----");
    				System.out.println("transaction id:"+transaction.getTransactionId());
    				System.out.println("from_account_no:"+transaction.getFromAccountNo());
    				System.out.println("to_account_no:"+transaction.getToAccountNo());
    				System.out.println("amount transfered:"+transaction.getAmountTransfered());
    				}
    				else
    				{
    					try {
							throw new TransactionFundTransferException();
						} catch (TransactionFundTransferException e) {
							e.printStackTrace();
						}
    				}
        				break;

        			case 5 : choice++;
        				break;
        				
        			case 6 : System.exit(0);
        				
        			default : System.out.println("Wrong Choice !!");
        			}
        		}
        	}
        	else
        	{
        		System.out.println("Wrong account number or password");
        		
        		try {
        			throw new InvalidInputException();
        		}catch(InvalidInputException e) {

        		}
        	}
        			break;


    	  case 3:System.exit(0);
    		break;
    	  }
		 }while(true);
    }

} 
