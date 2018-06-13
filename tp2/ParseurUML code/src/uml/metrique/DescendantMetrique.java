package uml.metrique;

import java.util.HashMap;

import uml.struct.Tree;
import uml.struct.UML_Generalization;

/**
 * 
 * @author Charbel Kassis
 * 
 * Classe abstraite qui represente les metriques qui calculent des valeurs par rapport a leurs sous-classes
 */
abstract class DescendantMetrique extends AbstractMetrique {

	protected DescendantMetrique(String selectedClassName) {
		
		super(selectedClassName);
		
	}

	/**
	 * 
	 * @param tree l'arbre qu'on remplis
	 */
	protected void fillDecendantTree(Tree<String> tree) {
		
		Tree<String>.TreeNode<String> node = tree.getRoot();
		
		/* appelle la fonction recursive qui va remplir l'arbre a partir du noeud */
		this.addSubclass(node);
		
	}
	
	/**
	 * Fonction qui ajoute les sous-classes d'un noeud puis recursivement leurs sous-classes a elles.
	 * 
	 * @param node le noeud pour lequel on veut ajouter les sous-classes
	 */
	protected void addSubclass(Tree<String>.TreeNode<String> node) {
		
		HashMap<String,UML_Generalization> generalizations = this.getGeneralizationMap();
		
		/* pour chaque generalization */
		for(String generalization : generalizations.keySet())
			
			/* si le noeud courant est une generalization */
			if(generalization.equals(node.get()))
				
				/* ajouter chacune des sous-classes de la generalization comme sous-classe du noeud */
				for(String sousclasse : generalizations.get(generalization).getSubClasses())
					
					node.addSon(sousclasse);
		
		/* recursivement aller chercher les sous-classes des sous-classes du noeud courant */
		for(Tree<String>.TreeNode<String> son : node.getSons())
			
			this.addSubclass(son);
		
	}


}
