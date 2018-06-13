package uml.gui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import uml.struct.AbstractUML;
import uml.struct.UML_DataItem;
import uml.struct.UML_Operation;

@SuppressWarnings("serial")
public final class UML_MethodList extends UML_List {

	private ArrayList<UML_Operation> operations;

	UML_MethodList() {
		
		super("Methodes");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractUML> void setList(ArrayList<T> list) {
		
		this.operations = (ArrayList<UML_Operation>) list;
		
	}

	@Override
	protected void iterateUMLStructure(DefaultListModel<String> listModel) {
		
		for(UML_Operation operation : this.operations) {
			
			String operationType = operation.getType();
			String operationIdentifier = operation.getIdentifier();
			String element = operationType+" "+operationIdentifier+"(";
			ArrayList<UML_DataItem> arguments = operation.getArguments();
			
			for(int i=0;i<arguments.size()-1;i++) {
				
				String argumentType = arguments.get(i).getType();
				element += argumentType+",";			
			}
			
				if(arguments.size() > 0)
			
					element += arguments.get(arguments.size()-1).getType();
			
				listModel.addElement(element+")");
		}		
		
	}

}
