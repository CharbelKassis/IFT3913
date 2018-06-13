package uml.extraction;

import uml.exception.UML_MissingSyntaxException;
import uml.exception.UML_SyntaxLengthException;

public abstract class ExtractListDec extends Extractor {

	protected ExtractListDec(int expectedNumberOfElements) {

		super(expectedNumberOfElements);
	}

	protected ExtractListDec(String line, int expectedNumberOfElements) {

		super(line, expectedNumberOfElements);

	}

	protected String isOnlyElementInLine(String string) {

		String[] textElements = this.removeWhitespace();

		if (textElements.length != 1)

			throw new UML_SyntaxLengthException(1, textElements.length, this.getLine());

		if (textElements[0].equals(string))

			return null;

		throw new UML_MissingSyntaxException(string);

	}

	protected String extractIdentifier() {

		String[] textElements = this.removeWhitespace();

		if (textElements.length != this.getNumberOfElements())

			throw new UML_SyntaxLengthException(this.getNumberOfElements(), textElements.length, this.getLine());

		if (!textElements[0].equals(this.getElementName()))

			throw new UML_MissingSyntaxException(this.getElementName());

		return textElements[textElements.length - 1];
	}

	protected abstract String getElementName();
}
