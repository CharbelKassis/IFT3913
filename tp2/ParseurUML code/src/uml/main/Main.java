package uml.main;

import uml.gui.UML_Frame;
import uml.gui.controller.UML_GUI_Controller;

public class Main {
	
	public static void main(String[] args) {
		
		UML_Frame frame = new UML_Frame();
		UML_GUI_Controller controller = new UML_GUI_Controller(frame);
		controller.start();
	}

}
