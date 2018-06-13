package uml.struct;

import java.util.*;

public final class UML_Operation extends AbstractUML {

	private ArrayList<UML_DataItem> arguments;
	private String type;

	public UML_Operation(String identifier, String type) {

		super(identifier);
		this.type = type;
		this.arguments = new ArrayList<>();
	}

	public ArrayList<UML_DataItem> getArguments() {

		return this.arguments;
	}

	public void addArgument(UML_DataItem argument) {

		this.arguments.add(argument);
	}
	
	public String getType() {
		
		return this.type;
	}

}
