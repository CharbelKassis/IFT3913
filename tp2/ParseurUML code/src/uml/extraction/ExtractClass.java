package uml.extraction;

public final class ExtractClass extends ExtractListDec {

	ExtractClass() {

		super(2);
	}

	ExtractClass(String line) {

		super(line, 2);
	}

	@Override
	public String extract(UML_Element element) {

		String string = null;

		switch (element) {

		case IDENTIFIER:
			string = this.extractIdentifier();
			break;
		case ATTRIBUTES:
			string = this.extractAttributes();
			break;
		case OPERATIONS:
			string = this.extractOperations();
			break;
		default:
			throw new IllegalArgumentException();
		}
		return string;
	}

	private String extractAttributes() {

		return this.isOnlyElementInLine("ATTRIBUTES");
	}

	private String extractOperations() {

		return this.isOnlyElementInLine("OPERATIONS");
	}

	@Override
	protected String getElementName() {

		return "CLASS";
	}

}
