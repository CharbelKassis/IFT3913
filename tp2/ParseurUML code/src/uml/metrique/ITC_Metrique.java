package uml.metrique;

import java.util.ArrayList;

import uml.struct.UML_DataItem;
import uml.struct.UML_Operation;

	final class ITC_Metrique extends AbstractMetrique {

	ITC_Metrique(String selectedClassName) {
		
		super(selectedClassName);

	}

	@Override
	public Integer calculateMetrique() {
	
		ArrayList<UML_Operation> operations = this.getUmlClassMap().get(this.getSelectedClassName()).getOperations();
		/* le compteur de nombre de classe du diagrame qui apparaissent comme argument de la classe selectionnee */
		int nbOfClassArguments = 0;
		
		/* pour chaque operation de la classe selectionne */
		for(UML_Operation operation : operations)
			
			/* pour chacun de ses arguments */
			for(UML_DataItem argument : operation.getArguments())
				
				/* si la map des classes contient le type d'un de ces arguments, alors une des classes 
				 * est l'argument d'une methode de la classe selectionee */

				if(this.getUmlClassMap().containsKey(argument.getType()))
					
					nbOfClassArguments++;
		
		
		return nbOfClassArguments;
	}

	@Override
	protected String getMetriqueName() {
		
		return "ITC";
	}

	@Override
	public String getTooltip() {
		
		return "ITC(ci) : Nombre de fois où d’autres classes du diagramme apparaissent comme types des arguments des méthodes de ci.";
	}

}
