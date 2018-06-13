package uml.metrique;

import java.util.*;
import java.util.Map.Entry;

import uml.struct.*;

/**
 * @author Charbel
 *
 * Classe servant a representer un calculateur de metrique
 * @param <UML_Generalization>
 */
public abstract class AbstractMetrique {
	
	private String selectedClassName;
	private HashMap<String, UML_Class> umlClassesMap;
	private HashMap<String, UML_Generalization> generalizationMap;
	private ArrayList<UML_Aggregation> aggregations;
	private ArrayList<UML_Association> associations;


	protected AbstractMetrique(String selectedClassName) {
		
		this.selectedClassName = selectedClassName;
	}
	
	public String getSelectedClassName() {
		
		return this.selectedClassName;
	}
	
	public void setClassMap(HashMap<String,UML_Class> umlClassesMap) {
		
		this.umlClassesMap = umlClassesMap;
	}
	
	public HashMap<String,UML_Class> getUmlClassMap() {
		
		return this.umlClassesMap;
	}
	
	public void setGeneralizationMap(HashMap<String,UML_Generalization> generalizationMap) {
		
		this.generalizationMap = generalizationMap;
	}
	
	public HashMap<String,UML_Generalization> getGeneralizationMap() {
		
		return this.generalizationMap;
	}
	
	public void setAggregationList(ArrayList<UML_Aggregation> aggregations) {
		
		this.aggregations = aggregations;
	}
	
	public ArrayList<UML_Aggregation> getAggregationList() {
		
		return this.aggregations;
	}
	
	public void setAssociationlist(ArrayList<UML_Association> associations) {
		
		this.associations = associations;
	}
	
	public ArrayList<UML_Association> getAssociationList() {
		
		return this.associations;
	}
	
	/** Fonction qui fait une copie de la liste de generalizations et remplis une liste des superClasses de la classe selectionnee
	 * 
	 * @param superClasses Une liste vide qui contiendra toutes les sous-classes de la classe selectionnee
	 */
	protected void getSuperclasses(HashSet<String> superClasses) {
		
		/* Creer une copie de generalizations et creer un HashSet qui contiendra la liste des superclasses */
		HashMap<String,UML_Generalization> generalizations = copyGeneralization(this.getGeneralizationMap());
		
		/* Remplir le set de superclasses avec la fonction getSuperclasses, ceci est la fonction statique qui
		 * porte le meme nom */
		getSuperclasses(this.getSelectedClassName(),superClasses,generalizations);
		
		/* Ajouter la classe elle-meme dans la liste des superClasses pour la suite de l'algorithme */
		superClasses.add(this.getSelectedClassName());
		
	}
	
	/** Fonction utilisee pour aller chercher recursivement toutes les super classes d'une classe en particulier. Il faut faire une copie de la liste
	 * des generalizations parce que l'algorithme enleve les classes deja traitees au fur et a mesure des appels recursive pour rendre l'algorithme
	 * plus efficase en terme de complexite.
	 * 
	 * @param umlClass la classe dont on cherche les superclasses
	 * @param superClasses la liste des superclasses extraites recursivement
	 * @param generalizations la liste des generalizations
	 * @return un HashSet contenant tous les ancetres de la classe de depart
	 */
	protected static HashSet<String> getSuperclasses(String umlClass, HashSet<String> superClasses, HashMap<String,UML_Generalization> generalizations) {
		
		/* pour chacune des generalizations restante */
		for(String generalization : generalizations.keySet()) {
			
			/* extraire la liste des sous-classes */
			ArrayList<String> subclasses = generalizations.get(generalization).getSubClasses();
			
			/* si la classe dont on cherche les superclasses sont dans cette liste */
			if(subclasses.contains(umlClass)) {
				
				/* ajouter le nom de la generalization dans la liste des superclasses */
				superClasses.add(generalization);
				/* enlever la generalization de la liste des generalizations pour le prochain appel recursif */
				generalizations.remove(generalization);
				getSuperclasses(generalization, superClasses,generalizations);
				
			}
			
		}
		
		return superClasses;		
			
	}
	
	/**
	 * fonction qui cherche toutes les sous-classes de la classe de depart recursivement en utilisant la liste
	 * des generalizations
	 * 
	 * @param umlClass
	 * @param sousClasses
	 * @param generalizations
	 * @return un HashSet contenant tous les decendants de la classe de depart
	 */
	protected static HashSet<String> getSousclasses(String umlClass, HashSet<String> sousClasses, HashMap<String,UML_Generalization> generalizations) {
		
		/* si la classe n'est pas une generalization, c'est-a-dire, qu'elle n'a pas de sous-classe */
		if(generalizations.containsKey(umlClass))
			
			return sousClasses;
		
		ArrayList<String> sousClassesListe = generalizations.get(umlClass).getSubClasses();
		
		/* pour chaque sous-classe, l'ajouter a la liste des sous-classes puis recursivement aller chercher leurs sous-classes a elles */
		for(String sousClasse : sousClassesListe) {
			
			sousClasses.add(sousClasse);
			getSousclasses(sousClasse, sousClasses,generalizations);
			
		}
		return sousClasses;
	}

	
	
	/** Fonction qui fait une copie de la liste des generalizations */
	protected static HashMap<String,UML_Generalization> copyGeneralization(HashMap<String,UML_Generalization> generalizations) {
		
		HashMap<String,UML_Generalization> map = new HashMap<>();
		
		for(Entry<String, UML_Generalization> set : generalizations.entrySet())
			
			map.put(set.getKey(), set.getValue());
		
		return map;
	}
	
	/**
	 * @return La valeur numerique de la metrique
	 */
	public abstract Number calculateMetrique();
	
	/**
	 * @return Le nom de la metrique
	 */
	protected abstract String getMetriqueName();
	
	
	/**
	 * 
	 * @return l'explication de la metrique
	 */
	public abstract String getTooltip();
	
	
	/**
	 * @return Le text de la forme : "NomDeMetrique : ValeurDeMetrique"
	 */
	public String toString() {
		
		return this.getMetriqueName()+" : "+this.calculateMetrique();
	}
	
	
}
