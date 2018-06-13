package uml.gui.controller;

import java.awt.event.*;
import javax.swing.JList;
import javax.swing.ListModel;
import uml.metrique.AbstractMetrique;

/**
 * Classe qui s'occupe d'afficher les tooltips des metriques
 * 
 * @author Charbel Kassis
 */
final class UML_MetriqueTooltipListener extends MouseMotionAdapter{

	@SuppressWarnings("unchecked")
	@Override
	public void mouseMoved(MouseEvent e) {
		
		JList<AbstractMetrique> list = (JList<AbstractMetrique>) e.getSource();
		ListModel<AbstractMetrique> model = list.getModel();
		int index = list.locationToIndex(e.getPoint());
		
		if(index > -1) {
			
			list.setToolTipText(null);
			list.setToolTipText(model.getElementAt(index).getTooltip());

		}
		
	}

}
