package uml.gui.controller;

import java.awt.event.*;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;

import uml.metrique.AbstractMetrique;

/**
 * 
 * @author Charbel Kassis
 * 
 * Classe qui s'occupe de reduire le delai d'affichage des tooltip et d'afficher la definition dans une boite
 * de dialogue
 */
 class UML_MetriqueMouseListener extends MouseAdapter {
	
    private static int initialDelay = ToolTipManager.sharedInstance().getInitialDelay();

    @Override
    public void mouseEntered(MouseEvent e) {
        
    	/* enleve le delai des tooltips, ils apparaissent immediatement */
    	ToolTipManager.sharedInstance().setInitialDelay(0);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    	/* remet le delai des tooltips par defaut lorsqu'on sort de la liste des metriques */
    	ToolTipManager.sharedInstance().setInitialDelay(initialDelay);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void mousePressed(MouseEvent e) {
    	
    	JList<AbstractMetrique> list = (JList<AbstractMetrique>) e.getSource();    		
		ListModel<AbstractMetrique> model = list.getModel();
		int index = list.locationToIndex(e.getPoint());
    	
		/* si la liste des metriques n'est pas chargee lors d'un clique, une exception est lancee:
		 * capturer l'exception et ne rien faire
		 */
		try {
	    	
	    	/* lors d'un clique gauche, afficher immediatement la definition */
	    	if(SwingUtilities.isLeftMouseButton(e)) {
	    		
	    		/* si aucune metrique est chargee alors capturer l'exception */
	    		JOptionPane.showMessageDialog(null, model.getElementAt(index).getTooltip());
	
	    	}
	    
		}
		
		catch(IndexOutOfBoundsException exp) {}
    	
    	
    }
    

}
