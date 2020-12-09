import java.util.*;

/**
 * Bad smells:
 * 	- Long Method - Extract Method
 * 	- Duplicated Code - Extract Method, Change Method Signature
 * 	- Data class - Encapsulate Fields
 */
public class Configuration {
	private int interval;

	private int duration;

	private int departure;

	public void load(Properties props) throws ConfigurationException {
		setInterval(props);

		setDuration(props);

		setDeparture(props);
	}

	private void setDeparture(Properties props) throws ConfigurationException {
		String valueString;
		int value;
		valueString = props.getProperty("departure");
		checkCondition(valueString == null, "departure offset");
		value = Integer.parseInt(valueString);
		checkCondition(value <= 0, "departure > 0");
		checkCondition((value % getInterval()) != 0, "departure % interval");
		departure = value;
	}

	private void setDuration(Properties props) throws ConfigurationException {
		String valueString;
		int value;
		valueString = props.getProperty("duration");
		checkCondition(valueString == null, "duration");
		value = Integer.parseInt(valueString);
		checkCondition(value <= 0, "duration > 0");
		checkCondition((value % getInterval()) != 0, "duration % interval");
		duration = value;
	}

	private void setInterval(Properties props) throws ConfigurationException {
		String valueString;
		int value;
		valueString = props.getProperty("interval");
		checkCondition(valueString == null, "monitor interval");
		value = Integer.parseInt(valueString);
		checkCondition(value <= 0, "monitor interval > 0");
		interval = value;
	}

	private void checkCondition(boolean b, String s) throws ConfigurationException {
		if (b) {
			throw new ConfigurationException(s);
		}
	}

	public int getInterval() {
		return interval;
	}

	public int getDuration() {
		return duration;
	}

	public int getDeparture() {
		return departure;
	}
}
