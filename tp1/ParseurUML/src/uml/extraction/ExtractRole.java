package uml.extraction;

import static uml.extraction.UML_Element.*;

import uml.exception.UML_MissingSyntaxException;
import uml.exception.UML_SyntaxLengthException;

public final class ExtractRole extends ExtractAttribute {

	ExtractRole() {

		super();
		this.identifierPosition = 1;     /*{ "CLASS" , IDENTIFIER , MULTIPLICITY }
										 * est la forme d'un Role
										 */

	}

	ExtractRole(String line) {

		super(line);
		this.identifierPosition = 1; 
	}

	@Override
	public String extract(UML_Element element) {

		String multiplicity = null;

		switch (element) {

		case MULTIPLICITY:

			multiplicity = super.extract(TYPE);

			if (isValidMultiplicity(multiplicity))

				return multiplicity;

			else

				throw new UML_MissingSyntaxException("ONE", "MANY", "ONE_OR_MANY", "OPTIONALLY_ONE", "UNDEFINED");

		default:
			return super.extract(element);
		}

	}

	@Override
	protected String getIdentifier() {

		this.textElements = this.removeWhitespace();

		if (!this.lengthCondition())

			throw new UML_SyntaxLengthException(this.getNumberOfElements(), this.textElements.length,this.getLine());

		if (!this.textElements[0].equals("CLASS"))

			throw new UML_MissingSyntaxException("CLASS");

		return this.textElements[this.identifierPosition];
	}

	private static boolean isValidMultiplicity(String multiplicity) {

		switch (multiplicity) {

		case "ONE":
		case "MANY":
		case "ONE_OR_MANY":
		case "OPTIONALLY_ONE":
		case "UNDEFINED":
			return true;
		default:
			return false;
		}
	}

}
