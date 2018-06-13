package uml.gui;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;

import uml.struct.AbstractUML;
import uml.struct.UML_Aggregation;
import uml.struct.UML_Association;
import uml.struct.UML_Role;

@SuppressWarnings("serial")
/**
 * 
 * @author Charbel Kassis
 *
 */
public class UML_AssociationAggregationList extends UML_List{
	
	private ArrayList<UML_Aggregation> aggregations;
	private ArrayList<UML_Association> associations;
	
	private HashMap<String,UML_Aggregation> title_aggregation_map; /* Map entre le titre sur la liste et l'objet Aggregation */
	private HashMap<String,UML_Association> title_association_map; /* Map entre le titre sur la liste et l'objet Association */

	UML_AssociationAggregationList() {
		
		super("Association/Agregation");
		
	}
	
	public HashMap<String,UML_Aggregation> getAggregationMap() {
		
		return this.title_aggregation_map;
		
	}
	
	public HashMap<String,UML_Association> getAssociationMap() {
		
		return this.title_association_map;
	}

	@Override
	public <T extends AbstractUML> void setList(ArrayList<T> list) {
		
		this.aggregations = new ArrayList<>();
		this.associations = new ArrayList<>();
		this.title_aggregation_map = new HashMap<>();
		this.title_association_map = new HashMap<>();
		
		for(T t : list) {
		
			try	{
		
				this.aggregations.add( (UML_Aggregation ) t);
			}
		
			catch(ClassCastException e) {
			
				this.associations.add( (UML_Association) t);
			}
		
		}
			
	}

	@Override
	protected void iterateUMLStructure(DefaultListModel<String> listModel) {
		
		for(UML_Association association : this.associations) {
			
			String title = "(R) "+association.getIdentifier();
			listModel.addElement(title);
			this.title_association_map.put(title, association);
		}
		
		for(UML_Aggregation aggregation : this.aggregations) {
			
			String title = "(A) "+aggregation.getContainer().getIdentifier();
			listModel.addElement(title);
			this.title_aggregation_map.put(title,aggregation);
			
		}
			
	}

}
