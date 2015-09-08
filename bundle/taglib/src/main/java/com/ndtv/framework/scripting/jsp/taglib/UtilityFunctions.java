/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.ndtv.framework.scripting.jsp.taglib;


import static com.ndtv.framework.util.Util.emptyIterator;
import static com.ndtv.framework.util.Util.isEmpty;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.jackrabbit.api.security.user.User;
import org.apache.jackrabbit.api.security.user.Group;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.day.cq.i18n.I18n;
import com.day.cq.wcm.api.components.EditContext;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.WCMMode;
import com.day.cq.wcm.foundation.ELEvaluator;
import com.day.cq.wcm.foundation.Image;

import com.ndtv.framework.util.Constants;
//import com.pearson.imt.pearsonsampling.wcm.util.LinkUtilities;
//import com.day.image.Layer;
//import com.day.text.Text;


/**
 * Contains a collection of static methods to be used as custom tag functions.
 *
 * @author unascribed
 * @author Scott Flesher
 */
public class UtilityFunctions {

	/**
	 * Logger.
	 */
	private static final Logger log = LoggerFactory
			.getLogger(UtilityFunctions.class);
	/**
     * The SITE_PAGE_DEPTH.
     */
    public static final int SITE_PAGE_DEPTH = 6;
    /**
     * The LANGUAGE_PAGE_DEPTH.
     */
    public static final int LANGUAGE_PAGE_DEPTH = 5;
    /**
     * The PROP_SHORT_TITLE.
     */
    public static final String PROP_SHORT_TITLE = "jcr:title";
    /**
     * The PROP_LONG_TITLE.
     */
    public static final String PROP_LONG_TITLE = "pageTitle";
    /**
     * The PROP_BROWSER_TITLE.
     */
    public static final String PROP_BROWSER_TITLE = "browserTitle";
    /**
     * The PROP_NAVIGATION_TITLE.
     */
    public static final String PROP_NAVIGATION_TITLE = "navTitle";
    /**
     * The PROP_H1_TITLE.
     */
    public static final String PROP_H1_TITLE = "h1Title";
    /**
     * The COMPONENT_PATH_START.
     */
    public static final String COMPONENT_PATH_START = "jcr:content";
    /**
     * The FORBIDDEN_ID_EXP.
     */
    public static final String FORBIDDEN_ID_EXP = "[^a-zA-Z0-9_-]";
    /**
     * The SITE_DEFAULT_LANGUAGE.
     */
    public static final String SITE_DEFAULT_LANGUAGE = "en";
    /**
     * The USER_GENERATED_PATH.
     */
    public static final String USER_GENERATED_PATH = "/content/usergenerated";
    /**
     * The PROP_LANG_CONFIG_PATH.
     */
    public static final String PROP_LANG_CONFIG_PATH = "langConfigPath";
    /**
     * The PROP_SITE_CONFIG_NAME.
     */
    public static final String PROP_SITE_CONFIG_NAME = "siteConfigName";
    /**
     * The BROWSER_PAGE_TITLE_TOKEN.
     */
    public static final String BROWSER_PAGE_TITLE_TOKEN = "<!--[{[browserpagetitle]}]-->";
    /**
     * The PAGE_DESCRIPTION_TOKEN.
     */
    public static final String PAGE_DESCRIPTION_TOKEN = "<!--[{[pagedescription]}]-->";
    /**
     * The PAGE_KEYWORDS_TOKEN.
     */
    public static final String PAGE_KEYWORDS_TOKEN = "<!--[{[pagekeywords]}]-->";
    
    /** The Constant ARTICLE_CONTENT_WELL. */
    public static final String ARTICLE_CONTENT_WELL = "content-well";
    
    /** The Constant ARTICLE_SMALL_MEDIA. */
    public static final String ARTICLE_SMALL_MEDIA = "content-small-media";
    
    /** The Constant SLING_RESOURCE_TYPE. */
    public static final String SLING_RESOURCE_TYPE = "sling:resourceType";
    
    /** The Constant PHOTO_COMPONENT. */
    public static final String PHOTO_COMPONENT = "photo";
    
    /** The Constant SMALL_PHOTO_COMPONENT. */
    public static final String SMALL_PHOTO_COMPONENT = "articletextimage";
    
    /** The Constant REFERENCE_COMPONENT. */
    public static final String REFERENCE_COMPONENT = "reference";
    
    /** The Constant FILE_REFERENCE. */
    public static final String FILE_REFERENCE = "fileReference";
    
    /** The Constant PATH. */
    public static final String PATH = "path";
    
    /** The Constant SLIDESHOW_CONTAINER. */
    public static final String SLIDESHOW_CONTAINER = "slideshow-container";
    
    /** The Constant IMAGES. */
    public static final String IMAGES = "images";
    
    /** The Constant TEMPLATE_TYPE. */
    public static final String TEMPLATE_TYPE = "templateType";
    
    /** The Constant LARGE_MEDIA. */
    public static final String LARGE_MEDIA = "largemedia";
    
    /** The Constant SMALL_MEDIA. */
    public static final String SMALL_MEDIA = "smallmedia";
    
    /** The Constant LANGUAGE. */
    public static final String LANGUAGE = "language";
    
    /** The Constant SITE. */
    public static final String SITE = "site";
    
    /**
     * Prevents public instantiation.
     */
    private UtilityFunctions() { }

    /**
     * Indicates the if the given key is hashed to a non-empty value in the
     * given value map.  If so, the key is returned, otherwise {@code null} is
     * returned.  If the provided value map is {@code null}, {@code null} is
     * immediately returned.
     *
     * @param pageProperties The value map from which to check the given key.
     * @param key The key to check in the given value map.
     * @return The given key or {@code null} depending whether the key is found
     * in the given value map.
     */
    private static String getPropertySource(
        final ValueMap pageProperties, final String key) {

        if (pageProperties == null) {
        	return null; 
        	}

        String value = pageProperties.get(key, String.class);
        if (StringUtils.isEmpty(value)) {
        	return null;
        }

        return key;
    }

    /**
     * Gets the property.
     *
     * @param pageProperties the page properties
     * @param key the key
     * @param defaultValue the default value
     * @return property
     */
    private static String getProperty(
        final ValueMap pageProperties,
        final String key,
        final String defaultValue) {

        String source = getPropertySource(pageProperties, key);
        if (StringUtils.isEmpty(source)) {
            return defaultValue;
        }
        return pageProperties.get(source, String.class);
    }

    /**
     * Gets the property source fallback.
     *
     * @param pageProperties the page properties
     * @param key the key
     * @param fallbackKey the fallback key
     * @return propertySourceFallback
     */
    private static String getPropertySourceFallback(
        final ValueMap pageProperties,
        final String key,
        final String fallbackKey) {
        
        if (pageProperties == null) {
        	return null; 
        }

        String value = pageProperties.get(key, String.class);
        if (StringUtils.isNotEmpty(value)) {
        	return key;
        }

        value = pageProperties.get(fallbackKey, String.class);
        if (StringUtils.isNotEmpty(value)) {
        	return fallbackKey;
        }

        return null;
    }

    /**
     * Gets the property fallback.
     *
     * @param pageProperties the page properties
     * @param key the key
     * @param fallbackKey the fallback key
     * @param defaultValue the default value
     * @return PropertyFallback
     */
    private static String getPropertyFallback(
        final ValueMap pageProperties,
        final String key,
        final String fallbackKey,
        final String defaultValue) {

        String source = getPropertySourceFallback(pageProperties, key, fallbackKey);

        if (StringUtils.isNotEmpty(source)) {
            return pageProperties.get(source, String.class);
        }

        return defaultValue;
    }

    /**
     * Gets the page title.
     *
     * @param pageProperties the page properties
     * @param defaultValue the default value
     * @return pageTitle
     */
    public static String getPageTitle(ValueMap pageProperties, String defaultValue) {
        return getPropertyFallback(pageProperties, PROP_LONG_TITLE, PROP_SHORT_TITLE, defaultValue);
    }

    /**
     * Gets the short title.
     *
     * @param pageProperties the page properties
     * @param defaultValue the default value
     * @return shortTitle
     */
    public static String getShortTitle(ValueMap pageProperties, String defaultValue) {
        return getProperty(pageProperties, PROP_SHORT_TITLE, defaultValue);
    }

    /**
     * Gets the navigation title.
     *
     * @param pageProperties the page properties
     * @param defaultValue the default value
     * @return navigationTitle
     */
    public static String getNavigationTitle(ValueMap pageProperties, String defaultValue) {
        return getPropertyFallback(pageProperties, PROP_NAVIGATION_TITLE, PROP_SHORT_TITLE, defaultValue);
    }

    /**
     * Retrieves the indicated property from the given resource.  If either the
     * given resource or key are {@code null} or empty, {@code null} is
     * returned.  If the given type is empty, it will be defaulted to
     *
     * @param resource The resource from which to retrieve the property.
     * @param key The property key.
     * @param typeName The expected type of the indicated property as a fully
     * qualified class name.
     * @return The indicated property from the given resource, or {@code null}
     * if not available.
     * @throws ClassNotFoundException if the indicated type is not a valid
     * class.
     * {@code String.class}.
     */
    public static Object getProperty(
        Resource resource, String key, String typeName)
        throws ClassNotFoundException {

        if (isEmpty(resource) || isEmpty(key)) {        	
        	return null;
        }
        Class<?> type;
        if (isEmpty(typeName)) {
            type = String.class;
        } else {
            type = Class.forName(typeName);
        }

        ValueMap properties = resource.adaptTo(ValueMap.class);
        return properties.get(key, type);
    }

    /**
     * Gets the browser title.
     *
     * @param pageProperties the page properties
     * @param defaultValue the default value
     * @return browserTitle
     */
    public static String getBrowserTitle(ValueMap pageProperties, String defaultValue) {
        if (pageProperties == null) {
        	return defaultValue;
        }
        String browserTitle = pageProperties.get(PROP_BROWSER_TITLE, String.class);
        return
            StringUtils.isNotEmpty(browserTitle) ?
            browserTitle :
            getPageTitle(pageProperties, defaultValue);
    }

    /**
     * Gets the h1 title.
     *
     * @param pageProperties the page properties
     * @param defaultValue the default value
     * @return H1Title
     */
    public static String getH1Title(ValueMap pageProperties, String defaultValue) {
        if (pageProperties == null) {
        	return defaultValue;
        }
        String h1Title = pageProperties.get(PROP_H1_TITLE, String.class);
        return
            StringUtils.isNotEmpty(h1Title) ?
            pageProperties.get(PROP_H1_TITLE, String.class) :
            getPageTitle(pageProperties, defaultValue);
    }

    /**
     * Gets the component relative path.
     *
     * @param path the path
     * @return ComponentRelativePath
     */
    public static String getComponentRelativePath(String path) {
        path = StringUtils.trimToEmpty(path);
        if (path.endsWith(COMPONENT_PATH_START)) {
        	return "";
        }
        if (path.indexOf(COMPONENT_PATH_START) > 0) {
            int index = path.indexOf(COMPONENT_PATH_START);
            return path.substring(index + COMPONENT_PATH_START.length() + 1);
        }
        return path;
    }

    /**
     * Parses the document safe id.
     *
     * @param str the str
     * @param delim the delim
     * @return DocumentSafeId
     */
    public static String parseDocumentSafeId(String str, String delim) {
        delim = delim.replaceAll(FORBIDDEN_ID_EXP, "");
        str = str.replaceAll(FORBIDDEN_ID_EXP, delim);

        while (str.startsWith(delim)) {
            str = str.replaceFirst(delim, "");
        }

        /* XML/HTML DTDs require a letter as the first character for the id attribute*/
        if (!Character.toString(str.charAt(0)).matches("[a-zA-Z]")) {
            str = "a" + str;
        }

        return str.replaceAll(FORBIDDEN_ID_EXP, delim);
    }

    /**
     * Gets the component div id.
     *
     * @param resource the resource
     * @param delim the delim
     * @return ComponentDivId
     */
    public static String getComponentDivId(Resource resource, String delim) {
        String id = null;

        if (resource != null) {
            delim = delim.replaceAll(FORBIDDEN_ID_EXP, "");
            id = getComponentRelativePath(resource.getPath());
            id = parseDocumentSafeId(id, delim);
        }

        return id;
    }

    /**
     * Gets the component div id.
     *
     * @param resource the resource
     * @return componentDivId
     */
    public static String getComponentDivId(Resource resource) {
        return getComponentDivId(resource, "_");
    }



    /**
     * Wrapper for {@link StringEscapeUtils#escapeHtml(String)}.
     *
     * @param html The HTML string to escape.
     * @return the result of the wrapped method call.
     */
    public static String escapeHtml(String html) {
        return StringEscapeUtils.escapeHtml(html);
    }

    /**
     * Escape property.
     *
     * @param p The page from which to retrieve the indicated page property.
     * @param key The key used to look up the value to return.
     * @return The value mapped to the given key in the provided map as a
     * string.
     */
    public static String escapeProperty(Page p, String key) {
        Object value = p.getProperties().get(key);
        return escapeHtml(value == null ? "" : value.toString());
    }

    /**
     * Gets the keywords.
     *
     * @param p The page from which to retrieve keywords.
     * @return A comma separated list of keywords from the given page node, and
     * the site config page (the current pageâ€™s level SITE_PAGE_DEPTH ancestor).
     * @throws RepositoryException the repository exception
     */
    public static String getKeywords(Page p) throws RepositoryException {
        Set<String> tags = new HashSet<String>();
        //tags.addAll( getPageKeywords( p ) );
       // tags.addAll( getSiteConfigKeywords( p ) );

        StringBuilder tagList = new StringBuilder();
        for (String tag : tags) {
            if (tagList.length() > 0) {
            	tagList.append(", ");
            }
            tagList.append(tag);
        }

        return escapeHtml(tagList.toString());
    }

    /**
     * Provides the title for the given page by looking at the following.
     * locations (returns the first found):
     * <ul>
     *   <li>{@code getTitle} property of given page</li>
     *   <li>{@code jcr:title} property of given page</li>
     *   <li>provided default</li>
     * </ul>
     *
     * @param page The page to get the title for.
     * @param d3fault The default title, if the given page does not provides its
     * own title.
     * @return The title of the given page for use in the browser &lt;title&gt;
     * tag.
     */
    public static String getTitle(Page page, String d3fault) {
        String title = page.getTitle();
        if (!StringUtils.isEmpty(title)) {
        	return title;
        }
        Object value = page.getProperties().get(PROP_SHORT_TITLE);
        title = value == null ? "" : value.toString();
        if (!StringUtils.isEmpty(title)) {
        	return title;
        }
        return d3fault;
    }

    /**
     * Provides the title for the given page by looking at the following.
     * locations (returns the first found):
     * <ul>
     *   <li>{@code browserTitle} property of given page</li>
     *   <li>{@code pageTitle} property of given page</li>
     *   <li>{@code jcr:title} property of given page</li>
     *   <li>provided default</li>
     * </ul>
     *
     * @param page The page to get the title for.
     * @param defaultTitle The default title, if the given page does not
     * provide its own title.
     * @return The title of the given page for use in the browser &lt;title&gt;
     * tag.
     */
    public static String getPageBrowserTitle(Page page, String defaultTitle) {
         return escapeHtml(getBrowserTitle(page.getProperties(), defaultTitle));
    }
    
    /**
     * Provides the description for the given page by getting the jcr:description property.
     *
     * @param page The page to get the title for.
     * @return The description of the given page for use in the HTML head
     */
    public static String getPageDescription(Page page) {
        final String retVal;
        if (page != null) {
            retVal = escapeHtml(getProperty(page.getProperties(), "jcr:description", ""));
        } else {
            retVal = "";
        }
        return retVal;
    }



    /**
     * Gets the redirect url.
     *
     * @param p The page from which to get the configured redirect URL.
     * @return The redirect URL configured for the given page.  If not
     * configured, {@code null} is returned.
     */
    public static String getRedirectUrl(Page p) {
        return p.getProperties().get("redirectTarget", String.class);
    }

    /**
     * Checks if is redirect.
     *
     * @param p The page in question.
     * @return {@code true} if the given page has been configured with a
     * redirect URL; {@code false} otherwise.
     */
    public static boolean isRedirect(Page p) {
        return !StringUtils.isEmpty(getRedirectUrl(p));
    }

    /**
     * Show header.
     *
     * @param p The page in question.
     * @return {@code true} if the header should be shown for the given page;
     * {@code false} otherwise.  Inspects the "hideHeader" property of the given
     * page.  If the property is not set, {@code true} is returned.
     */
    public static boolean showHeader(Page p) {
        Boolean hide = p.getProperties().get("hideHeader", Boolean.class);
        return hide == null || !hide;
    }

    /**
     * Show footer.
     *
     * @param p The page in question.
     * @return {@code true} if the footer should be shown for the given page;
     * {@code false} otherwise.  Inspects the "hideFooter" property of the given
     * page.  If the property is not set, {@code true} is returned.
     */
    public static boolean showFooter(Page p) {
        Boolean hide = p.getProperties().get("hideFooter", Boolean.class);
        return hide == null || !hide;
    }

    /**
     * Show right rail.
     *
     * @param p The page in question.
     * @return {@code true} if the Right Rail should be shown for the given page;
     * {@code false} otherwise.  Inspects the "hideRightRail" property of the given
     * page.  If the property is not set, {@code true} is returned.
     */
    public static boolean showRightRail(Page p) {
        Boolean hide = p.getProperties().get("hideRightRail", Boolean.class);
        return hide == null || !hide;
    }

    /**
     * Show left navigation.
     *
     * @param p The page in question.
     * @return {@code true} if the Left Nav should be shown for the given page;
     * {@code false} otherwise.  Inspects the "hideLeftNavigation" property of the given
     * page.  If the property is not set, {@code true} is returned.
     */
    public static boolean showLeftNavigation(Page p) {
        Boolean hide = p.getProperties().get("hideLeftNavigation", Boolean.class);
        return hide == null || !hide;
    }
    
    /**
     * Show bread crumb.
     *
     * @param p The page in question.
     * @return {@code true} if the BreadCrumb should be shown for the given page;
     * {@code false} otherwise.  Inspects the "hideBreadCrumb" property of the given
     * page.  If the property is not set, {@code true} is returned.
     */
    public static boolean showBreadCrumb(Page p) {
        Boolean hide = p.getProperties().get("hideBreadCrumb", Boolean.class);
        return hide == null || !hide;
    }


    /**
     * Show page title.
     *
     * @param p The page in question.
     * @return {@code true} if the Page Title Component should be shown for the given page;
     * {@code false} otherwise.  Inspects the "hidePageTitle" property of the given
     * page.  If the property is not set, {@code true} is returned.
     */
    public static boolean showPageTitle(Page p) {
        Boolean hide = p.getProperties().get("hidePageTitle", Boolean.class);
        return hide == null || !hide;
    }

        /**
         * Show container border.
         *
         * @param p The page in question.
         * @return {@code true} if the border should display around a parsys;
         * {@code false} otherwise.  Inspects the "showContainerBorder" property of the given
         * page.  If the property is not set, {@code true} is returned.
         */
    public static boolean showContainerBorder(Page p) {
    	if (p == null) {
    		return false;
    	}
        Boolean show = p.getProperties().get("showContainerBorder", Boolean.class);
        
        if (show == null) {
        	return false;
        }
        return show;
    }
    
    /**
     * Indicates whether the given page is a site root, or "level SITE_PAGE_DEPTH" page.
     * Accounts for the fact the getDepth treats /content is level 1, but 
     * getAbsoluteParent treats it as 0.
     *
     * @param p The page in question.
     * @return {@code true} if the given page is a site root, or "level SITE_PAGE_DEPTH" page;
     * {@code false} otherwise.
     */
    public static boolean isSiteRoot(Page p) {
        return (p.getDepth() == SITE_PAGE_DEPTH + 1);
    }
    
    /**
     * Gets the resource.
     *
     * @param page The page from which to retrieve the content.
     * @param path The path under the given page's content resource from which
     * to retrieve the content.  Should not be prefixed with slash ( "/" ).
     * @return The content resource of the given page under the given path.
     */
    public static Resource getResource(Page page, String path) {

        if (page == null) {
        	return null;
        }
        if (StringUtils.isEmpty(path)) {
        	return page.getContentResource();
        }

        Resource content = page.getContentResource();
        return content.getChild(path);
    }

    /**
     * Gets the resource.
     *
     * @param resource The returned node will be under the given path,
     * relative to this resource.
     * @param path The relative path under the provided resource where to
     * retrieve the node.
     * @return The node under the given path relative to the path of the
     * provided resource. If the provided resource is {@code null}, {@code null}
     * is returned.  If the given path is empty, the provided resource is
     * returned.
     */
    public static Resource getResource(Resource resource, String path) {
        if (resource == null) {
        	return null;
        }
        if (StringUtils.isEmpty(path)) {
        	return resource;
        }
        return resource.getChild(path);
    }

    /**
     * Gets the resource.
     *
     * @param request The current request. If {@code null}, {@code null} is
     * returned.
     * @param absolutePath The absolute path of a resource to return. If
     * @return The resource resolved from the given absolute path.
     * {@code null} or empty, {@code null} is returned.
     */
    public static Resource getResource(
        SlingHttpServletRequest request,
        String absolutePath) {

        if (request == null || StringUtils.isEmpty(absolutePath)) {
            return null;
        }
        ResourceResolver resolver = request.getResourceResolver();
        return resolver.getResource(absolutePath);
    }

    /**
     * Gets the children.
     *
     * @param r From which to get the children.
     * @return An iterator over the child nodes of the given resource. If the
     * given resource is {@code null}, an empty iterator is returned.
     */
    public static Iterator<Resource> getChildren(Resource r) {
        if (r == null) {
        	return emptyIterator();
        }
        return r.listChildren();
    }

    /**
     * Gets the base page redirect response.
     *
     * @param request the request
     * @param currentPage the current page
     * @param pageContext the page context
     * @return basePageRedirectResponse
     */
    public static  SlingHttpServletResponse getBasePageRedirectResponse(
        SlingHttpServletRequest request,
        Page currentPage,
        PageContext pageContext) {

    	SlingHttpServletResponse response = (SlingHttpServletResponse) pageContext.getResponse();
        WCMMode mode = WCMMode.fromRequest(request);
		boolean show = mode == WCMMode.EDIT;
		boolean design = mode == WCMMode.DESIGN;

		if (!show && !design) {
		    // read the redirect target from the 'page properties' and perform the redirect
		    String location = currentPage.getProperties().get("redirectTarget", String.class);
		    // resolve variables in path
		    location = ELEvaluator.evaluate(location, request, pageContext);
		    if (!StringUtils.isEmpty(location)) {
		        // check for recursion
		        if (!location.equals(currentPage.getPath())) {
		        	response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		        	response.setHeader("Location", location);
		        	response.setHeader("Connection", "close");
		        }
		    }
		}
		return response;
	}

    /**
     * Creates a valid href or link or URL from typical CQ link storage formats.
     * a simple function that converts an authored entered link to a valid link
     * for href use or in a redirect.
     * The function proxies to LinkHelper.createValidHref.
     *
     * @param request the request
     * @param page the page
     * @return the resource path
     */
  /*  public static String createValidLink( String link, String defaultLink ) {
    	log.debug( "[createValidLink] {}", link );
        String href = defaultLink;
        if ( StringUtils.isNotEmpty( link ) ) {
            href = LinkUtilities.createValidLink(link);
        }
        log.debug( "[createValidLink] Returning {}", href );
        return href;
    } */

 /*   public static String escapeDayUrl( String url, boolean standardEscape ) {
        if ( StringUtils.isEmpty( url ) ) return "";
        String result = url;
        result = result.replace( "jcr:content", ( "_jcr_content" ) );
        if ( standardEscape ) result = Text.escape( url, '%', true );
        return result;
    }*/

    /**
     * @param request The current request
     * @param page The page of which to get the resource path.
     * @return Using the resource resolver from the given request, will return
     * teh resource path for the given page.
     */
    private synchronized static String getResourcePath(
        SlingHttpServletRequest request,
        Page page) {

        String pagePath = page.getPath();
        ResourceResolver resolver = request.getResourceResolver();

        return resolver.map(pagePath);
    }

    /**
     * Gets the component base.
     *
     * @param uri The URI to CRX resource.
     * @return The "base" form of the indicated resource.  The extension and any
     * selectors will be removed. Example: "/path/to/resource.printable.html"
     * would return as "/path/to/resource"
     */
    public static String getComponentBase(String uri) {
        int lastSlash = uri.lastIndexOf("/");
        String resource = uri.substring(lastSlash + 1);
        StringTokenizer tokenizer = new StringTokenizer(resource, ".");
        return uri.substring(0, lastSlash + 1) + tokenizer.nextToken();
    }

    /**
     * Gets the canonical url.
     *
     * @param r The current request.
     * @param p The page from which to get the canonical URL.
     * @return The canonical URL of the given page if set. Otherwise,
     * {@code null} is returned.
     */
    public static String getCanonicalUrl(SlingHttpServletRequest r, Page p) {
        return "http://www.pearson.com" + getResourcePath(r, p) + ".html";
    }

    /**
     * Indicates whether currently in author mode or not.
     * @param context The current page context.
     * @return {@code true} if in author mode; {@code false} otherwise.
     */
    public static boolean isAuthorMode(PageContext context) {
        Boolean isEditMode = (Boolean) context.getAttribute("show");
        if (isEditMode == null) {
        	isEditMode = new Boolean(false);
        }
        Boolean isDesignMode = (Boolean) context.getAttribute("design");
        if (isDesignMode == null) {
        	isDesignMode = new Boolean(false);
        }
        return isEditMode || isDesignMode;
    }


    /**
     * Adapt to value map.
     *
     * @param resource To adapt to a value map.
     * @return The value map representation of the given resource.
     */
    public static ValueMap adaptToValueMap(Resource resource) {
        if (resource == null) {
        	return null;
        }
        return resource.adaptTo(ValueMap.class);
    }

    /**
     * Indicates if the given image matches the provided dimensions.
     *
     * @param link the link
     * @return {@code true} if the size of the given image match the expected
     * provided dimensions; {@code false} otherwise.
     */
 /*   public static boolean isSize( Image image, int width, int height ) {

        if ( image == null ) return false;

        Layer layer;
        try {
            layer = image.getLayer( true, true, true );
            if ( layer == null ) return false;
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        } catch ( RepositoryException e ) {
            throw new RuntimeException( e );
        }

        int layerWidth = layer.getWidth();
        int layerHeight = layer.getHeight();

        return ( layerWidth == width ) && ( layerHeight == height );
    }*/

    /**
     * Returns the given link ends with a ".html" extension.  If the given link
     * already has a ".html" extension, it is returned unchanged.
     *
     * @param link The path to a page resource.
     * @return Ensures the given link has a ".html" extension.
     */
    public static String withHypertextExtension(String link) {
        if (StringUtils.isEmpty(link)) {
        	return "#";
        }
        if (link.endsWith(".html")) {
        	return link;
        }
        if (link.startsWith("/content")) {
        	return link + ".html";
        }
        return link;
    }

    /**
     * Get the language for a given page. Defaults to 'en'.
     * @param currentPage Page to retrieve the language from.
     * @return String of the language, defaulting to 'en'.
     */
    public static String getPageLanguage(Page currentPage) {
        return getPageLanguage(currentPage, SITE_DEFAULT_LANGUAGE);
    }

    /**
     * Get the language for a given page. Default to the passed in value.
     * @param currentPage Page to retrieve the language from.
     * @param defaultLanguage Default value if the language is not set on the
     * page.
     * @return String of the language, defaulting to defaultLanguage.
     */
    public static String getPageLanguage(
        Page currentPage, String defaultLanguage) {

        if (currentPage == null) {
        	return defaultLanguage;
        }

        Locale locale = currentPage.getLanguage(false);
        if (locale == null) {
        	return defaultLanguage;
        }

        String language = locale.getLanguage();
        if (!StringUtils.isEmpty(language)) {
        	return language;
        }

        return defaultLanguage;
    }

    
    /**
     * Gets the page property.
     *
     * @param page the page
     * @param propertyName the property name
     * @return PageProperty
     */
    public static String getPageProperty(Page page, String propertyName) {
        String propertyValue = null;
        Node pageNode = page.adaptTo(Node.class);
        try {
            if (pageNode.hasNode(COMPONENT_PATH_START)) {
                Node jcrContentNode = pageNode.getNode(COMPONENT_PATH_START);
                if (jcrContentNode.hasProperty(propertyName)) {
                    propertyValue = jcrContentNode.getProperty(propertyName).getString().trim();
                }
            }
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        log.info(page.getTitle() + ":" + propertyName + "=" + propertyValue);
       /* ValueMap properties = page.adaptTo(ValueMap.class);
        return properties.get(key, String.class);*/
        return propertyValue;
    }

	/**
	 * Gets the next random number.
	 *
	 * @return NextRandomNumber
	 */
	public static int getNextRandomNumber() {
		return new Random().nextInt();
	}
    
	/**
	 * Draw.
	 *
	 * @param image the image
	 * @param w the w
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void draw(Image image, Writer w) throws IOException {
		image.draw(w);
	}

	/**
	 * Developed for Jira# 151. Based on pageModifiedDate property of the page, will
	 * display the message based on currentDate like "Updated 2 hours ago".
	 * 
	 * @param slingRequest - SlingHttpServletRequest
	 * @param path - Path of the current page.
	 * @return lastUpdated message.
	 */
	public static String lastUpdatedInfo(SlingHttpServletRequest slingRequest,
			String path) {

		log.info("In lastUpdatedInfo method ");
		String message = Constants.EMPTY_STRING;
		if (slingRequest != null
				&& !Constants.EMPTY_STRING.equalsIgnoreCase(path)) {
			try {

				log.info("Current time Zone: " + Calendar.getInstance().getTimeZone().getDisplayName());
				//Appending jcr:content to path
				path = path + Constants.FORWARD_SLASH
						+ Constants.JCR_CONTENT_NODE;

				ResourceResolver resourceResolver = slingRequest
						.getResourceResolver();
				Resource resource = resourceResolver.getResource(path);
				Node node = resource.adaptTo(Node.class);

				//dateformatter is dd/MM/yyyy HH:mm:ss
				SimpleDateFormat dateformatter = new SimpleDateFormat(
						Constants.DATE_FORMAT);
				dateformatter.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE_UTC));
				
				long ts = System.currentTimeMillis();
				Date localTime = new Date(ts);
				log.info("Local time: " + localTime);
				
				// Convert Local Time to UTC (Works Fine) 
				
				String gmtTime = dateformatter.format(localTime);
				
				log.info( "local System date in UTC: " + gmtTime.toString());
				

				//Gets the pageModified of page
				if(node.hasProperty(Constants.MODIFIED_DATE_IN_UTC)) {

					String lastModifiedDateStr = Constants.EMPTY_STRING;
					
					lastModifiedDateStr = node.getProperty(Constants.MODIFIED_DATE_IN_UTC).getString();
					
					DateFormat originalFormat = new SimpleDateFormat(Constants.UTC_DATE_FORMAT);
					originalFormat.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE_UTC));
					DateFormat targetFormat = new SimpleDateFormat(Constants.ARTICLE_TITLE_DATE_FORMAT);
					targetFormat.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE_UTC));
					
					Date updatedDate = originalFormat.parse(lastModifiedDateStr);
					
					String dateModified = targetFormat.format(updatedDate);
					
					log.info("modifiedDateInUtc property value is: " + dateModified);
					
					String date = dateModified.substring(0, 10);
					String time = dateModified.substring(11, 19);
					String year = date.substring(6, 10);
					String day = date.substring(3, 5);
					String month = date.substring(0, 2);

					// To get modified date in required format logic
					lastModifiedDateStr = day + Constants.FORWARD_SLASH + month + Constants.FORWARD_SLASH + year + Constants.SPACE + time;
					
					Date modifiedDate = dateformatter.parse(lastModifiedDateStr);
					
					//lastModifiedDateStr = dateformatter.format(modifiedDate);

					// To get current date in required format logic
										
					Date currentDate = dateformatter.parse(gmtTime);

					// Calculates the time difference between current and
					// modified date and time
					
					long diff = currentDate.getTime() - modifiedDate.getTime();

					long diffSeconds = diff / 1000 % 60;
					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000) % 24;
					long diffDays = diff / (24 * 60 * 60 * 1000);
					
					log.info("diff value is: " + diff);
					log.info("sec, min, hour, day: " + diffSeconds+ " , "  + diffMinutes + " , "  + diffHours + " , "  + diffDays);
					
					
					// Logic to set the message dispalyed on the page based on
					// diff
					if (diffDays == 0 && diffHours == 0 && diffMinutes == 0
							&& diffSeconds > 0) {
						message = Constants.UPDATED + Constants.SPACE
								+ diffSeconds + Constants.SPACE
								+ Constants.SECONDS + Constants.SPACE
								+ Constants.AGO;
					}
					if (diffDays == 0 && diffHours == 0 && diffMinutes > 0) {
						if (diffMinutes == 1) {
							message = Constants.UPDATED + Constants.SPACE
									+ diffMinutes + Constants.SPACE
									+ Constants.MINUTE + Constants.SPACE
									+ Constants.AGO;
						} else {
							message = Constants.UPDATED + Constants.SPACE
									+ diffMinutes + Constants.SPACE
									+ Constants.MINUTES + Constants.SPACE
									+ Constants.AGO;
						}
					}
					if (diffDays == 0 && diffHours > 0) {
						if (diffHours == 1) {
							message = Constants.UPDATED + Constants.SPACE
									+ diffHours + Constants.SPACE
									+ Constants.HOUR + Constants.SPACE
									+ Constants.AGO;
						} else {
							message = Constants.UPDATED + Constants.SPACE
									+ diffHours + Constants.SPACE
									+ Constants.HOURS + Constants.SPACE
									+ Constants.AGO;
						}
					}

					if (diffDays >= 1) {
						message = Constants.UPDATED + Constants.SPACE
								+ Constants.ON + Constants.SPACE + date;
					}
				}
				log.info("message is: " + message);
				return message;
			} catch (PathNotFoundException e) {
				log.error(e.getMessage(), e);
			} catch (RepositoryException e) {
				log.error(e.getMessage(), e);
			} catch (ParseException e) {
				log.error(e.getMessage(), e);
			}
		}

		return message;
	
	
	}
	
	/**
	 * Developed for Jira# 103. Based on cq:lastModified of a page, it will
	 * displays when that page is last updated. eg: "Updated 2 hours ago"
	 *
	 * @param slingRequest the sling request
	 * @param currentNode the current node
	 * @return lastUpdated message.
	 */
	public static String lastModifiedInfo(SlingHttpServletRequest slingRequest,
			Node currentNode, Page currentPage) {
		log.info("inside lastModifiedInfo");
		String message = Constants.EMPTY_STRING;
		if (slingRequest != null
				&& currentNode != null) {
			try {
				//dateformatter is dd/MM/yyyy HH:mm:ss
				SimpleDateFormat dateformatter = new SimpleDateFormat(
						Constants.DATE_FORMAT);

				String lastModifiedDateStr = Constants.EMPTY_STRING;
				String currentDateStr = Constants.EMPTY_STRING;

				Date modifiedDate = new Date();
				Date currentDate = new Date();
				//Gets the pageModified of page
				if(currentNode.hasProperty(Constants.JCR_LAST_MODIFIED_DATE)) {
					lastModifiedDateStr = currentNode.getProperty(
							Constants.JCR_LAST_MODIFIED_DATE).getString();
					String date = lastModifiedDateStr.substring(0, 10);
					String time = lastModifiedDateStr.substring(11, 19);
					String year = date.substring(0, 4);
					String month = date.substring(8, 10);
					String day = date.substring(5, 7);
					// To get modified date in required format logic
					lastModifiedDateStr = month + Constants.FORWARD_SLASH + day
							+ Constants.FORWARD_SLASH + year + Constants.SPACE
							+ time;
					modifiedDate = dateformatter.parse(lastModifiedDateStr);
					dateformatter.setTimeZone(TimeZone.getTimeZone(getTimeZoneValue(slingRequest, currentPage)));
					// lastModifiedDateStr = dateformatter.format(modifiedDate);

					// To get current date in required format logic
					currentDateStr = dateformatter.format(currentDate);
					currentDate = dateformatter.parse(currentDateStr);
					
					// Calculates the time difference between current and
					// modified date and time
					long diff = currentDate.getTime() - modifiedDate.getTime();
					long diffSeconds = diff / 1000 % 60;
					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000) % 24;
					long diffDays = diff / (24 * 60 * 60 * 1000);
					
					log.info("diff value is: " + diff);
					log.info("sec, min, hour, day: " + diffSeconds+ " , "  + diffMinutes + " , "  + diffHours + " , "  + diffDays);
					
					I18n international = getSiteRelatedI18n(currentPage, slingRequest);

					// String updated = getI18nString(international, Constants.UPDATED);
					String seconds_ago = getI18nString(international, Constants.SECONDS_AGO);
					String min_ago = getI18nString(international, Constants.MIN_AGO);
					String mins_ago = getI18nString(international, Constants.MINS_AGO);
					String hour_ago = getI18nString(international, Constants.HOUR_AGO);
					String hours_ago = getI18nString(international, Constants.HOURS_AGO);
					String publishedOn = getI18nString(international,Constants.PUBLISHED_ON);

                    log.info("seconds_ago=" + Constants.SECONDS_AGO + " - " + seconds_ago);
                    log.info("min_ago=" + Constants.MIN_AGO + " - " + min_ago);
                    log.info("mins_ago=" + Constants.MINS_AGO + " - " + mins_ago);
                    log.info("hour_ago=" + Constants.HOUR_AGO + " - " + hour_ago);
                    log.info("hours_ago=" + Constants.HOURS_AGO + " - " + hours_ago);
                    log.info("publishedOn=" + Constants.PUBLISHED_ON + " - " + publishedOn);

					// Logic to set the message dispalyed on the page based on
					// diff
					if (diffDays == 0 && diffHours == 0 && diffMinutes == 0
							&& diffSeconds > 0) {
						message = seconds_ago.replaceAll("\\{0\\}", String.valueOf(diffSeconds));
					}
					if (diffDays == 0 && diffHours == 0 && diffMinutes > 0) {
						if (diffMinutes == 1) {
							message = min_ago.replaceAll("\\{0\\}", String.valueOf(diffMinutes));
						} else {
							message = mins_ago.replaceAll("\\{0\\}", String.valueOf(diffMinutes));
						}
					}
					if (diffDays == 0 && diffHours > 0) {
						if (diffHours == 1) {
							message = hour_ago.replaceAll("\\{0\\}", String.valueOf(diffHours));
						} else {
							message = hours_ago.replaceAll("\\{0\\}", String.valueOf(diffHours));
						}
					}

					if (diffDays >= 1) {
						message = publishedOn + Constants.SPACE + date;
					}
				}
				return message;
			} catch (PathNotFoundException e) {
				log.error(e.getMessage(), e);
			} catch (RepositoryException e) {
				log.error(e.getMessage(), e);
			} catch (ParseException e) {
				log.error(e.getMessage(), e);
			}
		}
		return message;
	}
	/**
     * Gets the article lead.
     * 
     * @param articleResource
     *            the article resource
     * @return the article lead
     * @throws RepositoryException
     *             the repository exception
     */
    public static String getArticleLead(Node articleNode) {
        String articleLead = null;
        if (articleNode != null) {
            try {
                    if (articleNode.hasProperty(TEMPLATE_TYPE)) {
                        String templateType = articleNode.getProperty(TEMPLATE_TYPE).getString();
                        if(StringUtils.equalsIgnoreCase(templateType, LARGE_MEDIA)) {
                            if (articleNode.hasNode(
                                    Constants.CONTENT_WELL)) {
                                Node articleContentWellNode = articleNode.getNode(
                                        Constants.CONTENT_WELL);
                                 if (articleContentWellNode.hasNode("articletext_with_lead")) {
                                     Node articleTextWithLeadNode = articleContentWellNode.getNode(
                                             "articletext_with_lead");
                                     if (articleTextWithLeadNode
                                             .hasProperty("lead")) {
                                         log.info("lead in  utilityfunctions large media" + articleTextWithLeadNode
                                                 .getProperty("lead")
                                                 .getString());
                                         articleLead = articleTextWithLeadNode
                                                 .getProperty("lead")
                                                 .getString();
                                     }
                                 }
                            }
                        } else if (StringUtils.equalsIgnoreCase(templateType, "smallmedia")) {
                            if (articleNode.hasNode(ARTICLE_SMALL_MEDIA)) {
                                Node articleSmallMediaNode = articleNode.getNode(
                                                ARTICLE_SMALL_MEDIA);
                                if (articleSmallMediaNode
                                        .hasNode("small-photo")) {
                                    Node smallPhotoNode = articleSmallMediaNode
                                            .getNode("small-photo");

                                    if (smallPhotoNode
                                            .hasProperty("lead")) {
                                        log.info("lead in  utilityfunctions small media" + smallPhotoNode
                                                .getProperty("lead")
                                                .getString());
                                        articleLead = smallPhotoNode
                                                .getProperty("lead")
                                                .getString();

                                    }
                                }
                            }
                        }
                    }
                
            } catch (PathNotFoundException e) {

                log.error(e.getMessage(), e);
            } catch (ValueFormatException e) {

                log.error(e.getMessage(), e);
            } catch (RepositoryException e) {

                log.error(e.getMessage(), e);
            }
        }
        return articleLead;
    }
	/**
	 * Gets the article thumbnail.
	 *
	 * @param articleResource the article resource
	 * @return the article thumbnail
	 * @throws RepositoryException the repository exception
	 */
	
	
	
	/**
	 * Gets the component property.
	 *
	 * @param pageResource the page resource
	 * @param componentName the component name
	 * @param componentProperty the component property
	 * @return the component property
	 * @throws RepositoryException the repository exception
	 */
	public static String getComponentProperty(Resource pageResource, String componentName, String componentProperty) throws RepositoryException {
	    String componentPropertyValue = null;
	    if(pageResource != null) {
	        Node pageNode = pageResource.adaptTo(Node.class);
	        if(pageNode.hasNode(COMPONENT_PATH_START)) {
	            if(pageNode.getNode(COMPONENT_PATH_START).hasNode(componentName)) {
	                Node componentNode = pageNode.getNode(COMPONENT_PATH_START).getNode(componentName);
	                if(componentNode.hasProperty(componentProperty)) {
	                    componentPropertyValue = componentNode.getProperty(componentProperty).getString();
	                }
	            }
	        }
	    }
        return componentPropertyValue;
	}
	
	/**
	 * Gives the number of comments for a post.
	 *
	 * @param currentPage the current page
	 * @param slingRequest the sling request
	 * @return the article comment info
	 */
    public static Long getArticleCommentInfo(String path,
            SlingHttpServletRequest slingRequest) {
        Long atricleCommentCount = null;
        log.info("Inside getArticleCommentInfo");
        log.info("Inside getArticleCommentInfo path:"+path);
        ResourceResolver resourceResolver = slingRequest
                .getResourceResolver();
       // Node pageNode = currentPage.adaptTo(Node.class);
        Node pageNode = null;
        if(StringUtils.isNotBlank(path)) {
            if(StringUtils.endsWith(path,".html")) {
               path = path.replace(".html", "");
            }
            Resource res = resourceResolver.getResource(path);
            if(res != null) {
                pageNode = res.adaptTo(Node.class);
            }
        }
        try {
            if (pageNode != null
                    && pageNode.hasNode(Constants.JCR_CONTENT_NODE)) {
                Node contentPage = pageNode.getNode(Constants.JCR_CONTENT_NODE);
                log.info("current page is " + contentPage.getPath());
                if (contentPage.hasNodes()) {
                    
                    Resource allCommentsResource = null;
                    Resource filteredCommentsResource = null;
                    String commentPath = Constants.EMPTY_STRING;
                    String allCommentsPath = Constants.EMPTY_STRING;
                    String filteredCommentsPath = Constants.EMPTY_STRING;
                    NodeIterator iterator = contentPage.getNodes();
                    if (iterator != null) {
                        while (iterator.hasNext()) {
                            Node parsysNode = iterator.nextNode();
                            String parsysNodeName = parsysNode.getName();
                            if (parsysNodeName.contains(Constants.CONTENT_LEFT)
                                    || parsysNodeName
                                            .contains(Constants.CONTENT_BOTTOM)) {
                                if (parsysNode.hasNodes()) {
                                    NodeIterator parsysNodeIterator = parsysNode
                                            .getNodes();
                                    while (parsysNodeIterator.hasNext()) {
                                        Node node = parsysNodeIterator
                                                .nextNode();

                                        if (node.getName().contains(
                                                Constants.COMMENTS)
                                                && node.getProperty(
                                                        Constants.SLING_RESOURCE_TYPE)
                                                        .getString()
                                                        .equals(Constants.COMMENTS_SLING_RESOURCE_TYPE)) {

                                            commentPath = pageNode.getPath();
                                            allCommentsPath = Constants.CONTENT_USER_GENERATED
                                                    + commentPath
                                                    + Constants.COMMENTS_ALL_COUNTER_PATH;                                            
                                            filteredCommentsPath = Constants.CONTENT_USER_GENERATED
                                                    + commentPath
                                                    + Constants.COMMENTS_FILTERED_COUNTER_PATH;                                            
                                            allCommentsResource = resourceResolver
                                                    .getResource(allCommentsPath);
                                            filteredCommentsResource = resourceResolver
                                                    .getResource(filteredCommentsPath);
                                            log.info("allCommentsResource and filteredCommentsResource"+allCommentsResource+"..."+filteredCommentsResource);
                                            if (allCommentsResource != null) {
                                                Node allCommentsNode = allCommentsResource
                                                        .adaptTo(Node.class);                                                
                                                Long allCommentsCount = null;
                                                if (allCommentsNode
                                                        .hasProperty(Constants.TOTAL_COUNT)) {
                                                    allCommentsCount = allCommentsNode
                                                            .getProperty(
                                                                    Constants.TOTAL_COUNT)
                                                            .getLong();                                                    
                                                    Long filteredCommentsCount = null;
                                                    if (filteredCommentsResource != null) {

                                                        Node filteredCommentsNode = filteredCommentsResource
                                                                .adaptTo(Node.class);

                                                        if (filteredCommentsNode
                                                                .hasProperty(Constants.TOTAL_COUNT)) {
                                                            filteredCommentsCount = filteredCommentsNode
                                                                    .getProperty(
                                                                            Constants.TOTAL_COUNT)
                                                                    .getLong();
                                                        }
                                                    }

                                                    if (allCommentsCount != null
                                                            && filteredCommentsCount != null) {
                                                        atricleCommentCount = allCommentsCount
                                                                - filteredCommentsCount;
                                                    } else if (allCommentsCount != null) {
                                                        atricleCommentCount = allCommentsCount;
                                                    }

                                                }

                                            }

                                        }
                                    }

                                }

                            }
                        }
                    }
                }
            }
        } catch (RepositoryException e) {
            log.error(e.getMessage(), e);
        }
        log.info("atricleCommentCount for " + path + " is "
                + atricleCommentCount);
        return atricleCommentCount;
    }
    
    /**
     * Gets the home page property value.
     *
     * @param currentPage the current page
     * @param propertyName the property name
     * @return the home page property value
     */
    public static String getHomePagePropertyValue(Page currentPage, String propertyName) {
        String propertyValue = null;
        if (currentPage != null) {
            Page homePage = currentPage.getAbsoluteParent(3);
            if (homePage != null) {
                Node homePageNode = homePage.adaptTo(Node.class);
                try {
                    if (homePageNode != null && homePageNode.hasNode(COMPONENT_PATH_START)) {
                        Node jcrContentNode = homePageNode.getNode(COMPONENT_PATH_START);
                        propertyValue = returnEmptyIfNull(getNodePropertyValue(jcrContentNode, propertyName));
                    }
                } catch (RepositoryException e) {
                    log.error(e.getMessage());
                }
            }
        }
        log.info("Home Page Property: " + propertyName + "=" + propertyValue);
        return propertyValue;
    }
    
    /**
     * Return empty if null.
     *
     * @param propertyValue the property value
     * @return the string
     */
    public static String returnEmptyIfNull(String propertyValue) {
        if (propertyValue == null) {
            return Constants.EMPTY_STRING;
        }
        return propertyValue.trim();
    }
    
    /**
     * Gets the node property value.
     *
     * @param node the node
     * @param propertyName the property name
     * @return the node property value
     */
    public static String getNodePropertyValue(Node node, String propertyName) {
        String propertyNameValue = null;
        try {
            if (node.hasProperty(propertyName) && StringUtils.isNotBlank(node.getProperty(propertyName).getString())) {
                    propertyNameValue = node.getProperty(propertyName).getString();
                }
        } catch (RepositoryException re) {
            log.error("RepositoryException ", re);
        }
        log.info("Node Property: " + propertyName + "=" + propertyNameValue);
        return propertyNameValue;
    }
    
    /**
     * Gets the home page.
     *
     * @param currentPage the current page
     * @return the home page
     */
    public static Page getHomePage(Page currentPage) {
        Page homePage = null;
        if (currentPage != null) {
            homePage = currentPage.getAbsoluteParent(3);           
        }
        return homePage;
    }

    /**
     * replace the pattern with replacement in sourceString
     *
     * @param sourceString
     * @param pattern
     * @param replacement
     * @return the modified string
     *
     */
    public static String replaceAll(String sourceString, String pattern, String replacement) {
        return sourceString.replaceAll(pattern, replacement);
    }

    /**
     * compute the slideshow path to allow it to be loaded in any website
     *
     * @param slideshowAssetPath (ex: /content/tc/french_sites/le-bel-oeil/photo.2015.7.8.f6_cat_slideshow.html)
     * @param homePagePath (ex: /content/tc/french_sites/le-courrier-du-sud)
     * @return String the slideshow Path (ex: /content/tc/french_sites/le-courrier-du-sud/photo.le-courrier-du-sud.2015.7.8.f6_cat_slideshow.html)
     *
     */
    public static String getSlideshowPath(String slideshowAssetPath, String homePagePath) {
        if (!slideshowAssetPath.contains(homePagePath)) {
            slideshowAssetPath = slideshowAssetPath.replaceAll("^/content/tc/[^/]+/([^/]+)(/[^\\.]+)(\\.[0-9]{4}\\.[0-9]{1,2}.[0-9]{1,2}\\..*)$", homePagePath+"$2.$1$3");
        }
        return slideshowAssetPath;
    }

    /**
     * Gets the home page path.
     *
     * @param currentPage the current page
     * @return the home page path
     */
    public static String getHomePagePath(Page currentPage) {
        String homePagePath = null;
        if (currentPage != null) {
            Page homePage = currentPage.getAbsoluteParent(3);
            if (homePage != null) {
                homePagePath = homePage.getPath();
                log.info("homePagePath:" + homePagePath);
            }
        }
        return homePagePath;
    }

    public static Locale getPageSiteLocale(Page currentPage) {
        String propertyNameValue = getHomePagePropertyValue(currentPage, LANGUAGE);
        Locale pageLocale = null;
        if (StringUtils.isNotBlank(propertyNameValue)) {
            pageLocale = new Locale(propertyNameValue);
        } else {
            pageLocale = currentPage.getLanguage(true);
        }

        return pageLocale;
    }
    
    /**
     * Gets the site related i18n.
     *
     * @param currentPage the current page
     * @param slingRequest the sling request
     * @return the site related i18n
     */
    public static I18n getSiteRelatedI18n(Page currentPage, SlingHttpServletRequest slingRequest) {
        log.info("In getSiteRelatedI18n method");
        log.info("currentPage: " + currentPage.getPath());
        String propertyNameValue = getHomePagePropertyValue(currentPage, LANGUAGE);
        Locale pageLocale = getPageSiteLocale(currentPage);
        ResourceBundle resourceBundle = slingRequest.getResourceBundle(pageLocale);
        I18n i18n = new I18n(resourceBundle);
        return i18n;
    }
    /**
     * Gets the site related i18n.
     *
     * @param currentPage the current page
     * @param slingRequest the sling request
     * @return the site related i18n
     */
    public static I18n getNewsletterRelatedI18n(Page currentPage,
            SlingHttpServletRequest slingRequest) {
        log.info("inside getNewsletterRelatedI18n");
        String site = getPageProperty(currentPage, SITE);
        String propertyNameValue = null;
        log.info("homepage....." + site);
        ResourceResolver resolver = slingRequest.getResourceResolver();
        Page homePage = resolver.getResource(site).adaptTo(Page.class);       
        if(homePage != null) {
         propertyNameValue = getPageProperty(homePage, LANGUAGE);
         log.info("language....." + propertyNameValue);
        }
        
        Locale pageLocale = null;
        if (StringUtils.isNotBlank(propertyNameValue)) {
            pageLocale = new Locale(propertyNameValue);
        } else {
            pageLocale = currentPage.getLanguage(true);
        }
        ResourceBundle resourceBundle = slingRequest
                .getResourceBundle(pageLocale);
        I18n i18n = new I18n(resourceBundle);
        return i18n;
    }
    /**
     * Gets the i18n string.
     *
     * @param i18n the i18n
     * @param key the key
     * @return the i18n string
     */
    public static String getI18nString(I18n i18n, String key) {
        if(i18n != null) {
            return i18n.get(key);
        }
        return key;
    }
    
    /**
     * Gives the timeZone selected in the site config of homepage.
     * 
     * Gives the site timezone
     * @param currentPage
     * @return timezone
     */
    public static String getTimeZoneValue(SlingHttpServletRequest slingRequest, Page currentPage) {
        String timeZone = "EST";
        if (currentPage != null) {
            timeZone = getHomePagePropertyValue(currentPage, "timeZone");
        }
        log.info("timeZone:" + timeZone);
        return timeZone;
    }
    
  
    
    public static String getRenditionImageTag(Page page, String imagePath, Integer height, Integer width) {
    	  log.info("inside getRenditionImage" + imagePath);
          log.info("Image Path");
          String renditionImage = "";
          String htmlImgSrc = "";
          Resource imageResource = getResource(page, imagePath);
          if (imageResource != null) {
              log.info("imageResourcenot null");
              Node imageAssetResourceNode = imageResource.adaptTo(Node.class);
              try {
                  log.info("imageAssetResourceNode: "
                          + imageAssetResourceNode.getPath());

                  Node imageContentWellNode = imageAssetResourceNode.getNode(
                          Constants.JCR_CONTENT).getNode("renditions");

                  if (imageContentWellNode.hasNodes()) {
                      NodeIterator imageAssetResourceNodes = imageContentWellNode
                              .getNodes();
                      Node imageContentNode = null;
                      while (imageAssetResourceNodes.hasNext()) {
                          imageContentNode = imageAssetResourceNodes.nextNode();
                          /*
                           * String renditionNodePath = imageContentNode
                           * .getProperty( Constants.SLING_RESOURCETYPE)
                           * .getString();
                           */
                          log.info("imageContentNode: "
                                  + imageContentNode.getPath());

                          if (!imageContentNode.getPath().toString()
                                  .contains("original")
                                  && !imageContentNode.getPath().toString()
                                          .contains("tiff")) {
                              String[] imageContentNodeSplitArray = imageContentNode
                                      .toString().split("\\.");
                             int imageHeight = Integer
                                      .parseInt(imageContentNodeSplitArray[3]);
                              int imageWidth = Integer
                                      .parseInt(imageContentNodeSplitArray[4]);
                              if (imageHeight == height && imageWidth == width) {
                                  renditionImage = imageContentNode.getPath()
                                          .toString();
                                  log.info("renditionImage" + renditionImage);
                                  htmlImgSrc = "<img src="+'"'+renditionImage+'"'+" alt='' />";
                                  log.info("htmlImgSrc::::::::::"+htmlImgSrc);
                              }
                              else
                              {
                            	  htmlImgSrc = "<img src ="+'"' +imagePath+ '"'+" alt ='' height ="+'"' +height+'"'+ "width ="+'"' +width+'"'+ " />";
                              	log.info("htmlImgSrc in else::::"+htmlImgSrc);
                              }
                          }
                      }

                  }
              } catch (RepositoryException e) {
                  log.info("RepositoryException in rendition"
                          + e.getMessage());
              }
          }

          return htmlImgSrc;   	
    	    
    }
    
    
    public static String smallTemplatePropetryValue(Resource resource, String path, String property){
    	String propertyValue = "";    	
    	log.info("resource, path, property:: " + resource + "::: " + path + ":: " + property);
    	Resource smallPhotoResource = getResource(resource, path);
    	if(smallPhotoResource.getValueMap().containsKey(property)){
    	    propertyValue = smallPhotoResource.getValueMap().get(property,String.class);    	   
    	}    
    	log.info("propertyValue :" + propertyValue);
    	return propertyValue;
    }
    
    /**
     * page level protection by group.
     *
     * @param Resource the resource
     * @param Page the page
     */
    public static void groupBaseProtection(SlingHttpServletRequest slingRequest, Page currentPage, EditContext editContext ) {
		ResourceResolver resourceResolver = slingRequest.getResourceResolver();
        //Group protection
        User user = resourceResolver.adaptTo(User.class); 
        UserManager um = resourceResolver.adaptTo(UserManager.class); 
        String autorizedGroup = "administrators";
        if (currentPage != null) {
        	autorizedGroup = getHomePagePropertyValue(currentPage, "autorizedGroup");
        }
        log.error("autorizedGroup :"+autorizedGroup);  
        Group autorized;
		try {
			if (StringUtils.isNotBlank(autorizedGroup)&& editContext != null) {
				autorized = (Group)um.getAuthorizable(autorizedGroup);
				if(!autorized.isMember(user)){
					WCMMode mode = WCMMode.DISABLED.toRequest(slingRequest);
				}
			}
		} catch (RepositoryException e) { 
			e.printStackTrace();
		} 


    	
    }
    
    
    /**
     *  returns Static URL for each static / dynamic page as per Language
	 * 
	 * getStaticURL(Obituaries,en) returns /obituaries.html
	 * getStaticURL(Obituaries,fr) returns  /deces.html
	 * getStaticURL(Signup,en) returns /signup.html
	 * getStaticURL(Signup,fr) returns /inscription.html
     *
     * @param String pageName
     * @param String siteLanguage
     * 
     * */
	

    /**/
   // }
    
   
}