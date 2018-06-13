package uml.metrique;

import java.util.Map.Entry;

import uml.struct.*;

final class ETC_Metrique extends AbstractMetrique {

	ETC_Metrique(String selectedClassName) {
		
		super(selectedClassName);
		
	}

	@Override
	public Integer calculateMetrique() {
		
		int count = 0;
		
		/* pour chaque classe UML */
		for(Entry<String, UML_Class> umlClass : this.getUmlClassMap().entrySet())
			
			/* pour chaque operation */
			for(UML_Operation operation : umlClass.getValue().getOperations())
				
				/* pour chaque argument */
				for(UML_DataItem argument : operation.getArguments())
					
					/* si le type de l'argument est le nom de la classe selectionnee, incrementer le compteur */
					if(argument.getType().equals(this.getSelectedClassName()))
						
						count++;
		
		return count;
	}

	@Override
	protected String getMetriqueName() {
		
		return "ETC";
	}

	@Override
	public String getTooltip() {
		
		return "ETC(ci) : Nombre de fois où ci apparaît comme type des arguments dans les méthodes des autres classes du diagramme.";
	}

}
