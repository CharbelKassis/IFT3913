package uml.metrique;

import uml.struct.Tree;

final class CLD_Metrique extends DescendantMetrique {

	CLD_Metrique(String selectedClassName) {
		super(selectedClassName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer calculateMetrique() {
		
		/* l'arbres des descendants de la classe selectionne. La racine est la classe selectionne, ses fils sont 
		 * ses sous-classes.
		 */
		Tree<String> descendantTree = new Tree<>();
		descendantTree.setRoot(this.getSelectedClassName());
		this.fillDecendantTree(descendantTree);
		
		/* retourner la profondeur maximum de l'arbre des descendants, c'est-a-dire, la distance la plus longue entre
		 * la classe selectionee et tous les descendants "feuilles" dans l'arbre de l'heritage
		 */
		return descendantTree.getMaximumDepth();
	}
	
	@Override
	protected String getMetriqueName() {
		
		return "CLD";
	}

	@Override
	public String getTooltip() {
		
		return "CLD(ci) : Taille du chemin le plus long reliant une classe ci à une classe feuille dans le graphe d’héritage.";
	}

}
