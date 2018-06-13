package uml.metrique;

import uml.struct.Tree;

final class NOD_Metrique extends DescendantMetrique {

	NOD_Metrique(String selectedClassName) {
		
		super(selectedClassName);

	}

	@Override
	public Number calculateMetrique() {
		
		Tree<String> descendantTree = new Tree<>();
		descendantTree.setRoot(this.getSelectedClassName());
		this.fillDecendantTree(descendantTree);
		
		/* retourner le nombre de noeud - 1 de l'arbre de descendance, vu que les fils de la racine sont des sous-classes, alors
		 * le nombre de noeuds - 1 represente le nombre de sous-classe directes et indirectes.
		 */
		return descendantTree.size() -1;
	}

	@Override
	protected String getMetriqueName() {
		
		return "NOD";
	}

	@Override
	public String getTooltip() {
		
		return "NOD(ci) : Nombre de sous-classes directes et indirectes de ci.";
	}

}
