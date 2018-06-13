package uml.exception;

@SuppressWarnings("serial")

/**
 * 
 * @author Charbel Kassis
 * 
 * Exception lancee lorsqu'un element important d'une ligne est absent
 */
public class UML_MissingSyntaxException extends UML_Exception {

	public UML_MissingSyntaxException(String element, String line) {

		super("Element " + element + " introuvable."
		+"\n"+line);
	}

	public UML_MissingSyntaxException(String... elements) {

		super(generateText(elements));
	}

	private static String generateText(String[] elements) {

		String message = "Elements: ";

		for (int i = 0; i < elements.length-1; i++) {

			message += elements[i];

			if (i != elements.length - 2)

				message += " / ";
		}

		return message + " introuvables.\n "+elements[elements.length-1];
	}

}
