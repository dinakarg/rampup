/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.ndtv.framework.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.jcr.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A collection of commonly used functions.
 */
public final class Util {

    private static final Logger LOG = LoggerFactory.getLogger(Util.class);

    /**
     * Prevents public instantiation.
     */
    private Util() {
    }

    /**
     * The empty string object.
     */
    public static final String EMPTY = "";

    /**
     * Determines if the given object is {@code null} or empty depending on the
     * semantics of the object's type. The following types are supported:
     * <ul>
     * <li>String</li>
     * <li>Collection</li>
     * <li>Map</li>
     * <li>Iterator</li>
     * <li>Enumeration</li>
     * <li>object and primitive array</li>
     * </ul>
     * If the given object is non {@code null}, and is not one of the supported
     * types, {@code false} is returned.
     *
     * @param o The object to test.
     * @return {@code true} if the given object is {@code null}, or semantically
     * empty depending on the object type; {@code false} otherwise.
     */
    public static boolean isEmpty(final Object o) {
        LOG.debug("Checking is empty ");
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            return StringUtils.isBlank((String) o);
        }
        if (o instanceof Collection< ? >) {
            return ((Collection< ? >) o).isEmpty();
        }
        if (o instanceof Map< ? , ? >) {
            return ((Map< ? , ? >) o).isEmpty();
        }
        if (o instanceof Iterator< ? >) {
            return !((Iterator< ? >) o).hasNext();
        }
        if (o instanceof Enumeration< ? >) {
            return !((Enumeration< ? >) o).hasMoreElements();
        }
        if (o.getClass().isArray()) {
            return Array.getLength(o) == 0;
        }
        return false;
    }

    /**
     * @param <T> The implied type of the iterator elements.
     * @return An iterator over an empty collection of the implied type.
     */
    public static <T> Iterator<T> emptyIterator() {
        return Collections.<T>emptySet().iterator();
    }

    /**
     * @param <T> The implied type of elements held in the returned collection.
     * @return An empty collection of elements with the implied type.
     */
    public static <T> Collection<T> emptyCollection() {
        return emptySet();
    }

    /**
     * @param <T> The implied type of elements held in the returned set.
     * @return An empty set of elements with the implied type.
     */
    public static <T> Set<T> emptySet() {
        return Collections.emptySet();
    }

    /**
     * @param <T> The implied type of elements held in the returned list.
     * @return An empty list of elements with the implied type.
     */
    public static <T> java.util.List<Object> emptyList() {
        return Collections.emptyList();
    }

    /**
     * @param <K> The implied key type.
     * @param <V> The implied value type.
     * @return An empty map of entries with implied key, and value types.
     */
    public static <K, V> Map<K, V> emptyMap() {
        return Collections.emptyMap();
    }

    /**
     * A {@code null}-safe way to retrieve a boolean value from a
     * {@link ValueMap}.
     *
     * @param p Contains the boolean property.
     * @param k The property key.
     * @return The boolean value, or {@code false} if not found.
     */
    public static boolean getBoolean(ValueMap p, String k) {
        Boolean b = p.get(k, Boolean.class);
        return b != null && b;
    }

    /**
     * @param r Request of which to get the respective session object.
     * @return The session associated with the given request object, or
     * {@code null} if unable to get session object.
     */
    public static Session sessionOf(SlingHttpServletRequest r) {
        ResourceResolver resolver = r.getResourceResolver();
        if (isEmpty(resolver)) {
            return null;
        }
        return resolver.adaptTo(Session.class);
    }

    /** Replace UTF8 characters displayed as ANSI
     *
     * @param myWeirdString Request of which to get the respective session object.
     * @return The param string
     */
    public static String replaceWeirdCharacter(String myWeirdString) {
        if (myWeirdString != null) {
            //array to hold replacements
            String[][] replacements = {
                    {"Ã€", "À"},
                    {"Ã ", "à"},
                    {"Ã ", "Á"},
                    {"Ã¡", "á"},
                    {"Ã‚", "Â"},
                    {"Ã¢", "â"},
                    {"Ãƒ", "Ã"},
                    {"Ã£", "ã"},
                    {"Ã„", "Ä"},
                    {"Ã¤", "ä"},
                    {"Ã‡", "Ç"},
                    {"Ã§", "ç"},
                    {"Ãˆ", "È"},
                    {"Ã¨", "è"},
                    {"Ã‰", "É"},
                    {"Ã©", "é"},
                    {"ÃŠ", "Ê"},
                    {"Ãª", "ê"},
                    {"Ã‹", "Ë"},
                    {"Ã«", "ë"},
                    {"ÃŒ", "Ì"},
                    {"Ã¬", "ì"},
                    {"Ã ", "Í"},
                    {"Ã­", "í"},
                    {"ÃŽ", "Î"},
                    {"Ã®", "î"},
                    {"Ã ", "Ï"},
                    {"Ã¯", "ï"},
                    {"Ã‘", "Ñ"},
                    {"Ã±", "ñ"},
                    {"Ã’", "Ò"},
                    {"Ã²", "ò"},
                    {"Ã“", "Ó"},
                    {"Ã³", "ó"},
                    {"Ã”", "Ô"},
                    {"Ã´", "ô"},
                    {"Ã•", "Õ"},
                    {"Ãµ", "õ"},
                    {"Ã–", "Ö"},
                    {"Ã¶", "ö"},
                    {"Å ", "Š"},
                    {"Å¡", "š"},
                    {"Ãš", "Ú"},
                    {"Ã¹", "ù"},
                    {"Ã›", "Û"},
                    {"Ãº", "ú"},
                    {"Ãœ", "Ü"},
                    {"Ã»", "û"},
                    {"Ã™", "Ù"},
                    {"Ã¼", "ü"},
                    {"Ã ", "Ý"},
                    {"Ã½", "ý"},
                    {"Å¸", "Ÿ"},
                    {"Ã¿", "ÿ"},
                    {"Å½", "Ž"},
                    {"Å¾", "ž"}
            };

            //loop over the array and replace
            for(String[] replacement: replacements) {
                myWeirdString = myWeirdString.replace(replacement[0], replacement[1]);
            }
        }

        return myWeirdString;
    }

   /* private static volatile Map<String, String> codes = null;

    public static synchronized String getMappedCode(String shortCode) {

        if (codes == null) {

            codes = new HashMap<String, String>();
            codes.put("sci", "Science");
            codes.put("soc", "Social");
            codes.put("ma", "Math");
            codes.put("mu", "Music");
            codes.put("wl", "Languages");
            codes.put("art", "Art");
            codes.put("rla", "Rla");
        }
        String tCode = shortCode.toLowerCase();
        if (codes.containsKey(tCode)) {
            return codes.get(tCode).toLowerCase();
        }

        return tCode;
    } */

    /**
     * Gets the url parts.
     *
     * @param url the url
     * @return the url parts
     */
   /*  public static Map<String, String> getUrlParts(String url) {
        Map<String, String> parts = new HashMap<String, String>();
        URL aURL;
        try {
            if (!StringUtils.startsWithIgnoreCase(url, "http")) {
                url = "http://" + url;
            }
            aURL = new URL(url);
            String protocol = aURL.getProtocol();
            String authority = aURL.getAuthority();
            String host = aURL.getHost();
            int port = aURL.getPort();
            String path = aURL.getPath();
            String query = aURL.getQuery();
            String filename = aURL.getFile();
            String ref = aURL.getRef();
            parts.put(Constants.PROTOCOL, protocol);
            parts.put(Constants.AUTHORITY, authority);
            parts.put(Constants.HOST, host);
            parts.put(Constants.PORT, "" + port);
            parts.put(Constants.PATH, path);
            parts.put(Constants.QUERY, query);
            parts.put(Constants.FILENAME, filename);
            parts.put(Constants.REF, ref);
        } catch (MalformedURLException e) {
            LOG.error("Error in URL", e);
        }

        return parts;
    } */

}
