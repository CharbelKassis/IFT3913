package uml.gui.controller;

import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import uml.gui.UML_Frame;
import uml.parser.UML_Parser;

/**
 * 
 * @author Charbel Kassis
 *	
 * Classe servant a relier les listeners au composantes graphiques et a demarrer la fenetre
 */
public class UML_GUI_Controller {
	
	private UML_Frame frame;
	private UML_Parser parser;
	
	/**
	 * @param frame La fenetre
	 * 
	 */
	@SuppressWarnings("unchecked")
	public UML_GUI_Controller(UML_Frame frame) {
		
		this.frame = frame;
		this.parser = new UML_Parser();
		
		/* ajouter le listener au boutton du fileChooser */
		this.frame.getFileChooser().getButton().addActionListener(new UML_FileChooserListener(this.frame,this.parser));
		
		/* ajouter le listener au changement d'option de classe */
		JList<String> classList = (JList<String>) this.frame.getClassList().getUMLComponent();
		classList.addListSelectionListener(new UML_ClassListener(this.frame, this.parser));
		
		/* ajouter le listener au changement d'option d'aggregation / association */
		JList<String> assocAggrList = (JList<String>) this.frame.getInformationPanel().getAssocAggregList().getUMLComponent();
		assocAggrList.addListSelectionListener(new UML_AssocAggreListListener(this.frame));
		
		/* ajouter le listener au boutton pour afficher les metriques */
		this.frame.getFileChooser().getMetriqueButton().addActionListener(new UML_MetriqueListener(this.frame,this.parser));
		
		/* ajouter le listener pour afficher les tooltips pour les metriques */
		UML_MetriqueTooltipListener toolTipListener = new UML_MetriqueTooltipListener();
		this.frame.getMetriqueList().getUMLComponent().addMouseMotionListener(toolTipListener);
		this.frame.getMetriqueList().getUMLComponent().addMouseListener(new UML_MetriqueMouseListener() );
		
		/* ajouter le listener pour generer un fichier CSV des metriques */
		this.frame.getFileChooser().getCsvMetrique().addActionListener(new UML_CSV_MetriqueListener(this.frame, this.parser));
	}
	
	/**
	 * Afficher la fenetre
	 */
	public void start() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		this.frame.setVisible(true);
	}
}
