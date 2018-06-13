package uml.metrique;

import java.util.HashSet;

import uml.struct.UML_Aggregation;
import uml.struct.UML_Association;
import uml.struct.UML_Role;

final class CAC_Metrique extends AbstractMetrique {

	CAC_Metrique(String selectedClassName) {
		
		super(selectedClassName);
		
	}

	@Override
	public Integer calculateMetrique() {
		
		HashSet<String> superClasses = new HashSet<>();
		this.getSuperclasses(superClasses);
		
		/* retourner le nombre d'association et d'aggregation dont font partie la classe selectionees et 
		 * ses superclasses */
		int nbOfAssociation = this.getNbOfAssociation(superClasses);
		int nbOfAggregation = this.getNbOfAggregation(superClasses);
		
		return nbOfAssociation+nbOfAggregation;

	}
	
	/**
	 * 
	 * @param superClasses
	 * @return le nombre d'aggregation dont font partie la classe selectionees et ses superclasses
	 */
	private int getNbOfAggregation(HashSet<String> superClasses) {
		
		int count = 0;
		
		for(UML_Aggregation aggregation : this.getAggregationList())
			
			for(String superClass : superClasses)
				
				if( faitPartieAggregation(aggregation,superClass))
					
					count++;
		
		return count;	
	}
	
	/**
	 * 
	 * @param aggregation
	 * @param umlClass
	 * @return true si la class fait partie de l'aggregation, false sinon
	 */
	private static boolean faitPartieAggregation(UML_Aggregation aggregation, String umlClass) {
		
		/* si le nom du container est le meme que la classe selectionee, 
		 * alors la classe fait partie de l'aggregation */
		if(aggregation.getContainer().getIdentifier().equals(umlClass))
			
			return true;
		
		/* regarder toutes les parts de l'aggregation et verifier si leur nom est le meme 
		 * que la classe selectionee  */
		
		for(UML_Role part : aggregation.getParts())
			
			if(part.getIdentifier().equals(umlClass))
			
				return true;
	
		return false;
	}

	/**
	 * 
	 * @param superClasses
	 * @return le nombre d'association dont font partie la classe selectionnes et ses superclasses
	 */
	private int getNbOfAssociation(HashSet<String> superClasses) {
		
		int count = 0;
		
		for(UML_Association association : this.getAssociationList())
			
			for(String superClass : superClasses)
				
				if( faitPartieAssociation(association,superClass))
					
					count++;
		
		return count;					
			
	}
	
	/**
	 * 
	 * @param association
	 * @param umlClass
	 * @return true si la class fait partie de l'association, false sinon
	 */
	private static boolean faitPartieAssociation(UML_Association association, String umlClass) {
				
		for(UML_Role role : association.getRoles())
			
			if(role.getIdentifier().equals(umlClass))
				
				return true;
		
		return false;
		
	}

	@Override
	protected String getMetriqueName() {
		
		return "CAC";
	}

	@Override
	public String getTooltip() {
		
		return "CAC(ci) : Nombre d’associations (incluant les agrégations) locales/héritées auxquelles participe une classe ci.";
	}


}
