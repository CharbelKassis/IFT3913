package uml.extraction;

public final class ExtractGeneralization extends ExtractListDec {

	
	ExtractGeneralization() {
		
		super(2);
	}
	
	ExtractGeneralization(String line) {
		
		super(line,2);
	}
	
	@Override
	public String extract(UML_Element element) {

		String string = null;

		switch (element) {

		case IDENTIFIER:
			string = this.extractIdentifier();
			break;
			
		default:
			throw new IllegalArgumentException();
		}
		return string;
	}

	
	@Override
	protected String getElementName() {
		
		return "GENERALIZATION";
	}

}
