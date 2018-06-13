package uml.gui;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
/**
 * 
 * @author Charbel Kassis
 * 
 * La fenetre graphique du programme
 */
public final class UML_Frame extends JFrame {
	
	private BorderLayout layout;
	private UML_ClassList classList; 
	private UML_FileChooser fileChooser;
	private UML_InformationPanel informationPanel;
	private UML_Details details;
	
	public UML_Frame() {
		
		super("Parseur UML - TP1 IFT3913");
		this.instanciateAttributes();
		this.formatFrame();
		
		/* ajouter les paneaux */
		Container pane = this.getContentPane();
		pane.add(this.classList,BorderLayout.WEST);
		pane.add(this.fileChooser, BorderLayout.NORTH);
		pane.add(this.informationPanel, BorderLayout.CENTER);
		pane.add(this.details, BorderLayout.SOUTH);
		
	}

	public UML_ClassList getClassList() {
		
		return this.classList;
	}
	
	public UML_FileChooser getFileChooser() {
		
		return this.fileChooser;
	}
	
	public UML_InformationPanel getInformationPanel() {
		
		return this.informationPanel;
	}
	
	public UML_Details getDetailPanel() {
		
		return this.details;
	}
	
	private void instanciateAttributes() {
		
		this.layout = new BorderLayout();
		this.classList = new UML_ClassList();
		this.fileChooser = new UML_FileChooser();
		this.informationPanel = new UML_InformationPanel();
		this.details = new UML_Details();
		
	}
	
	/* Formater la fenetre */
	private void formatFrame() {
		
		Container pane = this.getContentPane();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.layout.setHgap(10);
		this.layout.setVgap(10);
		pane.setLayout(this.layout);
	}
		
}
