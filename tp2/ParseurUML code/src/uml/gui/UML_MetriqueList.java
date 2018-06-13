package uml.gui;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;

import uml.metrique.MetriqueBuilder;
import uml.struct.AbstractUML;
import uml.struct.UML_Class;
import uml.struct.UML_Generalization;
import uml.struct.UML_Model;

import static uml.metrique.MetriqueBuilder.*;

/**
 * 
 * @author Charbel Kassis
 * 
 * La classe qui represente la liste des metriques
 */
@SuppressWarnings("serial")
public final class UML_MetriqueList extends UML_List {	
	
	
	private UML_Model model; /* Le model UML */
	private HashMap<String,UML_Class> umlClassesMap; /* Une version map de la liste des UML_Class */
	private String selectedClassName; /* Le nom de la classe UML selectionee */
	private HashMap<String,UML_Generalization> umlGeneralizationMap; /* Une version map de la liste des UML_Generalization */

	UML_MetriqueList() {
		
		super("Métriques");
		this.umlClassesMap = new HashMap<>();
		this.umlGeneralizationMap = new HashMap<>();

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractUML> void setList(ArrayList<T> list) {
		
		/* Si le model est deja charge en memoire, ne plus rien faire : cette etape deviendra 
		 * inutile car elle effectuera la meme tache a chaque fois qu'on demandera une metrique */
		if(this.model == null) {
			
			/* vu que la liste des metriques est plus complexe que les anciennes liste, il faut lui passer toute la structure UML */
			this.model = ((ArrayList<UML_Model>)list).get(0);
				
			/* creer une map de UML_Class et UML_Generalization a partir des listes
			 * des UML_Class et UML_Generalization extraites de UML_Model */
			
			for(UML_Class umlClass : this.model.getClasses() ) 
						
				this.umlClassesMap.put(umlClass.getIdentifier(), umlClass);
			
			for(UML_Generalization generalization : this.model.getGeneralizations())
				
				this.umlGeneralizationMap.put(generalization.getIdentifier(), generalization);
		
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	protected <T> void iterateUMLStructure(DefaultListModel<T> listModel) {
		
		/* instancier le MetriqueBuilder puis lui ajouter les listes necessaires pour construire les objets AbstractMetrique */
		MetriqueBuilder builder = 
				
				new MetriqueBuilder().setClassMap(this.umlClassesMap)
									 .setGeneralizationMap(this.umlGeneralizationMap)
									 .setAggregationList(this.model.getAggregations())
									 .setAssociationlist(this.model.getAssociations())
									 .setSelectedClassName(this.selectedClassName);
									 
		/* construire les metriques puis ajouter eur texte dans l'objet DefaultListModel pour qu'il soit afficher
		 * dans la liste des metriques */
		listModel.addElement((T) builder.build(ANA));
		listModel.addElement((T) builder.build(NOM));
		listModel.addElement((T) builder.build(NOA));
		listModel.addElement((T) builder.build(ITC));
		listModel.addElement((T) builder.build(ETC));
		listModel.addElement((T) builder.build(CAC));
		listModel.addElement((T) builder.build(DIT));
		listModel.addElement((T) builder.build(CLD));
		listModel.addElement((T) builder.build(NOC));
		listModel.addElement((T) builder.build(NOD));

	}	
	
	/** Fonction utilisee pour aller chercher recursivement toutes les sous-classes d'une classe en particulier
	 * 
	 * @param umlClass la classe dont on cherche les sous-classes
	 * @param sousClasses la liste des sous-classes extraites recursivement
	 * @param generalizations la liste des generalizations
	 * @return un HashSet contenant tous les decendants de la classe de depart
	 */
	
	
	public void setUMLClass(String selectedClass) {
		
		this.selectedClassName = selectedClass;
		
	}
	
	
}
