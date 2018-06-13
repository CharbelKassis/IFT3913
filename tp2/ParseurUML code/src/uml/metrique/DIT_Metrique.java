package uml.metrique;

import java.util.ArrayList;
import java.util.HashMap;
import uml.struct.Tree;
import uml.struct.UML_Generalization;

final class DIT_Metrique extends AbstractMetrique {

	DIT_Metrique(String selectedClassName) {
		
		super(selectedClassName);

	}

	@Override
	public Integer calculateMetrique() {
		
		
		/* l'arbres des ancetres de la classe selectionne. La racine est la classe selectionne, ses fils sont 
		 * ses superclasses.
		 */
		Tree<String> ancestorTree = new Tree<>();
		ancestorTree.setRoot(this.getSelectedClassName());
		this.fillAncestorTree(ancestorTree);
		
		/* retourner la profondeur maximum de l'arbre des ancetres, c'est-a-dire, la distance la plus longue entre
		 * la classe selectionee et tous les ancetres "racines" dans l'arbre de l'heritage
		 */
		return ancestorTree.getMaximumDepth();
	}


	@Override
	protected String getMetriqueName() {
		
		return "DIT";
	}
	
	/**
	 * 
	 * @param tree l'arbre qu'on remplis
	 */
	private void fillAncestorTree( Tree<String> tree) {
		
		Tree<String>.TreeNode<String> node = tree.getRoot();
		
		/* appelle la fonction recursive qui va remplir l'arbre a partir du noeud */
		this.addSuperclass(node);

	}
	
	/**
	 * Fonction qui ajoute les superclasses d'un noeud puis recursivement leurs superclasses a elles.
	 * 
	 * @param node le noeud pour lequel on veut ajouter les superclasses
	 */
	private void addSuperclass(Tree<String>.TreeNode<String> node) {
		
		HashMap<String,UML_Generalization> generalizations = this.getGeneralizationMap();
		
		/* pour chaque generalization */
		for(String generalization : generalizations.keySet()) {
			
			ArrayList<String> subClasses = generalizations.get(generalization).getSubClasses();

			/* si le noeud courant est une sous-classe d'une generalization */
			if(subClasses.contains(node.get()))
					
				/* alors la generalization est une superclasse du noeud */
				node.addSon(generalization);
			
			/* recursivement aller chercher les superclasses des superclasses du noeud courant */
			for(Tree<String>.TreeNode<String> son : node.getSons())
				
				this.addSuperclass(son);
			
		}
		
	}

	@Override
	public String getTooltip() {
		
		return "DIT(ci) : Taille du chemin le plus long reliant une classe ci à une classe racine dans le graphe d’héritage.";
	}
	

}
