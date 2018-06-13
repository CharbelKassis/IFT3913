package uml.gui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import uml.struct.AbstractUML;
import uml.struct.UML_Generalization;

@SuppressWarnings("serial")
public final class UML_SousclasseList extends UML_List {

	private ArrayList<UML_Generalization> generalizations;

	UML_SousclasseList() {
		
		super("Sous-classes");

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractUML> void setList(ArrayList<T> list) {
		
		this.generalizations = (ArrayList<UML_Generalization>) list;
		
	}

	@Override
	protected void iterateUMLStructure(DefaultListModel<String> listModel) {
		
		try {
			UML_Generalization generalization = this.generalizations.get(0);
			
			for(String subClass : generalization.getSubClasses())
				
				listModel.addElement(subClass);
		}
		
		/* s'il n'y a pas de sous classes alors le code en haut lancera une exception */
		catch(IndexOutOfBoundsException e) {}
		
	}

}
