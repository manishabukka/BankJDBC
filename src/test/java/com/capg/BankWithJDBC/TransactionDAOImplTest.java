package com.capg.BankWithJDBC;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.capg.BankWithJDBC.TransactionDao.TransactionDAOImpl;
import com.capg.BankWithJDBC.beans.Customer;
import com.capg.BankWithJDBC.beans.Transaction;

class TransactionDAOImplTest {

	static Transaction transaction= new Transaction();
	static  Customer customer=new Customer();
	static TransactionDAOImpl t=new TransactionDAOImpl();

		//@Test
		void testDeposit() {
			assertEquals(11900, t.deposit(1002,2000));
		}

		//@Test
		void testWithDraw() {

			assertEquals(11000, t.withdraw(1002,900));
		}
		//@Test
		void testWithDraw1() {

			assertEquals(0, t.withdraw(1002,1000000));
		}

		//@Test
		void testBalance() {
			assertEquals(10100, t.balance(1002));
		}
		@Test
		void testFundTransfer() {
			transaction.setAmountTransfered(100);
			transaction.setFromAccountNo(1002);
			transaction.setToAccountNo(1001);
			assertEquals(transaction, t.fundTransfer(transaction));
		}


}
