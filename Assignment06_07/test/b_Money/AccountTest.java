package b_Money;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank SweBank;
	Account testAccount;

	/**
	 * Setting up currencies and creating accounts
	 * @throws Exception in case it tries to create already created account
	 */
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK",0.20);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea",DKK);
		SweBank.openAccount("Alice");
		Nordea.openAccount("Ulfric Stormcloak");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));
	}

	/**
	 * tests addTimedPayment, removeTimedPayment method and the TimePayment class
	 * timePayments are added to the testAccount
	 * checked if they are added correctly and they exist
	 * running 10 ticks
	 * checking if the money on all 3 accounts is reduced or increased as expected
	 * @throws AccountDoesNotExistException if a bank does not have an account requested
	 */
	@Test
	public void testAddRemoveTimedPayment() throws AccountDoesNotExistException{
		testAccount.addTimedPayment("AliceTimePayment",5,0, new Money(15000, SEK),SweBank,"Alice");
		testAccount.addTimedPayment("ForTheSkyrim",10,0, new Money(15000, SEK),Nordea,"Ulfric Stormcloak");

		assertTrue("should already exist",testAccount.timedPaymentExists("AliceTimePayment"));
		assertTrue("should already exist",testAccount.timedPaymentExists("ForTheSkyrim"));

		for (int i = 0; i < 10; i++) {
			testAccount.tick();
		}

		assertEquals(Integer.valueOf(9955000),testAccount.getBalance().getAmount());
		assertEquals(Integer.valueOf(30000),SweBank.getBalance("Alice"));
		assertEquals(Integer.valueOf(11250), Nordea.getBalance("Ulfric Stormcloak"));

	}

	/**
	 * Testing withdraw and deposit methods
	 * withdrawing money from 3 existing accounts
	 * checking if the amount is corresponding to what is should be
	 * depositing the same amount of money as we it was withdrawn
	 * checking if the amount on these accounts is the same as it was before the withdrawal operation
	 * @throws AccountDoesNotExistException if a bank does not have an account requested
	 */
	@Test
	public void testDepositWithdraw() throws AccountDoesNotExistException {
		testAccount.withdraw(new Money(10000, SEK));
		SweBank.withdraw("Alice",new Money(10000,SEK));
		Nordea.withdraw("Ulfric Stormcloak", new Money(20000,DKK));

		assertEquals(Integer.valueOf(9990000),testAccount.getBalance().getAmount());
		assertEquals(Integer.valueOf(-10000),SweBank.getBalance("Alice"));
		assertEquals(Integer.valueOf(-20000),Nordea.getBalance("Ulfric Stormcloak"));

		testAccount.deposit(new Money(10000, SEK));
		SweBank.deposit("Alice",new Money(10000,SEK));
		Nordea.deposit("Ulfric Stormcloak", new Money(20000,DKK));

		assertEquals(Integer.valueOf(10000000),testAccount.getBalance().getAmount());
		assertEquals(Integer.valueOf(0),Nordea.getBalance("Ulfric Stormcloak"));
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Alice"));
	}

	/**
	 * testing getBalance method
	 * checking if each account has the amount of money that it has been created with
	 * @throws AccountDoesNotExistException if a bank does not have an account that requested
	 */
	@Test
	public void testGetBalance() throws AccountDoesNotExistException{
		assertEquals(Integer.valueOf(10000000),testAccount.getBalance().getAmount());
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Alice"));
		assertEquals(Integer.valueOf(0),Nordea.getBalance("Ulfric Stormcloak"));
	}
}
