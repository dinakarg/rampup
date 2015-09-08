/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.ndtv.action;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import javax.jcr.Node;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.mockito.Mockito;
import javax.jcr.Property;
import javax.jcr.Value;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.designer.Style;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.junit.Test;

/**
 * The Class BaseTest.
 * This class can be used to write testcases.
 * The testcases developed using this class can be executed
 * using sling junit servlet or can be executed using external
 * tools such as Maven or eclipse.
 */
public class BaseTest  {

    /** The page context. */
    private final PageContext pageContext;

    /** The sling http servlet request. */
    private final SlingHttpServletRequest slingHttpServletRequest;

    /** The sling http servlet response. */
    private final SlingScriptHelper slingScriptHelper;
    
    /** The sling http servlet response. */
    private final SlingHttpServletResponse slingHttpServletResponse;

    /** The jsp writer. */
    private final JspWriter jspWriter;

    /** The resource. */
    private final Resource resource;

    /** The page. */
    private final Page page;
    
    /** The property. */
    private final Property property;
    
    /** The value. */
    private final Value value;
    
    /** The node. */
    public final Node node;

    /** The style. */
    private final Style style;

    /** The map. */
    @SuppressWarnings("unchecked")
    private Map map;

    /** The value map. */
    private ValueMap valueMap;

    /**
     * Instantiates a new base test.
     */
    public BaseTest() {
    	
        super();
        pageContext = Mockito.mock(PageContext.class);
        slingHttpServletRequest = Mockito.mock(SlingHttpServletRequest.class);
        slingHttpServletResponse = Mockito.mock(SlingHttpServletResponse.class);
        slingScriptHelper = Mockito.mock(SlingScriptHelper.class);
        property = Mockito.mock(Property.class);
        value = Mockito.mock(Value.class);
        node = Mockito.mock(Node.class);
        jspWriter = Mockito.mock(JspWriter.class);
        resource = Mockito.mock(Resource.class);
        page = Mockito.mock(Page.class);
        style = Mockito.mock(Style.class);
        @SuppressWarnings("unused")
        final Map propertiesMap = Mockito.mock(HashMap.class);
        final ValueMap propertiesValueMap =
            Mockito.mock(ValueMapDecorator.class);

        Mockito.when(pageContext.getRequest()).thenReturn(
                slingHttpServletRequest);
        Mockito.when(pageContext.getResponse()).thenReturn(
                slingHttpServletResponse);
        Mockito.when(pageContext.getAttribute("sling")).thenReturn(
                (SlingScriptHelper) slingScriptHelper);
        Mockito.when(pageContext.getAttribute("currentNode")).thenReturn(
                (Node) node);
        Node nod = (Node) pageContext.getAttribute("currentNode");
       
        Mockito.when(pageContext.getOut()).thenReturn(jspWriter);
        Mockito.when(slingHttpServletRequest.getResource())
                .thenReturn((Resource) resource);
        
        Mockito.when(ResourceUtil.getValueMap(resource)).
                                      thenReturn(propertiesValueMap);
    }

    /**
     * Gets the page context.
     *
     * @return the page context
     */
    public final PageContext getPageContext() {
        return pageContext;
    }
    
    /**
     * @return SlingScriptHelper
     */
    public final SlingScriptHelper getSling() {
        return (SlingScriptHelper) getPageContext().getAttribute("sling");
    }

    /**
     * Gets the sling http servlet request.
     *
     * @return the sling http servlet request
     */
    public final SlingHttpServletRequest getSlingHttpServletRequest() {
        return slingHttpServletRequest;
    }

    /**
     * Gets the sling http servlet response.
     *
     * @return the sling http servlet response
     */
    public final SlingHttpServletResponse getSlingHttpServletResponse() {
        return slingHttpServletResponse;
    }

    /**
     * Gets the jsp writer.
     *
     * @return the jsp writer
     */
    public final JspWriter getJspWriter() {
        return jspWriter;
    }

    /**
     * Gets the resource.
     *
     * @return the resource
     */
    public final Resource getResource() {
        return resource;
    }

    /**
     * Gets the page.
     *
     * @return the page
     */
    public final Page getPage() {
        return page;
    }

    /**
     * Gets the style.
     *
     * @return the style
     */
    public final Style getStyle() {
        return style;
    }

    /**
     * Gets the map.
     *
     * @return the map
     */
    public final Map getMap() {
        return map;
    }

    /**
     * Gets the value map.
     *
     * @return the value map
     */
    public final ValueMap getValueMap() {
        return valueMap;
    }

    /**
     * Dummy Test Method.
     */
    @Test
    public final void test() {
        assertEquals("Base Test", "true", "true");
    }
}
