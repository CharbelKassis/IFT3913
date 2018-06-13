package uml.struct;

import java.util.*;

public final class UML_Aggregation extends AbstractUML {

	private UML_Role container;
	private ArrayList<UML_Role> parts;

	public UML_Aggregation() {

		super(null);
		this.parts = new ArrayList<>();
	}

	public UML_Role getContainer() {

		return this.container;
	}

	public void setContainer(UML_Role container) {

		this.container = container;
	}

	public ArrayList<UML_Role> getParts() {

		return this.parts;
	}

	public void addPart(UML_Role part) {

		this.parts.add(part);
	}

}
