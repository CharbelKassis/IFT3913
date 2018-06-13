package uml.exception;

@SuppressWarnings("serial")
/**
 * 
 * @author Charbel Kassis
 *
 * Exception abstraite qui represente une exception lancee lorsqu'une erreur de syntaxe est detectee ou bien une mauvaise utilisation des extrators lors du parsing
 */
public abstract class UML_Exception extends RuntimeException {

	protected UML_Exception(String message) {
		
		super(message);
	}
}
