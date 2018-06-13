package uml.gui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import uml.struct.AbstractUML;
import uml.struct.UML_DataItem;

@SuppressWarnings("serial")
public final class UML_AttributeList extends UML_List {
	
	private ArrayList<UML_DataItem> attributes;
	
	UML_AttributeList() {
		
		super("Attributs");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractUML> void setList(ArrayList<T> list) {
		
		this.attributes = (ArrayList<UML_DataItem>) list;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <T> void iterateUMLStructure(DefaultListModel<T> listModel) {
		
		for(UML_DataItem attribute : this.attributes) {
			
			String type = attribute.getType();
			String identifier = attribute.getIdentifier();
			
			listModel.addElement((T) (type+" "+identifier));
			
		}
		
	}

}
