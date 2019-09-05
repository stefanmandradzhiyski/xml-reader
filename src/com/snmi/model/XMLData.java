package com.snmi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * XML data model
 * @author Stefan Mandradzhiyski
 * @version 1.0
 */
public class XMLData {

    /**
     * Variables
     */
    private String xmlPath;
    private String attributeName;
    private String attributeValue;
    private List<String> foundedAttributes = new ArrayList<>();
    private int attributesCounter = 0;
    private List<String> attributeNames = new ArrayList<>();
    private List<String> attributeValues = new ArrayList<>();

    /**
     * Default constructor
     */
    public XMLData() {}

    /**
     * Setters and getters
     */
    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public List<String> getFoundedAttributes() {
        return foundedAttributes;
    }

    public void setFoundedAttributes(List<String> foundedAttributes) {
        this.foundedAttributes = foundedAttributes;
    }

    public int getAttributesCounter() {
        return attributesCounter;
    }

    public void setAttributesCounter(int attributesCounter) {
        this.attributesCounter = attributesCounter;
    }

    public List<String> getAttributeNames() {
        return attributeNames;
    }

    public void setAttributeNames(List<String> attributeNames) {
        this.attributeNames = attributeNames;
    }

    public List<String> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<String> attributeValues) {
        this.attributeValues = attributeValues;
    }

    /**
     * Clean the model state
     */
    public void cleanModel() {
        this.xmlPath = "";
        this.attributeName = "";
        this.attributeValue = "";
        this.attributesCounter = 0;
        this.foundedAttributes.clear();
        this.attributeNames.clear();
        this.attributeValues.clear();
    }
}
