package uml.exception;

@SuppressWarnings("serial")
/**
 * 
 * @author Charbel Kassis
 * 
 * Exception lancee lorsqu'une syntaxe illegale est detectee
 */
public final class UML_IllegalSyntaxException extends UML_Exception {
	
	public UML_IllegalSyntaxException(String line) {
		
		super(line);
	
	}
}
