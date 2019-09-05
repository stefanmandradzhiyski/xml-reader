package com.snmi.view;

import java.util.List;

/**
 * XML View
 * @author Stefan Mandradzhiyski
 * @version 1.0
 */
public class XMLView {

    /**
     * Constants
     */
    private static final String SPLITTER = "----------------------------";

    /**
     * Variables
     */
    private static final String RESULT = "Founded results: %d";

    /**
     * Updating the console view
     * @param list take the founded elements list
     */
    public void showResult(List<String> list) {
        list.forEach(System.out::println);
        System.out.println();
        System.out.printf(RESULT, list.size());
        System.out.println();
        System.out.println(SPLITTER);
    }

}
