package uml.exception;

@SuppressWarnings("serial")
/**
 * 
 * @author Charbel Kassis
 * 
 * Exception lancee lorsque le nombre d'elements textuels dans une ligne n'est pas egale ou bien inferieur au nombre espere.
 */
public final class UML_SyntaxLengthException extends UML_Exception {

	public UML_SyntaxLengthException(int length, int currentLength, String line) {

		super("Cette ligne doit contenir seulement ou plus que " + length + " element(s): " + "nombre d'elements recus : "
				+ currentLength + ".\n" + line);
				
			
	}
}
