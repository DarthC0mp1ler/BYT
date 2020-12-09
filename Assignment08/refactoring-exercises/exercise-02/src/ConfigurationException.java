/**
 * Bad smells:
 * 	- Speculative Generality - Remove method
 */
public class ConfigurationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConfigurationException(String arg0) {
		super(arg0);
	}
}
