package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;

	/**
	 * Setting up currencies with exchange rates
	 */
	@Before
	public void setUp(){
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		NOK = new Currency("NOK",1.25);
	}

	/**
	 * testing getName method
	 * checking whether it returns the correct name of the currency
	 */
	@Test
	public void testGetName() {
		assertEquals("SEK",SEK.getName());
		assertEquals("DKK",DKK.getName());
		assertEquals("EUR",EUR.getName());
		assertEquals("NOK",NOK.getName());
	}

	/**
	 * testing getRate methot
	 * checking whether it returns the correct rate of the currency
	 */
	@Test
	public void testGetRate() {
		assertEquals(0.15,SEK.getRate(),0.00001);
		assertEquals(0.20,DKK.getRate(),0.00001);
		assertEquals(1.5,EUR.getRate(),0.00001);
		assertEquals(1.25,NOK.getRate(),0.00001);
	}

	/**
	 * testing the setRate method
	 * setting new rates to the currencies
	 * checking if the returned values correspond to the new rates
	 */
	@Test
	public void testSetRate() {
		Double newRate = 0.275;
		Double change = 0.123;
		SEK.setRate(newRate);
		DKK.setRate(newRate + change);
		EUR.setRate(newRate - change);
		NOK.setRate(newRate*change);

		assertEquals(newRate, SEK.getRate(),0.00001);
		assertEquals(newRate + change, DKK.getRate(),0.00001);
		assertEquals(newRate - change, EUR.getRate(),0.00001);
		assertEquals(newRate * change, NOK.getRate(),0.00001);
	}

	/**
	 * testing the universalValue method
	 * checking whether the returned value correspond to the universal value of 100 in some specific currency
	 * it is represented in the following way:
	 * 	last 2 digits - decimal part
	 * 	everything else - whole part
	 */
	@Test
	public void testUniversalValue() {
		Integer univAmount = 100;
		assertEquals(Integer.valueOf(1500),SEK.universalValue(univAmount));
		assertEquals(Integer.valueOf(2000),DKK.universalValue(univAmount));
		assertEquals(Integer.valueOf(15000),EUR.universalValue(univAmount));
		assertEquals(Integer.valueOf(12500),NOK.universalValue(univAmount));
	}

	/**
	 * testing valueInThisCurrency method
	 * checking whether the value inserted in the method in the form described above corresponds to the value of
	 * the currency object we invoked this method on
	 */
	@Test
	public void testValueInThisCurrency() {
		Integer checkAmount = 10000;
		assertEquals(Integer.valueOf(13333),SEK.valueInThisCurrency(checkAmount,DKK));
		assertEquals(Integer.valueOf(75000),DKK.valueInThisCurrency(checkAmount,EUR));
		assertEquals(Integer.valueOf(8333),EUR.valueInThisCurrency(checkAmount,NOK));
		assertEquals(Integer.valueOf(1200),NOK.valueInThisCurrency(checkAmount,SEK));
	}

}
