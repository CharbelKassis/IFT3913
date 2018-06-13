package uml.struct;

import java.util.*;

public class UML_Generalization extends AbstractUML {

	private ArrayList<String> subClasses;

	public UML_Generalization(String identifier) {

		super(identifier);
		this.subClasses = new ArrayList<>();

	}

	public void addSubClass(String subClass) {

		this.subClasses.add(subClass);
	}

	public ArrayList<String> getSubClasses() {

		return this.subClasses;
	}
}
