package uml.struct;

import java.util.*;

public final class UML_Model extends AbstractUML {

	private ArrayList<UML_Class> classes;
	private ArrayList<UML_Association> associations;
	private ArrayList<UML_Generalization> generalizations;
	private ArrayList<UML_Aggregation> aggregations;

	public UML_Model(String identifier) {

		super(identifier);
		this.classes = new ArrayList<>();
		this.associations = new ArrayList<>();
		this.generalizations = new ArrayList<>();
		this.aggregations = new ArrayList<>();
	}

	public ArrayList<UML_Class> getClasses() {
		return classes;
	}

	public void addClass(UML_Class umlClass) {

		this.classes.add(umlClass);
	}

	public ArrayList<UML_Association> getAssociations() {
		
		return associations;
	}

	public void addAssociation(UML_Association umlAssociation) {
		
		this.associations.add(umlAssociation);
	}

	public ArrayList<UML_Generalization> getGeneralizations() {
		
		return generalizations;
	}

	public void addGeneralization(UML_Generalization umlGeneralization) {
		
		this.generalizations.add(umlGeneralization);
	}

	public ArrayList<UML_Aggregation> getAggregations() {
		
		return aggregations;
	}

	public void addAggregations(UML_Aggregation umlAggregation) {
		
		this.aggregations.add(umlAggregation);
	}
	
	

}
