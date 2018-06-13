package uml.metrique;

final class NOC_Metrique extends AbstractMetrique {

	NOC_Metrique(String selectedClassName) {

		super(selectedClassName);
		
	}

	@Override
	public Integer calculateMetrique() {
		
		/* si la classe selectionee n'est pas une generalization */
		if(!this.getGeneralizationMap().containsKey(this.getSelectedClassName()))
			
			return 0;
		
		else
			
			/* sinon retourner la taille de la liste de ses sous-classes */
			return this.getGeneralizationMap().get(this.getSelectedClassName()).getSubClasses().size();
	}

	@Override
	protected String getMetriqueName() {
		
		return "NOC";
	}

	@Override
	public String getTooltip() {
		
		return "NOC(ci) : Nombre de sous-classes directes de ci.";
	}

}
