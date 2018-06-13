package uml.extraction;

public final class ExtractAssociation extends ExtractListDec {

	ExtractAssociation() {

		super(2);

	}

	ExtractAssociation(String line) {

		super(line, 2);
	}

	@Override
	public String extract(UML_Element element) {

		switch (element) {

		case IDENTIFIER:
			return this.extractIdentifier();
		case ROLES:
			return this.extractRoles();
		default:
			throw new IllegalArgumentException();

		}
	}

	@Override
	protected String getElementName() {

		return "RELATION";
	}

	private String extractRoles() {

		return this.isOnlyElementInLine("ROLES");
	}

}
