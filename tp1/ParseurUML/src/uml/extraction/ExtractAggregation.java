package uml.extraction;

import uml.exception.UML_SyntaxLengthException;

public final class ExtractAggregation extends ExtractListDec {

	ExtractAggregation() {
		
		super(1);
		
	}
	
	ExtractAggregation(String line) {
		
		super(line,1);
	}

	@Override
	protected String getElementName() {
		
		return "AGGREGATION";
	}

	@Override
	public String extract(UML_Element element) {
		
		String[] elements = this.removeWhitespace();
		
		if(elements.length != this.getNumberOfElements())
			
			throw new UML_SyntaxLengthException(this.getNumberOfElements(), elements.length, this.getLine());
		
		switch(element) {
			
			case IDENTIFIER: return this.extractIdentifier(); 
			case CONTAINER: return this.extractContainer();
			case PARTS: return this.extractParts();
			default: throw new IllegalArgumentException();
		}
	}
	
	private String extractContainer() {
		
		return this.isOnlyElementInLine("CONTAINER");
	}
	
	private String extractParts() {
		
		return this.isOnlyElementInLine("PARTS");
	}
	
	@Override
	protected String extractIdentifier() {
			
		super.extractIdentifier();
		
		return null;
	}

}
