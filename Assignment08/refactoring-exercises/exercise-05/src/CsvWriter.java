/**
 * Bad Smells:
 *  - Divergent Change - Extract Class, Extract Superclass
 */
public class CsvWriter {

	private LineWriter lineWriter;

	public CsvWriter() {
		lineWriter = new LineWriter();
	}

	public void write(String[][] lines) {
		for (int i = 0; i < lines.length; i++)
			lineWriter.writeLine(lines[i]);
	}

}