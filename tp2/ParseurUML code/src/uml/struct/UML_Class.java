package uml.struct;

import java.util.*;

public final class UML_Class extends AbstractUML {

	private ArrayList<UML_DataItem> attributes;
	private ArrayList<UML_Operation> operations;

	public UML_Class(String identifier) {

		super(identifier);
		this.attributes = new ArrayList<>();
		this.operations = new ArrayList<>();
	}

	public ArrayList<UML_DataItem> getAttributes() {

		return this.attributes;
	}

	public ArrayList<UML_Operation> getOperations() {

		return this.operations;
	}

	public void addAttribute(UML_DataItem attribute) {

		this.attributes.add(attribute);
	}

	public void addOperation(UML_Operation operation) {

		this.operations.add(operation);
	}

}
