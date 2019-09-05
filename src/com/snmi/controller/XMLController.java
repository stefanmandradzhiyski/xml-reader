package com.snmi.controller;

import com.snmi.OperationType;
import com.snmi.XMLLoader;
import com.snmi.XMLValidator;
import com.snmi.model.XMLData;
import com.snmi.view.XMLView;

import java.util.List;

/**
 * XML Controller
 * @author Stefan Mandradzhiyski
 * @version 1.0
 */
public class XMLController {

    /**
     * Constants
     */
    private static final String INCORRECT_OPERATION = "Operation hasn't implemented yet. ";

    /**
     * Variables
     */
    private XMLData model;
    private XMLView view;
    private XMLLoader xmlLoader;

    /**
     * Custom constructor
     * @param model take the model
     * @param view take the view
     */
    public XMLController(XMLData model, XMLView view) {
        this.model = model;
        this.view = view;
        this.xmlLoader = new XMLLoader();
    }

    /**
     * Make a request to fill the model
     * @param operationType take the operation type
     */
    private void fillModel(OperationType operationType) {
        String xmlPath = XMLValidator.validateXMLName();
        model.setXmlPath(xmlPath);

        switch (operationType) {
            case SINGLE:
                String attributeName = XMLValidator.validateAttribute();
                String attributeValue = XMLValidator.validateAttributeValue();
                model.setAttributeName(attributeName);
                model.setAttributeValue(attributeValue);
                break;

            case MULTIPLE:
                int attributesCounter = XMLValidator.validateAttributeCounter();
                List<String> attributeNames = XMLValidator.validateAttributeNames(attributesCounter);
                List<String> attributeValues = XMLValidator.validateAttributeValues(attributeNames);
                model.setAttributesCounter(attributesCounter);
                model.setAttributeNames(attributeNames);
                model.setAttributeValues(attributeValues);
                break;

            default:
                throw new IllegalArgumentException(INCORRECT_OPERATION);
        }
    }

    /**
     * Search in the XML file for specific word in specific attribute
     * @param operationType take the operation type
     */
    public void searchDataInXML(OperationType operationType) {
        model.cleanModel();
        fillModel(operationType);
        List<String> founded = xmlLoader.searchInXML(model, operationType);
        model.setFoundedAttributes(founded);
    }

    /**
     * Updating the view
     */
    public void updateView() {
        view.showResult(model.getFoundedAttributes());
    }
}
