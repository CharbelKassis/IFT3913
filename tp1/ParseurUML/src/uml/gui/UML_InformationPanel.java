package uml.gui;

import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
/**
 * 
 * @author Charbel Kassis
 *
 * Ce paneau contiendra les 4 carres d'information sur la classe choisi (attributs, methodes, sous-classes, association/aggregation)
 */
public final class UML_InformationPanel extends JPanel {
	
	private UML_AttributeList attributeList;
	private UML_MethodList methodList;
	private UML_SousclasseList sousClasseList;
	private UML_AssociationAggregationList associationAggregationList;
	
	public UML_InformationPanel() {
		
		super();
		
		this.attributeList = new UML_AttributeList();
		this.methodList = new UML_MethodList();
		this.sousClasseList = new UML_SousclasseList();
		this.associationAggregationList = new UML_AssociationAggregationList();
		
		GridLayout layout = new GridLayout(2,2);
		this.setLayout(layout);
		this.add(this.attributeList);
		this.add(this.methodList);
		this.add(this.sousClasseList);
		this.add(this.associationAggregationList);
	}

	public UML_AttributeList getAttributeList() {
		
		return this.attributeList;
	}


	public UML_MethodList getOperationList() {
		
		return this.methodList;
	}
	
	public UML_SousclasseList getSousClasseList() {
		
		return this.sousClasseList;
	}
	
	public UML_AssociationAggregationList getAssocAggregList() {
		
		return this.associationAggregationList;
	}
	
	
}
