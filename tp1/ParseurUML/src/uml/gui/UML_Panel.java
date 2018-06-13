package uml.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
/**
 * 
 * @author Charbel Kassis
 * 
 * Cette classe represente le modele d'un element graphique dans la fenetre, elle consiste en un titre en haut et le composant en bas place dans un JScrollPane
 */
abstract class UML_Panel extends JPanel {
	
	protected BorderLayout layout;
	protected JLabel label;
	protected JComponent component;
	protected JScrollPane scroll;
	
	/**
	 * 
	 * @param title Le titre du paneau
	 * @param component La composante graphique a ajouter
	 */
	protected UML_Panel(String title, JComponent component) {
		
		/* init les objets */
		this.instanciateAttributes(title, component);
		/* formater le paneau */
		this.formatPanel();
		/* ajouter les objets au paneau */
		this.addComponents();
	}
	
	/**
	 * 
	 * @param title Le titre du paneau
	 * @param component La composante graphique a ajouter
	 */
	private void instanciateAttributes(String title, JComponent component) {
		
		this.label = new JLabel(title);
		this.component = component;
		this.scroll = new JScrollPane(this.component);
	}
	
	/** Ajouter le style du paneau */
	private void formatPanel() {
		
		this.layout = new BorderLayout();
		this.scroll.setBorder(new EmptyBorder(0,20,20,20));
		this.label.setBorder(new EmptyBorder(0,20,0,0));
		this.component.setBorder(BorderFactory.createLineBorder(Color.black));
		this.component.setBackground(Color.WHITE);
	}
	
	/**
	 * Mettre le titre et le JScrollPane contenant la liste a l'interieur
	 */
	private void addComponents() {
		
		this.setLayout(this.layout);
		this.add(this.label,BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);

	}
	
	/**
	 * @return le composant graphique qui contiendra l'information sur un ou plusieurs elements UML
	 */
	public JComponent getUMLComponent() {
		
		return this.component;
	}
	
}
