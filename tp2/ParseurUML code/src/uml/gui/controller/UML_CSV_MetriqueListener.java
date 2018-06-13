package uml.gui.controller;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import uml.exception.*;
import uml.gui.*;
import uml.metrique.*;
import uml.parser.*;
import uml.struct.*;
import static uml.metrique.MetriqueBuilder.*;

final class UML_CSV_MetriqueListener implements ActionListener {
	
	private UML_Frame frame;
	private UML_Parser parser;
	private HashMap<String,UML_Class> umlClassesMap;
	private HashMap<String,UML_Generalization> umlGeneralizationMap;

	UML_CSV_MetriqueListener(UML_Frame frame, UML_Parser parser) {
		
		this.frame = frame;
		this.parser = parser;
		this.umlClassesMap = new HashMap<>();
		this.umlGeneralizationMap = new HashMap<>();
		
	}
	
	public void actionPerformed(ActionEvent e) {
				
		/* si aucun fichier n'a ete parse */
		try {
			
			this.parser.getModel(); // verifier si un ficheir a ete parse
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setDialogTitle("Choisir l'emplacement où le fichier CSV sera enregistré");
			int result = fileChooser.showOpenDialog(this.frame);
			
			/* une fois que l'utilisateur a choisi le repertoire ou enregistrer le fichier CSV */
			if(result == JFileChooser.APPROVE_OPTION) {
	
				File directory = fileChooser.getSelectedFile();
				String fileName = this.parser.getModel().getIdentifier() + ".csv";
				
				/* le path complet du fichier csv est: (repertoire choisi par utilisateur)/(nom du model).csv */
				File csvFile = new File(directory,fileName);
				PrintWriter writer = null;
				
				try {
					
					writer = new PrintWriter(csvFile);
				} 
				
				catch (FileNotFoundException exp) { return; }
				
				this.writeCSV(writer);
				
				/* une fois que le fichier a ete ecrit, afficher un message */
				JOptionPane.showMessageDialog(this.frame, "Le fichier "+fileName+" a été créé."
						+ "\nLien du fichier: "+csvFile.getAbsolutePath());
			}
			
		}
		
		catch(UML_FileNotParsedException exp) {
			
			/* afficher un message */
			JOptionPane.showMessageDialog(this.frame, "Aucun fichier UML n'a été parsé\nImpossible de générer un fichier CSV.");
			return;
		}
			
	
	}
	
	/**
	 * 
	 * @param writer l'objet PrintWriter qui ecrira dans le fichier CSV
	 */
	private void writeCSV(PrintWriter writer) {
		
		UML_Model model = this.parser.getModel();
		
		/* creer les map des classes et generalization pour les passer au MetriqueBuilder */
		for(UML_Class umlClass : model.getClasses() ) 
			
			this.umlClassesMap.put(umlClass.getIdentifier(), umlClass);
		
		for(UML_Generalization generalization : model.getGeneralizations())
			
			this.umlGeneralizationMap.put(generalization.getIdentifier(), generalization);
		
		/* pour chacune des classes */
		for(String umlClass : umlClassesMap.keySet()) {
			
			String nomDeLigne = umlClass;
			String ligne = this.getCSVRow(nomDeLigne);
			writer.println(umlClass+","+ligne);
			writer.flush();
		}
			
		writer.close();	
			
		
	}

	/**
	 * 
	 * @param nomDeLigne le nom de la classe pour laquelle on veut extraire les metriques
	 * @return la ligne des metriques pour la classe nomDeLigne
	 */
	private String getCSVRow(String nomDeLigne) {
		
		String row = "";
		
		MetriqueBuilder builder = new MetriqueBuilder().setClassMap(this.umlClassesMap)
									.setGeneralizationMap(this.umlGeneralizationMap)
									.setAggregationList(this.parser.getModel().getAggregations())
									.setAssociationlist(this.parser.getModel().getAssociations())
									.setSelectedClassName(nomDeLigne);		
									
		/* chercher toutes les metriques, de ANA jusqu'a NOD */
		for(int i=ANA ; i<=NOD ; i++) {
			
			/* ajouter la valuer de la metrique */
			row += builder.build(i).calculateMetrique();
			
			if(i!= NOD)
				
				row += ",";
		}
		
		return row;

	}

}
