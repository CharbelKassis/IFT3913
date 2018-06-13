package uml.metrique;

import java.util.HashSet;
import uml.struct.UML_Operation;

	final class NOM_Metrique extends AbstractMetrique {

	NOM_Metrique(String selectedClassName) {
		
		super(selectedClassName);
		
	}

	@Override
	public Integer calculateMetrique() {
		
		HashSet<String> superClasses = new HashSet<>();
		this.getSuperclasses(superClasses);
		return this.getNumberOfMethods(superClasses);
	}

	@Override
	protected String getMetriqueName() {
		
		return "NOM";
	}
	
	/** Fonction qui calcule le nombre d'operation
	 * @param superClasses Un set contenant le nom des superClasses
	 * @return le nombre de methodes locales et heritees
	 */
	private int getNumberOfMethods(HashSet<String> superClasses) {
		
		/* Vu que le HashSet n'accepte pas les doublons, on stocke les nom des methode dans le HashSet NomDeMethodes, si une sous classes redefini une methode de la superclasse, ceci
		 * comptera comme un doublons, la structure de donnee va ignorer ces doublons. Iterer sur les classes et leur liste d'operation pour stocker les nom de methodes dans
		 * le HashSet NomDeMethodes
		 */
		HashSet<String> nomDeMethodes = new HashSet<>();
		
		for(String className : superClasses)
			
			for(UML_Operation operation : this.getUmlClassMap().get(className).getOperations())
			
				nomDeMethodes.add(operation.getIdentifier());
		
		return nomDeMethodes.size();
		
	}

	@Override
	public String getTooltip() {
		
		return "<html>NOM(ci) : Nombre de méthodes locales/héritées de la classe ci. <br/>"
				+ "Dans le cas où une méthode est héritée et redéfinie localement <br/>"
				+ "même nom, même ordre et types des arguments et même type de retour), elle ne compte qu’une fois.</html>";
	}


}
