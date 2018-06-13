package uml.exception;

@SuppressWarnings("serial")

/**
 * 
 * @author Charbel Kassis
 * 
 * Exception lancee quand on essaye d'extraire de l'information des roles avant de les avoir extrait eux-memes avant
 */
public final class UML_RolesNotExtractedException extends UML_Exception {
	
	public UML_RolesNotExtractedException(String line) {
		
		super("Il faut extraire les roles de la ligne avant d'extraire l'identifiant ou la multiplicite. utilisez"
				+ "l'instruction nomDeLExtracteurMultipleRoles.extract(ROLES)"+
				"\n"+line);
		
	}

}

