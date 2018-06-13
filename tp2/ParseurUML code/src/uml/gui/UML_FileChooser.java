package uml.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
/**
 * 
 * @author Charbel Kassis
 * 
 * Le paneau qui contient le boutton qui sert a choisir un fichier ainsi qu'une zone de text pour afficher le chemin vers le fichier
 */
public final class UML_FileChooser extends JPanel{

	private JButton button;
	private JLabel label;
	private BorderLayout layout;
	private JButton metrique;
	private JButton csvMetrique;
	private JPanel metriqueButtonPanel;
	
	UML_FileChooser() {
		
		/* init les objets */
		this.instanciateAttributes();
		/* appliquer un layout */
		this.setLayout();
		/* ajouter les composants */
		this.addComponents();
		
		this.setBorder(new EmptyBorder(20,20,20,50));

	}
	
	/* Creer les objects */
	private void instanciateAttributes() {
		
		this.button = new JButton("Charger Fichier");
		this.label = new JLabel();
		/* MAJ : Le boutton de la metrique */
		this.metrique = new JButton("Calculer métriques");
		this.csvMetrique = new JButton("Générer fichier CSV");
		this.metriqueButtonPanel = new JPanel();
		
	}
	
	/* Creer le layout */
	private void setLayout() {
		
		this.layout = new BorderLayout();
		this.layout.setHgap(30);
		this.layout.setVgap(30);
		this.setLayout(this.layout);
		this.label.setBorder(BorderFactory.createLineBorder(Color.black));
		this.metriqueButtonPanel.setLayout(new BorderLayout());
		
	}
	
	/* Mettre le boutton et la zone de text a l'interieur */
	private void addComponents() {
		
		/* ajouter les tooltips sur les bouttons */
		this.button.setToolTipText("Selectionner un fichier text UCD pour qu'il soit parser par ce programme.");
		this.metrique.setToolTipText("Afficher les métriques de la classe sélectionnée");
		this.csvMetrique.setToolTipText("Générer un fichier CSV qui contient les métriques de toutes les classes.");
		
		this.add(this.button,BorderLayout.WEST);
		this.add(this.label,BorderLayout.CENTER);
		/* MAJ: ajout des bouttons des metrique */
		
		this.metriqueButtonPanel.add(this.metrique,BorderLayout.EAST);
		this.metriqueButtonPanel.add(this.csvMetrique,BorderLayout.WEST);
		this.add(metriqueButtonPanel,BorderLayout.EAST);
	}
	
	public JButton getButton() {
		
		return this.button;
	}
	
	/* MAJ: le getteur du nouveau boutton pour la metrique */
	public JButton getMetriqueButton() {
		
		return this.metrique;
	}
	
	/* MAJ: le getteur du nouveau boutton csv metrique */
	public JButton getCsvMetrique() {
		
		return this.csvMetrique;
	}
	
	public JLabel getLabel() {
		
		return this.label;
	}

}
