package uml.exception;

@SuppressWarnings("serial")
/**
 * 
 * @author Charbel Kassis
 * 
 * Exception lancee quand on essaye d'extraire de l'information des classes UML avant de les avoir extrait elles-memes
 */
public final class UML_ClassesNotExtractedException extends UML_Exception {

	public UML_ClassesNotExtractedException(String line) {

		super("Il faut extraire les classes de la ligne avant d'extraire l'identifiant. utilisez"
				+ " l'instruction nomDeLExtracteurSubclass.extract(CLASSES)"
				+ "\n"+line);
				
				

	}
}
