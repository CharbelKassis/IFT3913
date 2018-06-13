package uml.gui;

import javax.swing.*;
import java.util.*;
import uml.struct.*;

@SuppressWarnings({ "serial"})
/**
 * 
 * @author Charbel Kassis
 *
 * Cette classe represente le modele du paneau de la liste d'information
 */
public abstract class UML_List extends UML_Panel {
	
	/**
	 * 
	 * @param title le titre qui sera afficher au dessus du panneau
	 */
	<T> UML_List(String title) {
		
		/* MAJ: une JList<T> avec un seul element selectione a la fois */
		super(title,createList());

	}
	  
	/**
	 * 
	 * @return une JList qu'on ne peux selectionner qu'un element a la fois;
	 */
    private static <T> JList<T> createList() {
		
    	JList<T> list = new JList<>();
    	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	return list;
	}

	/**
     * Affiche la liste des elements UML dans la liste 
     */
	@SuppressWarnings("unchecked")
	public <T> void showList() {
		
		JList<T> list = (JList<T>) this.component;
		DefaultListModel<T> listModel = new DefaultListModel<>();

		this.iterateUMLStructure(listModel);
		list.setModel(listModel);
		
	}
	
	/**
	 * Ajouter la liste d'element UML a l'objet UML_List
	 * @param list la liste qui sera ajouter et traiter par la suite par le panneau de la GUI
	 */
	public abstract <T extends AbstractUML> void setList (ArrayList<T> list);
	
	/**
	 * Fonction servant a remplir un DefaultListModel en iterant sur la liste d'element UML 
	 * ajoute a l'objet avec la methode setList
	 * 
	 * @param listModel la listes des elements qui sera afficher dans le panneau 
	 */
	protected abstract <T> void iterateUMLStructure(DefaultListModel<T> listModel);

}
