package uml.exception;

@SuppressWarnings("serial")
/**
 * 
 * @author Charbel Kassis
 *
 * Exception lancee quand on essaye d'extraire de l'information des arguments avant de les avoir extrait eux-memes avant
 */
public final class UML_ArgumentNotExtractedException extends UML_Exception {

	public UML_ArgumentNotExtractedException(String line) {

		super("Il faut extraire les arguments de la ligne avant d'extraire l'identifiant ou le type de retour. utilisez"
				+ " l'instruction nomDeLExtracteurArgument.extract(ARGUMENTS) "+
				
				"\n"+line);
				

	}
}
