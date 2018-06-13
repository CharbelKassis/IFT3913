package uml.gui.controller;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import uml.exception.UML_Exception;
import uml.gui.*;
import uml.parser.UML_Parser;
import uml.struct.UML_Class;

/**
 * 
 * @author Charbel Kassis
 *
 * Classe servant ajouter une action au boutton de selection d'un fichier
 */
class UML_FileChooserListener implements ActionListener {
	
	private UML_Frame frame;
	private UML_Parser parser;

	public UML_FileChooserListener(UML_Frame frame, UML_Parser parser) {
		
		this.frame = frame;
		this.parser = parser;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/* ouvrir un FileChooser */
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(this.frame);
		
		/* si le resultat est correcte alors */
		if(result == JFileChooser.APPROVE_OPTION) {
			
			File file = chooser.getSelectedFile();
			try {
				
				/* parser le fichier */
				this.parser.addFile(file);
				this.parser.parse();
				
				ArrayList<UML_Class> umlClasses = parser.getModel().getClasses();
				
				/* extraire les classes puis les faire afficher dans la ClassList */
				this.frame.getClassList().setList(umlClasses);
				this.frame.getClassList().showList();
				
				/* mettre le chemin du fichier dans le label */
				this.frame.getFileChooser().getLabel().setText(file.getAbsolutePath());
				/* afficher un message */
				JOptionPane.showMessageDialog(this.frame, "Parsing reussi.");
				
			} 
			
			catch (UML_Exception exp) {
				
				JOptionPane.showMessageDialog(this.frame, "Erreur de parsing\nException lancee: \n"+exp.getMessage());
			}
			
			catch(IOException exp) {}
		}

	}



}
