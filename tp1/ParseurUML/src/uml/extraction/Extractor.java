package uml.extraction;

public abstract class Extractor {

	private String line;
	private int expectedNumberOfElements;

	Extractor(int expectedNumberOfElements) {

		this.expectedNumberOfElements = expectedNumberOfElements;
	}

	Extractor(String line, int expectedNumberOfElements) {

		this.line = line;
		this.expectedNumberOfElements = expectedNumberOfElements;
	}

	public String getLine() {

		return this.line;
	}

	protected int getNumberOfElements() {

		return this.expectedNumberOfElements;
	}

	public abstract String extract(UML_Element element);

	public String[] removeWhitespace() {

		return this.line.trim().split("\\s+");

	}

	public static String[] removeWhitespace(String line) {

		return line.trim().split("\\s+");
	}

	public Extractor newLine(String line) {

		this.line = line;
		return this;
	}
	
	public static int countCharacters(String line, char character) {

		int count = 0;

		for (int i = 0; i < line.length(); i++)

			if (line.charAt(i) == character)

				count++;

		return count;
	}
}
