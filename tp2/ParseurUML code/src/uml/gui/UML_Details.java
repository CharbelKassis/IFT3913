package uml.gui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import uml.struct.AbstractUML;
import uml.struct.UML_Aggregation;
import uml.struct.UML_Association;
import uml.struct.UML_Role;

@SuppressWarnings("serial")
public final class UML_Details extends UML_List {

	private AbstractUML abstractUML;

	UML_Details() {
		super("Details");
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T extends AbstractUML> void setList(ArrayList<T> list) {
		
		this.abstractUML = list.get(0);

	}

	@SuppressWarnings("unchecked")
	@Override
	protected <T> void iterateUMLStructure(DefaultListModel<T> listModel) {
		
		String identifier = abstractUML.getIdentifier();
		
		/* identifier = null => c'est une aggregation */
				
		if(identifier == null) 
			
			this.showAggregation((DefaultListModel<String>) listModel);
		
		else
			
			this.showAssociation((DefaultListModel<String>) listModel);
			
			

	}

	private void showAssociation(DefaultListModel<String> listModel) {
		
		UML_Association association = (UML_Association) this.abstractUML;
		listModel.addElement("RELATION "+association.getIdentifier());
		listModel.addElement("<html><pre>\t"+"ROLES</pre></html>");
		listModel.addElement("<html><pre>\t"+"CLASS "+association.getRoles()[0].getIdentifier()+" "+association.getRoles()[0].getMultiplicity()+",</pre></html>");
		listModel.addElement("<html><pre>\t"+"CLASS "+association.getRoles()[1].getIdentifier()+" "+association.getRoles()[1].getMultiplicity()+"</pre></html>");
		
	}

	private void showAggregation(DefaultListModel<String> listModel) {
		
		UML_Aggregation aggregation = (UML_Aggregation) this.abstractUML;
		listModel.addElement("AGGREGATION");
		listModel.addElement("CONTAINER");
		listModel.addElement("<html><pre>\t"+"CLASS "+aggregation.getContainer().getIdentifier()+" "+
		aggregation.getContainer().getMultiplicity()+"</pre></html>");
		listModel.addElement("PARTS");
		
		ArrayList<UML_Role> parts = aggregation.getParts();
		String partsString = "<html><pre>\t";
		
		for(int i=0;i<parts.size()-1;i++)
			
			partsString += "CLASS "+parts.get(i).getIdentifier()+" "+parts.get(i).getMultiplicity()+",";
		
		partsString += "CLASS "+parts.get(parts.size()-1).getIdentifier()+" "+parts.get(parts.size()-1).getMultiplicity();
		
		listModel.addElement(partsString+"</pre></html>");
		
	}

}
