package com.ndtv.util;

import java.util.List;
import java.util.ArrayList;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class CommonUtils. It include reusable methods.
 *
 * @author GDINAKAR
 */
public class CommonUtils {

    /** The Constant EMPTY_STRING. */
    private static final String EMPTY_STRING = "";

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory
            .getLogger(CommonUtils.class);
    /**
     * Instantiates a new common utils.
     */
    protected CommonUtils() {
    }

    /**
     * Gets the node property value.
     *
     * @param node
     *            the node
     * @param propertyName
     *            the property name
     * @return the node property value
     */
    public static String getNodePropertyValue(final Node node,
            final String propertyName) {
        String propertyValue = null;
        try {
            if (node.hasProperty(propertyName)) {
                propertyValue = node.getProperty(propertyName).getString();
            }
        } catch (RepositoryException e) {
            LOG.error(
                    "Error while retrieving the property value from the node "
                            + e.getMessage(), e);
        }
        return propertyValue;

    }

    /**
     * Gets the node property values.
     *
     * @param node the node
     * @param propertyName the property name
     * @return the node property values
     */
    public static List<String> getNodePropertyValues(final Node node,
            final String propertyName) {
        Value[] propertyValues = {};
        List<String> listOfValues = null;
        try {
            propertyValues = node.getProperty(propertyName).getValues();
            if (propertyValues.length > 0) {
                listOfValues = new ArrayList<String>();
                for (Value str : propertyValues) {
                    listOfValues.add(str.toString());
                }
            }

        } catch (RepositoryException e) {
            LOG.error(
                    "Error while retrieving the property value from the node "
                            + e.getMessage(), e);
        }

        return listOfValues;
    }

    /**
     * Return empty if null.
     *
     * @param propertyValue
     *            the property value
     * @return the string
     */
    public static String returnEmptyIfNull(final String propertyValue) {
        if (propertyValue == null) {
            return EMPTY_STRING;
        }
        return propertyValue.trim();
    }

}
