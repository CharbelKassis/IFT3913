package uml.exception;

@SuppressWarnings("serial")
/**
 * 
 * @author Charbel Kassis
 *
 * Exception lancee quand on essaye d'extraire de l'information du parseur avant meme d'avoir parser le fichier
 */
public final class UML_FileNotParsedException extends UML_Exception {
	
	public UML_FileNotParsedException() {
		
		super("Le fichier n'a pas encore ete parse");
	}
}
