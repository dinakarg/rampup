/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.ndtv.action;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.apache.sling.scripting.jsp.util.JspSlingHttpServletResponseWrapper;
import org.apache.sling.scripting.jsp.util.TagUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.components.Component;
import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Style;

/**
 * The Class BaseAction.
 * @author kumarip
 *
 */
public abstract class BaseAction {

    /**
     * logger object for handling log messages.
     */
    private static final Logger LOG = LoggerFactory.getLogger(BaseAction.class);
    /**
     * To hold the page context.
     */
    private transient PageContext pageContext;
    /**
     * To hold sling request.
     */
    private transient SlingHttpServletRequest slingRequest;
    /**
     * To hold the sling response.
     */
    private transient SlingHttpServletResponse slingResponse;
    
    /*
    * To hold the sling response.
    */
   /**
    *  private transient ResourceResolver resourceResolver;
    * Avoid unused private fields such as 'resourceResolver
    * Removed resourceResolver and kept assignments with side effects
    */
  
   

    /**
     * Returns the page context.
     *
     * @return PageContext
     */
    public PageContext getPageContext() {
        return this.pageContext;
    }

    /**
     * @param pageContextArg - pageContext.
     */
    public void init(PageContext pageContextArg) {
        LOG.debug("intiating base action");
        this.pageContext = pageContextArg;
        slingRequest = TagUtil.getRequest(this.pageContext);
        slingResponse = new JspSlingHttpServletResponseWrapper(this.pageContext);
    }


    /**
     * Get the current resource object.
     *
     * @return Resource object.
     */
    public Resource getCurrentResource() {
        return slingRequest.getResource();
    }
    
    /**
     * Get the current resource object.
     *
     * @return Resource object.
     */
    public Resource getResource(String resourcePath) {
        return getResourceResolver().getResource(resourcePath);
    }

    /**
     * to get the sling request.
     *
     * @return slingRequest
     */
    public SlingHttpServletRequest getSlingRequest() {
        return slingRequest;
    }
    
    
    /**
     * to get the sling request.
     *
     * @return resourceResolver
     */
    public ResourceResolver getResourceResolver() {
        return getSlingRequest().getResourceResolver();
    }

    /**
     * to get the sling response.
     *
     * @return slingResponse.
     */
    public SlingHttpServletResponse getSlingResponse() {
        return slingResponse;
    }

    /**
     * *
     * to get the current page.
     *
     * @return currentPage
     */
    public Page getCurrentPage() {
        return (Page) pageContext.getAttribute("currentPage");

    }

    /**
     * To get the currentStyle.
     *
     * @return currentStyle
     */
    public Style getCurrentStyle() {
        return (Style) pageContext.getAttribute("currentStyle");

    }

    /**
     * To get the currentNode.
     *
     * @return currentNode
     */
    public Node getCurrentNode() {
        return (Node) pageContext.getAttribute("currentNode");
    }
    
    
    /**
     * To get the currentDesign.
     *
     * @return currentDesign
     */
    public Design getCurrentDesign() {
        return (Design) pageContext.getAttribute("currentDesign");
    }
    
    
    /** 
     * To get the resourceDesign.
     *
     * @return resourceDesign
     */
    public Design getResourceDesign() {
        return (Design) pageContext.getAttribute("resourceDesign");
    }
    
    /**
     * Gets the component.
     *
     * @return the component
     */
    public Component getComponent() {
        return (Component) pageContext.getAttribute("component");
    }

    /**
     * to get the propertyValues.
     *
     * @param propertyName - propertyName.
     * @return propertyValues.
     */
    @SuppressWarnings("deprecation")
    public String[] getPropertyValues(String propertyName) {
        Resource resource = slingRequest.getResource();
       /* String[] propertyValues = ((String[]) ResourceUtil
                .getValueMap(resource).get(propertyName, String[].class)); */
        return ((String[]) ResourceUtil
                .getValueMap(resource).get(propertyName, String[].class));
    }

    /**
     * Returns sling script helper class.
     *
     * @return SlingScriptHelper.
     */
    public SlingScriptHelper getSling() {
        return (SlingScriptHelper) getPageContext().getAttribute("sling");
    }

  
    /**
     * Sanitizes an URl, if url contains jcr:content it is replaced with
     * _jcr_content.
     *
     * @param url - The url to sanitize.
     * @return - Sanitized url.
     */
    public String sanitizeUrl(String url) {
       /* String sanitizedUrl = url.replaceAll("jcr:content", "_jcr_content"); */
        return url.replaceAll("jcr:content", "_jcr_content");
    }

    /**
     * @param node
     * @param property
     * @return propertyValue
     */
    public String getPropertyValue(Node node, String property) {
        try {
            ValueMap valMap = getSlingRequest().getResourceResolver()
                    .getResource(node.getPath()).adaptTo(ValueMap.class);
            return (String) valMap.get(property);
        } catch (RepositoryException re) {
            LOG.error("RepositoryException ", re);
            return null;
        }
    }

    /**
     * @param clazz
     * @return Object
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    public Object getService(Class clazz) {
        return getSling().getService(clazz);
    }

   

    /**
     * @param node
     * @param propertyName
     * @return propertyNameValue
     */
    protected String getNodePropertyValue(Node node, String propertyName) {
        
        String propertyNameValue = null;
        try {
            if (node.hasProperty(propertyName) && StringUtils.isNotBlank(node.getProperty(propertyName).getString())) {
                    propertyNameValue = node.getProperty(propertyName).getString();
                }
        } catch (RepositoryException re) {
            LOG.error("RepositoryException ", re);
        }
        
        return propertyNameValue;
    }

    /**
     * @param style
     * @param propertyName
     * @return propertyNameValue
     */
    protected String getStylePropertyValue(Style style, String propertyName) {
        String propertyNameValue = null;
        if (style.containsKey(propertyName) && StringUtils.isNotBlank(style.get(propertyName).toString())) {
                propertyNameValue = style.get(propertyName).toString();
            }
        
        return propertyNameValue;
    }
  
}
