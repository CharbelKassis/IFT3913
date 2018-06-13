package uml.gui.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uml.gui.UML_Details;
import uml.gui.UML_Frame;
import uml.parser.UML_Parser;
import uml.struct.AbstractUML;
import uml.struct.UML_Aggregation;
import uml.struct.UML_Association;

final class UML_AssocAggreListListener implements ListSelectionListener {
	
	private UML_Frame frame;


	public UML_AssocAggreListListener(UML_Frame frame) {
		
		this.frame = frame;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		if(!e.getValueIsAdjusting() ) {
			
			JList<String> list = (JList<String>) e.getSource();
			String selectedItem = list.getSelectedValue();

			if(list.getSelectedValue() != null) {
				
				UML_Association association;
				HashMap<String,UML_Association> associationMap = this.frame.getInformationPanel().getAssocAggregList().getAssociationMap();
				HashMap<String,UML_Aggregation> aggregationMap = this.frame.getInformationPanel().getAssocAggregList().getAggregationMap();
					
				
				if( (association = associationMap.get(selectedItem)) == null) {

					this.showAggregation(aggregationMap.get(selectedItem));
					
				}
				else {
						
					this.showAssociation(association);
					
				}
			
			}

		}
		
	}

    private void showAssociation(UML_Association association) {
		
		ArrayList<UML_Association> associationList = new ArrayList<>();
		associationList.add(association);
		
		this.frame.getDetailPanel().setList(associationList);
		this.frame.getDetailPanel().showList();
	}



	private void showAggregation(UML_Aggregation aggregation) {
		
		ArrayList<UML_Aggregation> aggregationList = new ArrayList<>();
		aggregationList.add(aggregation);
		
		this.frame.getDetailPanel().setList(aggregationList);
		this.frame.getDetailPanel().showList();
		
	}
	
}
