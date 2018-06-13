package uml.extraction;

public class ExtractorBuilder {
	
	private String line;
	
	public ExtractorBuilder() {}
	
	public ExtractorBuilder(String line) {
		
		this.line = line;
	}
	
	public ExtractorBuilder newLine(String line) {
		
		this.line = line;
		return this;
	}
	
	public Extractor build(UML_Element element) {

		Extractor extractor = null;
		
		switch(element) {
		
		case AGGREGATION: extractor = new ExtractAggregation(this.line);break;
		case ARGUMENTS: extractor = new ExtractArgument(this.line);break;
		case ASSOCIATION: extractor = new ExtractAssociation(this.line);break;
		case ATTRIBUTE: extractor = new ExtractAttribute(this.line);break;
		case CLASS: extractor = new ExtractClass(this.line);break;
		case GENERALIZATION: extractor = new ExtractGeneralization(this.line);break;
		case MODEL: extractor = new ExtractModel(this.line);break;
		case ROLES: extractor = new ExtractMultipleRoles(this.line);break;
		case OPERATION: extractor = new ExtractOperation(this.line);break;
		case ROLE: extractor = new ExtractRole(this.line);break;
		case SUBCLASSES: extractor = new ExtractSubclass(this.line);break;
		default: throw new IllegalArgumentException();
		}
		
		return extractor;
	}
}
