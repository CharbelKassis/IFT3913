package uml.metrique;

import java.util.ArrayList;
import java.util.HashMap;

import uml.struct.*;


/**
 * 
 * @author Charbel Kassis
 * 
 * Classe servant a construire des objets de type AbstractMetrique
 */
public class MetriqueBuilder {
	
	/* Valeur des positions des metriques. Utile pour faire des switch/case */
	public final static int ANA = 0;
	public final static int NOM = 1;
	public final static int NOA = 2;
	public final static int ITC = 3;
	public final static int ETC = 4;
	public final static int CAC = 5;
	public final static int DIT = 6;
	public final static int CLD = 7;
	public final static int NOC = 8;
	public final static int NOD = 9;

	private HashMap<String, UML_Generalization> generalizationMap;
	private HashMap<String, UML_Class> classMap;
	private String selectedClassName;
	private ArrayList<UML_Aggregation> aggregations;
	private ArrayList<UML_Association> associations;

	public MetriqueBuilder setGeneralizationMap(HashMap<String,UML_Generalization> generalizationMap) {
		
		this.generalizationMap = generalizationMap;
		return this;
	}
	
	public MetriqueBuilder setClassMap(HashMap<String,UML_Class> classMap) {
		
		this.classMap = classMap;
		return this;
	}
	
	public MetriqueBuilder setSelectedClassName(String selectedClassName) {
		
		this.selectedClassName = selectedClassName;
		return this;
	}
	
	public MetriqueBuilder setAggregationList(ArrayList<UML_Aggregation> aggregations) {
		
		this.aggregations = aggregations;
		return this;
	}
	
	public MetriqueBuilder setAssociationlist(ArrayList<UML_Association> associations) {
		
		this.associations = associations;
		return this;
	}
	
	public AbstractMetrique build(int metrique) {
		
		AbstractMetrique abstractMetrique = null;
		
		if(this.selectedClassName == null)
			
			throw new IllegalArgumentException("Impossible de construire un AbstractMetrique. Il faut passer "
					+" le nom de classe selectionee au MetriqueBuilder avant de construire l'objet");
		
		switch(metrique) {
			
			case ANA: abstractMetrique = new ANA_Metrique(this.selectedClassName);break;
			case ITC: abstractMetrique = new ITC_Metrique(this.selectedClassName);break;
			case NOA: abstractMetrique = new NOA_Metrique(this.selectedClassName);break;
			case NOM: abstractMetrique = new NOM_Metrique(this.selectedClassName);break;
			case ETC: abstractMetrique = new ETC_Metrique(this.selectedClassName);break;
			case CAC: abstractMetrique = new CAC_Metrique(this.selectedClassName);break;
			case DIT: abstractMetrique = new DIT_Metrique(this.selectedClassName);break;
			case CLD: abstractMetrique = new CLD_Metrique(this.selectedClassName);break;
			case NOC: abstractMetrique = new NOC_Metrique(this.selectedClassName);break;
			case NOD: abstractMetrique = new NOD_Metrique(this.selectedClassName);break;
			default: return abstractMetrique;
		}
			
			abstractMetrique.setGeneralizationMap(generalizationMap);
			abstractMetrique.setClassMap(this.classMap);
			abstractMetrique.setAggregationList(this.aggregations);
			abstractMetrique.setAssociationlist(this.associations);
			
			return abstractMetrique;
		
	}

}
