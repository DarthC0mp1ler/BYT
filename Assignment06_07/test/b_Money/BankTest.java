package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;

	/**
	 * Setting up currencies, banks and opening few accounts
	 */
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	/**
	 * Testing the getName method
	 * checking if it returns the correct name of the bank
	 */
	@Test
	public void testGetName() {
		assertEquals("SweBank",SweBank.getName());
		assertEquals("Nordea",Nordea.getName());
		assertEquals("DanskeBank",DanskeBank.getName());
	}

	/**
	 * testing getCurrency method
	 * checking whether it returns the correct currency of this bank
	 */
	@Test
	public void testGetCurrency() {
		assertEquals(SEK,SweBank.getCurrency());
		assertEquals(SEK,Nordea.getCurrency());
		assertEquals(DKK,DanskeBank.getCurrency());
	}

	/**
	 * Testing openAccount method
	 * it should throw AccountExistsException as an account is to be created with existing name
	 * @throws AccountExistsException if the account with the specific name already exists
	 */
	@Test(expected=AccountExistsException.class)
	public void testOpenAccount() throws AccountExistsException{
		SweBank.openAccount("Bob");
		Nordea.openAccount("Isolda");
		DanskeBank.openAccount("Ulfric Stormcloak");
	}

	/**
	 * testing the deposit method
	 * depositing the money to the banks in different currencies
	 * checking if bank accounts have the deposited amount of money
	 * @throws AccountDoesNotExistException if the account referred to does not exist
	 */
	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Bob",new Money(20000,SEK));
		SweBank.deposit("Ulrika",new Money(10000,DKK));
		Nordea.deposit("Bob", new Money(13000,SEK));
		DanskeBank.deposit("Gertrud",new Money(5050,SEK));

		assertEquals(Integer.valueOf(20000),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(13333),SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(13000),Nordea.getBalance("Bob"));
		assertEquals(Integer.valueOf(3787),DanskeBank.getBalance("Gertrud"));

	}


	/**
	 * testing the withdraw method
	 * invoking the testDeposit method
	 * withdrawing the money that was deposited
	 * checking whether the amount of money equals to the one before the deposition
	 * @throws AccountDoesNotExistException if the account referred  to does not exist
	 */
	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		testDeposit();

		SweBank.withdraw("Bob",new Money(20000,SEK));
		SweBank.withdraw("Ulrika",new Money(10000,DKK));
		Nordea.withdraw("Bob", new Money(13000,SEK));
		DanskeBank.withdraw("Gertrud",new Money(5050,SEK));

		assertEquals(Integer.valueOf(0),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(0),Nordea.getBalance("Bob"));
		assertEquals(Integer.valueOf(0),DanskeBank.getBalance("Gertrud"));
	}

	/**
	 * checking the getBalance method
	 * invoking the testWithdraw method as the getBalance is used there
	 * @throws AccountDoesNotExistException
	 */
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		testWithdraw();
	}

	/**
	 * testing the testTransfer method
	 * transferring money from one account to another
	 * checking if the amount of money in both accounts changed correspondingly
	 * @throws AccountDoesNotExistException if account referred to does not exist
	 */
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		// transfer from one bank to another
		SweBank.transfer("Bob",Nordea,"Bob",new Money(20000,SEK));
		assertEquals(Integer.valueOf(-20000),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(20000),Nordea.getBalance("Bob"));

		Nordea.transfer("Bob",SweBank,"Bob",new Money(20000,SEK));
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(0),Nordea.getBalance("Bob"));

		SweBank.transfer("Ulrika",DanskeBank,"Gertrud",new Money(20000,SEK));
		assertEquals(Integer.valueOf(-20000),SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(15000),DanskeBank.getBalance("Gertrud"));

		DanskeBank.transfer("Gertrud",SweBank,"Ulrika",new Money(15000,DKK));
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(0),DanskeBank.getBalance("Gertrud"));

		//transfer within one bank
		SweBank.transfer("Bob","Ulrika",new Money(20000,SEK));
		assertEquals(Integer.valueOf(-20000),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(20000),SweBank.getBalance("Ulrika"));

		SweBank.transfer("Ulrika","Bob",new Money(20000,SEK));
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Ulrika"));

	}

	/**
	 * testing the addTimedPayment, removeTimedPayment and tick methods
	 * creating 2 time payments on 2 accounts
	 * running 14 ticks
	 * checking the amount of money on accounts
	 * disabling one timed payment
	 * running 5 more ticks
	 * checking the amount of money
	 * @throws AccountDoesNotExistException if the account referred to does not exist
	 */
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Bob","123fhf123",5,3,new Money(20000,SEK),SweBank,"Ulrika");
		Nordea.addTimedPayment("Bob","434fmfgg5",10,3,new Money(15000, DKK), DanskeBank, "Gertrud");


		for (int i = 0; i < 14; i++) {
			SweBank.tick();
			Nordea.tick();
		}

		assertEquals(Integer.valueOf(-40000),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(40000),SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(-20000),Nordea.getBalance("Bob"));
		assertEquals(Integer.valueOf(15000),DanskeBank.getBalance("Gertrud"));

		SweBank.removeTimedPayment("Bob","123fhf123");

		for (int i = 0; i < 5; i++) {
			SweBank.tick();
			Nordea.tick();
		}

		assertEquals(Integer.valueOf(-40000),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(40000),SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(-40000),Nordea.getBalance("Bob"));
		assertEquals(Integer.valueOf(30000),DanskeBank.getBalance("Gertrud"));
	}
}
