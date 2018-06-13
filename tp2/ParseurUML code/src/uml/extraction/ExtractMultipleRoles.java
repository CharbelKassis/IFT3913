package uml.extraction;

import uml.exception.UML_RolesNotExtractedException;
import static uml.extraction.UML_Element.*;

public final class ExtractMultipleRoles extends Extractor {
	
	private int indexOfLastRole; /* index du dernier role extrait */
	private String[] roles; /* separer les lignes dans un tableau */
	private ExtractRole extractor;
	
	ExtractMultipleRoles() {
		
		super(3);
		this.extractor = new ExtractRole();
		
	}
	
	ExtractMultipleRoles(String line) {
		
		super(line,3);
		this.extractor = new ExtractRole();
	}

	@Override
	public String extract(UML_Element element) {
		
		switch(element) {
		
		case ROLES: 
			
			this.extractRoles();
			return null;
			
		case IDENTIFIER:
		case MULTIPLICITY:
			
			return this.extractElement(element);
		
		default: throw new IllegalArgumentException();
			
		}
		
	}

	private String extractElement(UML_Element element) {
		
		if(this.roles == null)
			
			throw new UML_RolesNotExtractedException(this.getLine());
		
		String extractedElement = this.extractor.newLine(this.roles[this.indexOfLastRole]).extract(element);
		
		if(element == MULTIPLICITY)
			
			this.indexOfLastRole++;
		
		return extractedElement;
	}

	private void extractRoles() {
		
		int nbOfVirgule = countCharacters(this.getLine(),',');
		this.roles = new String[nbOfVirgule+1];
		
		if(this.roles.length == 1) {
			
			this.roles[0] = this.getLine();
			return;
		
		}
		
		for(int i=0;i<nbOfVirgule;i++) {
			
			String line = this.getLine();
			this.roles[i] = line.substring(0,line.indexOf(","));
			line = line.substring(line.indexOf(",")+1,line.length());
			this.newLine(line);
		}
		
		this.roles[this.roles.length-1] = this.getLine();
		
	}
	
	public int getNbOfRoles() {
		
		if(this.roles == null)
			
			throw new UML_RolesNotExtractedException(this.getLine());
		
		return this.roles.length;
	}

}
