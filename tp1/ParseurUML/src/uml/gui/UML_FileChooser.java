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
		
	}
	
	/* Creer le layout */
	private void setLayout() {
		
		this.layout = new BorderLayout();
		this.layout.setHgap(30);
		this.layout.setVgap(30);
		this.setLayout(this.layout);
		this.label.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/* Mettre le boutton et la zone de text a l'interieur */
	private void addComponents() {
		
		this.add(this.button,BorderLayout.WEST);
		this.add(this.label,BorderLayout.CENTER);
	}
	
	public JButton getButton() {
		
		return this.button;
	}
	
	public JLabel getLabel() {
		
		return this.label;
	}
}
