package uml.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;

import uml.gui.UML_Frame;
import uml.parser.UML_Parser;
import uml.struct.UML_Model;

/**
 * 
 * @author Charbel Kassis
 * 
 * Classe qui sert d'ecouteur de clique sur le boutton "Afficher metriques"
 */
final class UML_MetriqueListener implements ActionListener {
	
	private UML_Frame frame;
	private UML_Parser parser;

	UML_MetriqueListener(UML_Frame frame, UML_Parser parser) {
		
		this.frame = frame;
		this.parser = parser;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String selectedClass = ((JList<String>) this.frame.getClassList().getUMLComponent() ).getSelectedValue();
			
		/* Si aucune classe n'est selectionnée, afficher un message à l'utilisateur */
		if(selectedClass == null) {
			
			JOptionPane.showMessageDialog(this.frame, "Aucune classe n'est selectionnée.\nImpossible d'afficher les métriques.");
			return;
		}
			
		/* creer une liste de UML_Model puis ajouter le model dedans pour ensuite passer la liste a la methode setList */
		ArrayList<UML_Model> model = new ArrayList<>();
		model.add(this.parser.getModel());
		
		this.frame.getMetriqueList().setUMLClass(selectedClass);
		this.frame.getMetriqueList().setList(model);
		this.frame.getMetriqueList().showList();
	}

}
