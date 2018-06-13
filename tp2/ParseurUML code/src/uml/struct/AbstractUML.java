package uml.struct;

/**
 * 
 * @author Cher
 * 
 * Modele abstrait d'un element UML
 */
public abstract class AbstractUML {

	private String identifier;

	protected AbstractUML(String identifier) {

		this.identifier = identifier;
	}

	public String getIdentifier() {

		return this.identifier;
	}
}
