import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

/**
 * Bad smells:
 *  - Long Methods - Extract Method
 *  - Speculative Generality - Rename Method
 */
public class PersonTest {
	private Person bobSmith;
	private Person jennyJJones;

	@Before
	public void setUp() {
		bobSmith = new Person("Smith", "Bob", null);
		jennyJJones = new Person("Jones", "Jenny", "J");
	}

	@Test
	public void testToString(){
		assertEquals("Smith, Bob", bobSmith.toString());
		assertEquals("Jones, Jenny J", jennyJJones.toString());
	}

	@Test
	public void displayTest() throws IOException {
		StringWriter out;

		out = new StringWriter();
		bobSmith.display(out);
		assertEquals("Smith, Bob", out.toString());

		out = new StringWriter();
		jennyJJones.display(out);
		assertEquals("Jones, Jenny J", out.toString());
	}

	@Test
	public void formatTest() {
		StringWriter out;

		assertEquals("Smith, Bob", bobSmith.format());
		assertEquals("Jones, Jenny J", jennyJJones.format());
	}

	@Test
	public void printTest() throws IOException {
		StringWriter out = new StringWriter();
		bobSmith.print(out);
		assertEquals("Bob Smith", out.toString());

		out = new StringWriter();
		jennyJJones.print(out);
		assertEquals("Jenny J Jones", out.toString());
	}


}
