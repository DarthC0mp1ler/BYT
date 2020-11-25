package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100, DKK123;

	/**
	 * setting up currencies and the money instances
	 */
	@Before
	public void setUp(){
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);

		DKK123 = new Money(12340,DKK);
	}

	/**
	 * testing the getAmount method
	 * checking whether the value returned corresponds to the one the object was initialized with
	 */
	@Test
	public void testGetAmount() {
		assertEquals(Integer.valueOf(0),SEK0.getAmount());
		assertEquals(Integer.valueOf(10000),SEK100.getAmount());
		assertEquals(Integer.valueOf(20000),SEK200.getAmount());
		assertEquals(Integer.valueOf(-10000),SEKn100.getAmount());

		assertEquals(Integer.valueOf(0),EUR0.getAmount());
		assertEquals(Integer.valueOf(1000),EUR10.getAmount());
		assertEquals(Integer.valueOf(2000),EUR20.getAmount());

		assertEquals(Integer.valueOf(12340),DKK123.getAmount());
	}

	/**
	 * testing the getCurrency method
	 * checking wither the currency equals to the one we created the object with
	 */
	@Test
	public void testGetCurrency() {
		assertEquals(SEK,SEK0.getCurrency());
		assertEquals(SEK,SEK100.getCurrency());
		assertEquals(SEK,SEK200.getCurrency());
		assertEquals(SEK,SEKn100.getCurrency());

		assertEquals(EUR, EUR10.getCurrency());
		assertEquals(EUR,EUR0.getCurrency());
		assertEquals(EUR,EUR20.getCurrency());

		assertEquals(DKK, DKK123.getCurrency());
	}

	/**
	 * testing toString method
	 * checking whether the string returned corresponds to the one it should return
	 */
	@Test
	public void testToString() {
		assertEquals("200.0 SEK",SEK200.toString());
		assertEquals("100.0 SEK",SEK100.toString());
		assertEquals("0.0 SEK",SEK0.toString());
		assertEquals("-100.0 SEK",SEKn100.toString());

		assertEquals("10.0 EUR", EUR10.toString());
		assertEquals("20.0 EUR", EUR20.toString());
		assertEquals("0.0 EUR", EUR0.toString());

		assertEquals("123.4 DKK", DKK123.toString());
	}

	/**
	 * testing the universalValue method
	 * checking whether the universal value corresponds to the amount of this money in the universal value
	 */
	@Test
	public void testUniversalValue() {
		assertEquals(Integer.valueOf(300000),SEK200.universalValue());
		assertEquals(Integer.valueOf(150000),SEK100.universalValue());
		assertEquals(Integer.valueOf(0),SEK0.universalValue());
		assertEquals(Integer.valueOf(-150000),SEKn100.universalValue());

		assertEquals(Integer.valueOf(0),EUR0.universalValue());
		assertEquals(Integer.valueOf(150000),EUR10.universalValue());
		assertEquals(Integer.valueOf(300000),EUR20.universalValue());

		assertEquals(Integer.valueOf(246800),DKK123.universalValue());
	}

	/**
	 * testing the equals method
	 */
	@Test
	public void testEqualsMoney() {
		assertTrue("should be equal",SEK200.equals(EUR20));
		assertTrue("should be equal",SEK100.equals(EUR10));
		assertTrue("should be equal",EUR0.equals(SEK0));

		assertFalse("should not be equal",SEK100.equals(DKK123));
	}

	/**
	 * testing add method
	 * checking whether the addition of money amounts is correct
	 */
	@Test
	public void testAdd() {
		Money res1 = SEK200.add(EUR20);
		Money res2 = SEK100.add(EUR10);
		Money res3 = SEK0.add(SEK0);
		Money res4 = DKK123.add(SEK0.add(EUR0));

		assertEquals(Integer.valueOf(40000),res1.getAmount());
		assertEquals(Integer.valueOf(20000),res2.getAmount());
		assertEquals(Integer.valueOf(0),res3.getAmount());
		assertEquals(Integer.valueOf(12340),res4.getAmount());
	}

	/**
	 * testing sub method
	 * checking whether the subtraction of money amount is correct
	 */
	@Test
	public void testSub() {
		Money res1 = SEK200.sub(EUR20);
		Money res2 = SEK100.sub(EUR10);
		Money res3 = SEK0.sub(SEK0);
		Money res4 = DKK123.sub(SEK0.add(EUR0));

		assertEquals(Integer.valueOf(0),res1.getAmount());
		assertEquals(Integer.valueOf(0),res2.getAmount());
		assertEquals(Integer.valueOf(0),res3.getAmount());
		assertEquals(Integer.valueOf(12340),res4.getAmount());
	}

	/**
	 * testing isZero method
	 * checking if the returned value correctly tells whether the amount is zero
	 */
	@Test
	public void testIsZero() {
		String tstr = "should be zero";
		String fstr = "should not be zero";
		assertFalse(fstr,SEK100.isZero());
		assertFalse(fstr,SEK200.isZero());
		assertFalse(fstr,SEKn100.isZero());
		assertTrue(tstr,SEK0.isZero());

		assertFalse(fstr,EUR10.isZero());
		assertFalse(fstr,EUR20.isZero());
		assertTrue(tstr,EUR0.isZero());

		assertFalse(fstr,DKK123.isZero());
	}

	/**
	 * testing the negate method
	 * checking whether the negation of amount is correct
	 */
	@Test
	public void testNegate() {
		assertEquals(Integer.valueOf(-10000),SEK100.negate().getAmount());
		assertEquals(Integer.valueOf(-20000),SEK200.negate().getAmount());
		assertEquals(Integer.valueOf(10000),SEKn100.negate().getAmount());
		assertEquals(Integer.valueOf(-0),SEK0.negate().getAmount());

		assertEquals(Integer.valueOf(-1000),EUR10.negate().getAmount());
		assertEquals(Integer.valueOf(-2000),EUR20.negate().getAmount());
		assertEquals(Integer.valueOf(-0),EUR0.negate().getAmount());

		assertEquals(Integer.valueOf(-12340),DKK123.negate().getAmount());
	}

	/**
	 * testing compareTo method
	 * checking if it correctly compares the amounts of money
	 */
	@Test
	public void testCompareTo() {
		assertEquals("should be equal", 0, SEK100.compareTo(EUR10));
		assertEquals("should be equal", 0, SEK200.compareTo(EUR20));

		assertTrue("should be more", 0 < SEK200.compareTo(EUR10));
		assertTrue("should be more", 0 < SEK100.compareTo(EUR0));

		assertTrue("should be less", 0 > SEK100.compareTo(DKK123));
		assertTrue("should be less", 0 > SEKn100.compareTo(DKK123));
		assertTrue("should be less", 0 > EUR0.compareTo(DKK123));

	}
}
