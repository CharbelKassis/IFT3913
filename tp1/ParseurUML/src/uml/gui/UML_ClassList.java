package uml.gui;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;

import uml.struct.AbstractUML;
import uml.struct.UML_Class;

@SuppressWarnings("serial")
public final class UML_ClassList extends UML_List {
	
	private ArrayList<UML_Class> umlClasses;
	private HashMap<String,UML_Class> umlClassesMap;
	
	UML_ClassList() {
		
		super("Classes");

		this.umlClassesMap = new HashMap<>();
	}

	@Override
	protected void iterateUMLStructure(DefaultListModel<String> listModel) {
		
		for(UML_Class umlClass : this.umlClasses)
		
			listModel.addElement(umlClass.getIdentifier());
		
		this.arrayListToHashMap();

	}
	
	/* Transformer l'ArrayList de UML_Class en HashMap */
	private void arrayListToHashMap() {
		
		for(UML_Class umlClass : this.umlClasses ) 
			
			this.umlClassesMap.put(umlClass.getIdentifier(), umlClass);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractUML> void setList(ArrayList<T> list) {
		
		this.umlClasses = (ArrayList<UML_Class>) list;
		
	}
	
	public HashMap<String,UML_Class> getClassMap() {
		
		return this.umlClassesMap;
	}

}
