package uml.struct;

public final class UML_DataItem extends AbstractUML {

	private String type;

	public UML_DataItem(String identifier, String type) {

		super(identifier);
		this.type = type;
	}

	public String getType() {

		return this.type;
	}
}
