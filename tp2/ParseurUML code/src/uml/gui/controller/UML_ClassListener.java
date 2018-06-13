package uml.gui.controller;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import uml.gui.*;
import uml.metrique.AbstractMetrique;
import uml.parser.*;
import uml.struct.*;


/**
 * 
 * @author Charbel Kassis
 * 
 * Classe servant a ajouter une action sur la selection d'une classe dans la liste des classes
 */
final class UML_ClassListener implements ListSelectionListener {
	
private UML_Frame frame;
private UML_Parser parser;

/**
 * 
 * @param frame
 * @param parser
 */
	UML_ClassListener(UML_Frame frame, UML_Parser parser) {
		
		this.frame = frame;
		this.parser = parser;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		if( ! e.getValueIsAdjusting() ) {
			
			JList<String> list = (JList<String>) e.getSource();
			String selectedItem = list.getSelectedValue();
			
			if(selectedItem != null)
			
				this.showResults(selectedItem);
			
			/* MAJ : effacer l'ancienne list des metriques */
			this.deleteMetriqueList();
		}

	}
	
	/**
	 * Fonction qui efface la liste des metriques
	 */
	@SuppressWarnings("unchecked")
	private void deleteMetriqueList() {
		
		/* si la liste est vide, une exception est lancee. Ne rien faire */
		try {
			JList<AbstractMetrique> list = (JList<AbstractMetrique>) this.frame.getMetriqueList().getUMLComponent();
			((DefaultListModel<AbstractMetrique>) list.getModel()).clear();
		}
		catch(Exception e) {}
		
	}

	private void showResults(String selectedItem) {
		
		UML_Class selectedClass = this.frame.getClassList().getClassMap().get(selectedItem);
		
		this.showAttributes(selectedClass);
		this.showMethods(selectedClass);
		this.showSousClasses(selectedItem);
		this.showAssocAggreg(selectedItem);
		 
		
	}

	private void showAttributes(UML_Class selectedClass) {
		
		ArrayList<UML_DataItem> attributes = selectedClass.getAttributes();
		this.frame.getInformationPanel().getAttributeList().setList(attributes);
		this.frame.getInformationPanel().getAttributeList().showList();
	}
	
	private void showMethods(UML_Class selectedClass) {
		
		ArrayList<UML_Operation> operations = selectedClass.getOperations();
		this.frame.getInformationPanel().getOperationList().setList(operations);
		this.frame.getInformationPanel().getOperationList().showList();
	}
	
	private void showSousClasses(String selectedItem) {
		
		ArrayList<UML_Generalization> generalizations = this.parser.getModel().getGeneralizations();
		ArrayList<UML_Generalization> generalizations2 = new ArrayList<>();
		
		for(UML_Generalization generalization : generalizations) {
			
			if(generalization.getIdentifier().equals(selectedItem)) {

				generalizations2.add(generalization);
				break;

			}
		
		}
		
		this.frame.getInformationPanel().getSousClasseList().setList(generalizations2);
		this.frame.getInformationPanel().getSousClasseList().showList();
		
	}
	
	private void showAssocAggreg(String selectedItem) {
		
		ArrayList<AbstractUML> assocAggreg = new ArrayList<>();
		ArrayList<UML_Association> associations = this.parser.getModel().getAssociations();
		ArrayList<UML_Aggregation> aggregations = this.parser.getModel().getAggregations();
		ArrayList<UML_Association> newAssociations;
		ArrayList<UML_Aggregation> newAggregations;
		
		newAssociations = removeUselessAssociation(associations,selectedItem);
		newAggregations = removeUselessAggregation(aggregations,selectedItem);

		
		combineList(assocAggreg,newAssociations);
		combineList(assocAggreg,newAggregations);

		this.frame.getInformationPanel().getAssocAggregList().setList(assocAggreg);
		this.frame.getInformationPanel().getAssocAggregList().showList();
		
	}
	
	
	/** Enleve les aggregations qui se sont pas en relation avec la classe selectionee
	 * 
	 * @param aggregations toutes les aggregations
	 * @param selectedItem le nom de la classe selectionee
	 * @return 
	 * @return le nouveau tableau
	 */
	private static ArrayList<UML_Aggregation> removeUselessAggregation(
			ArrayList<UML_Aggregation> aggregations, String selectedItem) {
		
		ArrayList<UML_Aggregation> newAggregations = new ArrayList<>();
		
		
		for(int i=0;i<aggregations.size();i++) {
			
			if(aggregations.get(i).getContainer().getIdentifier().equals(selectedItem)) {				
				
				newAggregations.add(aggregations.get(i));
				continue;
			}
			
			ArrayList<UML_Role> roles = aggregations.get(i).getParts();
			
			for(int j=0;j<roles.size();j++) {
				
				if(roles.get(j).getIdentifier().equals(selectedItem)) {
					
					newAggregations.add(aggregations.get(i));
					break;
					
				}
	
			}	
	
			
		}
		
		return newAggregations;
		
	}

	/** enleve les associations qui ne sont pas en relation avec la classe selectionee 
	 * @param associations toutes les associations
	 * @param selectedItem le nom de la classe selectionee
	 * @return le nouveau tableau
	 */
	
	private static ArrayList<UML_Association> removeUselessAssociation(
			ArrayList<UML_Association> associations, String selectedItem) {
		
		
		ArrayList<UML_Association> newAssociations = new ArrayList<>();
		
		for(int i=0;i<associations.size();i++) {
			
			if(associations.get(i).getRoles()[0].getIdentifier().equals(selectedItem) 
					
										||
					
			associations.get(i).getRoles()[1].getIdentifier().equals(selectedItem) )
		
				newAssociations.add(associations.get(i));
					
		}
		
		return newAssociations;
		
	}

	/** Ajouter des elements d'une liste d'objets d'une sous classe de AbstractUML a une liste d'AbstractUML */
	private static <T extends AbstractUML> void combineList(ArrayList<AbstractUML> assocAggreg, ArrayList<T> abstractUML) {
		
		for(T t : abstractUML)
			
			assocAggreg.add(t);
	}
}
