package uml.extraction;

import uml.exception.UML_MissingSyntaxException;
import uml.exception.UML_SyntaxLengthException;

public final class ExtractModel extends Extractor {

	ExtractModel() {

		super(2);
	}

	ExtractModel(String line) {

		super(line, 2);

	}

	@Override
	public String extract(UML_Element element) {

		String string = null;

		switch (element) {

		case IDENTIFIER:
			string = this.extractIdentifier();
			break;
		case LIST_DEC:
			string = this.extractListDesc();
			break;
		default:
			throw new IllegalArgumentException();
		}

		return string;
	}

	private String extractListDesc() {

		String[] textElements = this.removeWhitespace();

		switch (textElements[0]) {

		case "CLASS":
		case "RELATION":
		case "AGGREGATION":
		case "GENERALIZATION":
			return textElements[0];
		default:
			throw new UML_MissingSyntaxException("CLASS", "RELATION", "AGGREGATION", "GENERALIZATION");

		}

	}

	private String extractIdentifier() {

		String[] textElements = this.removeWhitespace();

		if (textElements.length != 2)

			throw new UML_SyntaxLengthException(2, textElements.length, this.getLine());

		if (!textElements[0].equals("MODEL"))

			throw new UML_MissingSyntaxException("MODEL");

		return textElements[1];
	}
}
