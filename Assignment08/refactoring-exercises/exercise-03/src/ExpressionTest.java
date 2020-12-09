import org.junit.Test;
import static org.junit.Assert.*;

public class ExpressionTest {

	@Test
	public void testConstant() {
		Expression e = new Constant(-43);
		assertEquals(e.evaluate(), -43);
	}
	@Test
	public void testAddition() {
		Expression e = new Add(new Constant(100), new Constant(-100));
		assertEquals(e.evaluate(), 0);
	}
	@Test
	public void testSubtraction() {
		Expression e = new Subtract(new Constant(100), new Constant(-100));
		assertEquals(e.evaluate(), 200);
	}
	@Test
	public void testMultiplication() {
		Expression e = new Multiply(new Constant(100), new Constant(-100));
		assertEquals(e.evaluate(), -10000);
	}
	@Test
	public void testDivision() {
		Expression e = new Divide(new Constant(100), new Constant(-100));
		assertEquals(e.evaluate(), -1);
	}
	@Test
	public void testComplexExpression() {
		// 1+2-3*4/5
		Expression e = new Subtract(
				new Add(
						new Constant(1), new Constant(2)
				),
				new Divide(
						new Multiply(
								new Constant(3), new Constant(4)
						),
						new Constant(5)
				)
		);
		assertEquals(e.evaluate(), 1);
	}

}
