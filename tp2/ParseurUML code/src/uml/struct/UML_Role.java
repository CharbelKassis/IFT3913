package uml.struct;

public class UML_Role extends AbstractUML {

	private String multiplicity;

	public UML_Role(String identifier, String multiplicity) {

		super(identifier);

		switch (multiplicity) {

		case "ONE":
		case "MANY":
		case "ONE_OR_MANY":
		case "OPTIONALLY_ONE":
		case "UNDEFINED":
			this.multiplicity = multiplicity;break;
		default:
			throw new IllegalArgumentException(multiplicity);
		}

	}

	public String getMultiplicity() {

		return this.multiplicity;
	}

}
