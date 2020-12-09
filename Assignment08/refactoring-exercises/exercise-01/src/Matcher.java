/**
 * Bad smells:
 * 	- Comments - Extract methods
 * 	- Speculative Generality - Remove method
 */
public class Matcher {

	public boolean match(int[] expected, int[] actual, int clipLimit, int delta) {

		clipTooLargeValues(actual, clipLimit);

		if (!areLengthsSame(expected, actual))
			return false;

		return !CheckEntriesWithinDelta(expected, actual, delta);
	}

	private boolean areLengthsSame(int[] expected, int[] actual) {
		return actual.length == expected.length;
	}

	private boolean CheckEntriesWithinDelta(int[] expected, int[] actual, int delta) {
		for (int i = 0; i < actual.length; i++)
			if (Math.abs(expected[i] - actual[i]) > delta)
				return true;
		return false;
	}

	private void clipTooLargeValues(int[] actual, int clipLimit) {
		for (int i = 0; i < actual.length; i++)
			if (actual[i] > clipLimit)
				actual[i] = clipLimit;
	}
}