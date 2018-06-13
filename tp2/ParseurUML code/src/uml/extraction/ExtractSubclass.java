package uml.extraction;

import uml.exception.UML_ClassesNotExtractedException;
import uml.exception.UML_IllegalSyntaxException;
import uml.exception.UML_MissingSyntaxException;
import uml.exception.UML_SyntaxLengthException;

public final class ExtractSubclass extends Extractor {
	
	private int indexOfLastClass = 1; /* { "SUBCLASSES" , CLASS_IDENTIFIER_1+"," , CLASS IDENTIFIER_2+"," ... "," CLASS_IDENTIFIER_K} */ 
	private String[] classes;  		
	
	ExtractSubclass() {
		
		super(2);
		
	}

	ExtractSubclass(String line) {
		
		super(line, 2);
	}

	@Override
	public String extract(UML_Element element) {
		
		switch(element) {
		
		case CLASSES: return this.extractClasses();
		case IDENTIFIER: return this.extractIdentifier();
			
		default: throw new IllegalArgumentException();	
		}
		
		
	}

	private String extractClasses() {
		
		this.classes = this.removeWhitespace();
		
		this.verifySyntax();
		
		return null;
		
	}
	
	private String extractIdentifier() {
		
		if(this.classes == null)
			
			throw new UML_ClassesNotExtractedException(this.getLine());
		
		String extractedClass = this.classes[this.indexOfLastClass];
		boolean containsVirgule = extractedClass.charAt(extractedClass.length()-1) == ',';
		boolean isLastClass = this.indexOfLastClass == this.classes.length-1;
		
		if(!isLastClass && !containsVirgule)
			
			throw new UML_MissingSyntaxException(",");
		
		else if(isLastClass && containsVirgule)
			
			throw new UML_IllegalSyntaxException(this.getLine());
		
		if(!isLastClass)
			
			extractedClass = extractedClass.substring(0, extractedClass.length()-1);
	
		this.indexOfLastClass++;
		
		return extractedClass;
			
	}

	private void verifySyntax() {
		
		String[] elements = this.removeWhitespace();
		
		if(elements.length < this.getNumberOfElements())
			
			throw new UML_SyntaxLengthException(this.getNumberOfElements(), elements.length,this.getLine());
		
		if(!elements[0].equals("SUBCLASSES"))
			
			throw new UML_MissingSyntaxException("SUBCLASSES");
		
	}
	
	public int getNbOfClasses() {
		
		if(this.classes == null)
			
			throw new UML_ClassesNotExtractedException(this.getLine());
		
		return this.classes.length-1;
	}

}
