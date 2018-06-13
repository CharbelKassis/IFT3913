package uml.extraction;

import uml.exception.UML_MissingSyntaxException;
import uml.exception.UML_SyntaxLengthException;

public class ExtractAttribute extends Extractor {

	protected String[] textElements;
	protected int identifierPosition;

	ExtractAttribute() {

		super(3);

	}

	ExtractAttribute(String line) {

		super(line, 3);
	}

	@Override
	public Extractor newLine(String line) {

		Extractor extractor = super.newLine(line);
		this.textElements = this.removeWhitespace();
		return extractor;
	}

	@Override
	public String extract(UML_Element element) {

		String string = null;
		this.textElements = this.removeWhitespace();
		this.verifyLength();

		switch (element) {

		case IDENTIFIER:
			string = this.getIdentifier();
			break;
		case TYPE:
			string = this.getType();
			break;
		default:
			throw new IllegalArgumentException();
		}

		return string;
	}

	private void verifyLength() {

		/*
		 * regarde si la condition du nombre d'elements dans la ligne extraite
		 * satisfait le critere
		 */
		if (!this.lengthCondition())

			/* il n'y a pas assez ou bien trop d'element extrait de la ligne */
			throw new UML_SyntaxLengthException(this.getNumberOfElements(), this.textElements.length,this.getLine());

	}

	protected String getIdentifier() {

		/* regarde si l'avant dernier element est un : */
		if (!this.textElements[this.textElements.length - 2].equals(":"))

			throw new UML_MissingSyntaxException(":");

		return this.textElements[this.identifierPosition];
	}

	protected boolean lengthCondition() {

		return this.textElements.length == this.getNumberOfElements();
	}

	protected String getType() {

		if (!this.isLast()) {

			String lastElement = this.textElements[this.textElements.length - 1];
			return lastElement.substring(0, lastElement.length() - 1);

		}

		else

			return this.textElements[this.textElements.length - 1];
	}

	/* verifier si c'est le dernier attribut (ou operation) de la liste */
	public boolean isLast() {

		String lastElement = this.textElements[this.textElements.length - 1];
		char lastCharacter = lastElement.charAt(lastElement.length() - 1);

		return lastCharacter != ',';
	}

}
