package com.snmi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * XML Validator utility class
 * @author Stefan Mandradzhiyski
 * @version 1.0
 */
public class XMLValidator {

    /**
     * Constants
     */
    private static final String ENTER_XML = "Please, enter valid name of xml file: ";
    private static final String XML_PATH = "task.xml";
    private static final String ENTER_ATTRIBUTE = "Please, enter valid attribute by which you will look for (name/description/type): ";
    private static final String NAME_ATTRIBUTE = "name";
    private static final String DESCRIPTION_ATTRIBUTE = "description";
    private static final String TYPE_ATTRIBUTE = "type";
    private static final String ENTER_WORD = "Please, enter a word to look for in the selected attribute: ";
    private static final String ENTER_COUNTER = "Please, enter the number of attributes that you want to search for (between 1-3 including): ";
    private static final String INCORRECT_COUNTER = "Please, input correct number between 1 and 3(including)";
    private static final String ENTER_ATTRIBUTE_NAME = "Choose attribute %d (name/description/type): ";
    private static final String ENTER_ATTRIBUTE_VALUE = "Searching value in attribute %s :";

    /**
     * Class variable
     */
    private static Scanner console = new Scanner(System.in);

    /**
     * Private default constructor
     */
    private XMLValidator() {}

    /**
     * Validate the xml file name
     * @return validated xml file name
     */
    public static String validateXMLName() {
        String path;
        do{
            System.out.print(ENTER_XML);
            path = console.nextLine().toLowerCase().trim();
        } while (!XML_PATH.equals(path));

        return path;
    }

    /**
     * Validate the attribute name
     * @return validated attribute name
     */
    public static String validateAttribute() {
        String attributeName;
        do{
            System.out.print(ENTER_ATTRIBUTE);
            attributeName = console.nextLine().toLowerCase().trim();
        } while (isAttributeValid(attributeName));

        return attributeName;
    }

    /**
     * Validate the attribute value
     * @return validated attribute value
     */
    public static String validateAttributeValue() {
        String attributeValue;
        do {
            System.out.print(ENTER_WORD);
            attributeValue = console.nextLine().toLowerCase().trim();
        } while (attributeValue.length() < 1);
        return attributeValue;
    }

    /**
     * Attribute name validation
     * @param attribute take the input attribute
     * @return boolean result after validation check
     */
    private static boolean isAttributeValid(String attribute) {
        return  !NAME_ATTRIBUTE.equals(attribute) &&
                !DESCRIPTION_ATTRIBUTE.equals(attribute) &&
                !TYPE_ATTRIBUTE.equals(attribute);
    }

    /**
     * Validate the attribute counter
     * @return the attribute counter
     */
    public static int validateAttributeCounter() {
        int attributesCounter = 0;
        do{
            System.out.print(ENTER_COUNTER);
            try{
                attributesCounter = Integer.parseInt(console.nextLine().trim());
            }
            catch(Exception e){
                System.out.println(INCORRECT_COUNTER);
            }
        } while (attributesCounter <= 0 || attributesCounter > 3);
        return attributesCounter;
    }

    /**
     * Validate the attribute names list
     * @param counter take the counter of desired attributes
     * @return the attribute names list
     */
    public static List<String> validateAttributeNames(int counter) {
        List<String> attributeNames = new ArrayList<>();
        for (int i = 1; i <= counter; i++){
            String attribute;
            do{
                System.out.printf(ENTER_ATTRIBUTE_NAME, i);
                attribute = console.nextLine().toLowerCase().trim();
            } while (isAttributeValid(attribute));
            attributeNames.add(attribute);
        }
        return attributeNames;
    }

    /**
     * Validate the attribute values list
     * @param attributeNames take the attribute names list
     * @return return the filled attribute values list
     */
    public static List<String> validateAttributeValues(List<String> attributeNames) {
        List<String> attributeValues = new ArrayList<>();
        for (String attributeName : attributeNames) {
            System.out.printf(ENTER_ATTRIBUTE_VALUE, attributeName);
            attributeValues.add(console.nextLine().toLowerCase().trim());
        }
        return attributeValues;
    }

}
