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
	
	UML_List(String title) {
		
		super(title,new JList<String>());

	}
	  
    /** Affiche la liste des elements UML dans la liste */
	@SuppressWarnings("unchecked")
	public void showList() {
		
		JList<String> list = (JList<String>) this.component;
		DefaultListModel<String> listModel = new DefaultListModel<>();

		this.iterateUMLStructure(listModel);
		list.setModel(listModel);
		
	}
	
	/** Ajouter la liste d'element UML a l'objet UML_List */
	public abstract <T extends AbstractUML> void setList (ArrayList<T> list);
	
	/** Fonction servant a remplir un DefaultListModel en iterant sur la liste d'element UML 
	 * ajoute a l'objet avec la methode setList */
	protected abstract void iterateUMLStructure(DefaultListModel<String> listModel);

}
