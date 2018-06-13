package uml.metrique;

import java.util.HashSet;

import uml.struct.UML_DataItem;

	final class NOA_Metrique extends AbstractMetrique {

	NOA_Metrique(String selectedClassName) {
		
		super(selectedClassName);
		
	}

	@Override
	public Integer calculateMetrique() {
			
		HashSet<String> superClasses = new HashSet<>();
		
		this.getSuperclasses(superClasses);
		return this.getNumberOfAttributs(superClasses);
	}

	@Override
	protected String getMetriqueName() {
		
		return "NOA";
	}
	
	/**
	 * @param superClasses Un set contenant le nom des superClasses
	 * @return le nombre d'attributs locales et heritees
	 */
	private int getNumberOfAttributs(HashSet<String> superClasses) {
		
		/* Vu que le HashSet n'accepte pas les doublons, on stocke les nom des attributs dans le HashSet NomDAttributs, si une sous classes redefini une attribut de la superclasse, ceci
		 * comptera comme un doublons, la structure de donnee va ignorer ces doublons. Iterer sur les classes et leur liste d'attributs pour stocker les nom des attributs dans
		 * le HashSet nomDAttributs
		 */
		HashSet<String> nomDAttributs = new HashSet<>();
		
		for(String className : superClasses)
			
			for(UML_DataItem attribut : this.getUmlClassMap().get(className).getAttributes())
			
				nomDAttributs.add(attribut.getIdentifier());
		
		return nomDAttributs.size();
		
	}

	@Override
	public String getTooltip() {
		
		return "NOA(ci) : Nombre d’attributs locaux/hérités de la classe ci.";
	}

}
