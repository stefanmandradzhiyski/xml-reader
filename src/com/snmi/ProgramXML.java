package com.snmi;

import com.snmi.controller.XMLController;
import com.snmi.model.XMLData;
import com.snmi.view.XMLView;

/**
 * Main program which can be used to search data in specific xml file
 * @author Stefan Mandradzhiyski
 * @version 1.0
 */
public class ProgramXML {

    public static void main(String[] args) {
        XMLData model = new XMLData();
        XMLView view = new XMLView();
        XMLController controller = new XMLController(model, view);

        controller.searchDataInXML(OperationType.MULTIPLE);
        controller.updateView();

        controller.searchDataInXML(OperationType.SINGLE);
        controller.updateView();
    }

}
