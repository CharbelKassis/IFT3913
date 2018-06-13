package uml.extraction;

import uml.exception.UML_MissingSyntaxException;

public final class ExtractOperation extends ExtractAttribute {

	ExtractOperation() {

		super();
	}

	ExtractOperation(String line) {

		super(line);

	}

	@Override
	public String getIdentifier() {

		if (!this.checkParentheses())

			throw new UML_MissingSyntaxException("(", ")");

		String identifier = super.getIdentifier();

		if (identifier.contains("("))

			return identifier.substring(0, identifier.indexOf('('));

		return identifier;
	}

	private boolean checkParentheses() {

		this.textElements = this.removeWhitespace();

		/* verifie si la syntaxe est correcte */
		if (this.checkParentheses(textElements))

			return true;

		return false;

	}

	@Override
	protected boolean lengthCondition() {

		return this.textElements.length >= this.getNumberOfElements();
	}

	private boolean checkParentheses(String[] textElements) {

		char currentParenthese = '(';

		for (String element : textElements) {

			switch (currentParenthese) {
			/* verifier le premiere parenthese */
			case '(':

				if (element.contains("("))

					currentParenthese = ')';
				/* verifier la seconde parenthese */
			case ')':

				if (element.contains(")"))

					currentParenthese = '0';

				break;
			/* verifier s'il y a dautres parenthese apres */
			case '0':

				if (element.contains("(") || element.contains(")"))

					return false;
			}

		}
		/*
		 * si currentParenthese vaut '0' alors on a trouve les deux parentheses
		 * et on est sorti de la boucle, donc il n'y a aucun probleme : la
		 * syntaxe est correcte.
		 */
		return currentParenthese == '0';

	}

}
