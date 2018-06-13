package uml.metrique;

import uml.struct.UML_Class;
import uml.struct.UML_Operation;

	final class ANA_Metrique extends AbstractMetrique {

	ANA_Metrique(String selectedClass) {
		
		super(selectedClass);

	}

	@Override
	public Double calculateMetrique() {
		
		UML_Class umlClass = this.getUmlClassMap().get(this.getSelectedClassName());
		double argumentCount = 0;
		
		/* s'il n'y pas de methodes, afficher "ANA = 0" */
		if(umlClass.getOperations().size() == 0)
			
			return 0.0;
		
		/* pour chaque methode de la classe */
		for(UML_Operation operation : umlClass.getOperations())
			
			/* ajouter au compteur, le nombre d'argument */
			argumentCount += operation.getArguments().size();
		
		/* puis finalement,  diviser par le nombre de methodes dans la classe */
		return argumentCount /= umlClass.getOperations().size();
	}

	@Override
	protected String getMetriqueName() {
		
		return "ANA";
	}

	@Override
	public String getTooltip() {
		
		return "ANA(ci) : Nombre moyen d’arguments des méthodes locales pour la classe ci.";
	}

}
