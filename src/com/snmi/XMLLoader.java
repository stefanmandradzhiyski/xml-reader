package com.snmi;

import com.snmi.model.XMLData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * XML Loader which act as Facade
 * @author Stefan Mandradzhiyski
 * @version 1.0
 */
public class XMLLoader {

    /**
     * Constants
     */
    private static final String FIELD = "Field";
    private static final String ROOT = "XML Root element : %s";
    private static final String SPLITTER = "----------------------------";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String TYPE = "type";
    private static final String FOUNDED = "Attribute '%s' value is '%s' with text '%s'";
    private static final String INCORRECT_OPERATION = "Operation hasn't implemented yet. ";

    /**
     * Main search in xml method
     * @param model take the xml data model
     * @param operationType take the operation type
     * @return filled list with all founded elements
     */
    public List<String> searchInXML(XMLData model, OperationType operationType) {
        List<String> foundedAttributes = new ArrayList<>();
        NodeList nodeList = takeNode(model.getXmlPath());
        loopThroughNode(nodeList, model, foundedAttributes, operationType);
        return foundedAttributes;
    }

    /**
     * Loading the xml file
     * @param xmlPath take the xml file path name
     * @return document
     */
    private Document loadXMLFile(String xmlPath) {
        Document document = null;
        try {
            File fXmlFile = new File(xmlPath);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(fXmlFile);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * Create a node list
     * @param xmlPath take the xml file path
     * @return the created node list
     */
    private NodeList takeNode(String xmlPath) {
        Document document = loadXMLFile(xmlPath);
        document.getDocumentElement().normalize();
        System.out.println();
        System.out.printf(ROOT, document.getDocumentElement().getNodeName());
        System.out.println();
        System.out.println(SPLITTER);
        return document.getElementsByTagName(FIELD);
    }

    /**
     * Loop though node list and fill the list with founded results
     * @param nodeList take the node list
     * @param model take the model
     * @param foundedAttributes take the founded results list
     * @param operationType take the operation type
     */
    private void loopThroughNode(NodeList nodeList, XMLData model,
                                 List<String> foundedAttributes, OperationType operationType) {
        for (int current = 0; current < nodeList.getLength(); current++) {
            Node node = nodeList.item(current);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String nameXML = eElement.getAttribute(NAME);
                String descriptionXML = eElement.getAttribute(DESCRIPTION);
                String typeXML = eElement.getAttribute(TYPE);
                String textXML = eElement.getTextContent();

                switch (operationType) {
                    case SINGLE:
                        fillListWithSingleMatches(model, nameXML, descriptionXML, typeXML, textXML, foundedAttributes);
                        break;
                    case MULTIPLE:
                        fillListWithMultipleMatches(model, nameXML, descriptionXML, typeXML, textXML, foundedAttributes);
                        break;
                    default:
                        throw new IllegalArgumentException(INCORRECT_OPERATION);
                }
            }
        }
    }

    /**
     * Search single matches between attributes name and values and add it to the list
     * @param model take the model
     * @param nameXML take the name content
     * @param descriptionXML take the description content
     * @param typeXML take the type content
     * @param textXML take the text content
     * @param foundedAttributes take list for founded elements
     */
    private void fillListWithSingleMatches(XMLData model, String nameXML, String descriptionXML,
                               String typeXML, String textXML, List<String> foundedAttributes) {
        if (NAME.equals(model.getAttributeName()) && nameXML.toLowerCase().equals(model.getAttributeValue())){
            String founded = String.format(FOUNDED, model.getAttributeName(), nameXML, textXML);
            foundedAttributes.add(founded);
        } else if (DESCRIPTION.equals(model.getAttributeName()) && descriptionXML.toLowerCase().equals(model.getAttributeValue())) {
            String founded = String.format(FOUNDED, model.getAttributeName(), descriptionXML, textXML);
            foundedAttributes.add(founded);
        } else if (TYPE.equals(model.getAttributeName()) && typeXML.toLowerCase().equals(model.getAttributeValue())) {
            String founded = String.format(FOUNDED, model.getAttributeName(), typeXML, textXML);
            foundedAttributes.add(founded);
        }
    }

    /**
     * Search multiple matches between attributes name and values and add it to the list
     * @param model take the model
     * @param nameXML take the name content
     * @param descriptionXML take the description content
     * @param typeXML take the type content
     * @param textXML take the text content
     * @param foundedAttributes take list for founded elements
     */
    private void fillListWithMultipleMatches(XMLData model, String nameXML, String descriptionXML,
                                             String typeXML, String textXML, List<String> foundedAttributes) {
        Set<String> uniqueAttributeNames = new HashSet<>(model.getAttributeNames());
        for (String attributeName : uniqueAttributeNames) {
            for (String attributeValue : model.getAttributeValues()) {
                if (NAME.equals(attributeName) && nameXML.toLowerCase().equals(attributeValue)){
                    String founded = String.format(FOUNDED, attributeName, nameXML, textXML);
                    foundedAttributes.add(founded);
                } else if (DESCRIPTION.equals(attributeName) && descriptionXML.toLowerCase().equals(attributeValue)) {
                    String founded = String.format(FOUNDED, attributeName, descriptionXML, textXML);
                    foundedAttributes.add(founded);
                } else if (TYPE.equals(attributeName) && typeXML.toLowerCase().equals(attributeValue)) {
                    String founded = String.format(FOUNDED, attributeName, typeXML, textXML);
                    foundedAttributes.add(founded);
                }
            }
        }
    }

}
