package com.ndtv.action;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.ndtv.model.TopStoryBean;

/**
 * @author SRASHMI
 * 
 */
public class TopNewsComponent extends WCMUse {

    /** The Constant NEWS2. */
    private static final String NEWS2 = "news";

    /** The Constant TITLE. */
    private static final String TITLE = "title";
    /** The log. */
    private static final Logger LOG = LoggerFactory
            .getLogger(TopNewsComponent.class);
    /** The top stories bean. */
    private TopStoryBean topStoriesBean = null;

    /**
     * This method calls from component to get the bean properties.
     * 
     * @throws Exception
     *             exception.
     * 
     ** 
     */
    @Override
    public void activate() throws Exception {
        // TODO Auto-generated method stub
        Node node = getResource().adaptTo(Node.class);
        topStoriesBean = new TopStoryBean();
        if (null != node) {
            try {
                if (node.hasProperty(TITLE)) {
                    topStoriesBean
                            .setTitle(node.getProperty(TITLE).getString());
                }
            } catch (RepositoryException e) {
                LOG.error(
                        "Error while retrieving the property value from the node"
                                + e.getMessage(), e);
            }
            try {
                List<String> newsList = new ArrayList<String>();
                if (node.hasProperty(NEWS2)) {
                    Value[] news = {};
                    if (node.getProperty(NEWS2).isMultiple()) {
                        news = node.getProperty(NEWS2).getValues();

                    } else {
                        newsList.add(node.getProperty("news").getString());
                    }
                    for (Value str : news) {
                        newsList.add(str.toString());
                    }
                    topStoriesBean.setNews(newsList);
                }
            } catch (RepositoryException e) {
                LOG.error(
                        "Error while retrieving the property value from the node"
                                + e.getMessage(), e);
            }
        }
    }

    /**
     * @return the topStoriesBean
     */
    public TopStoryBean getTopStoriesBean() {
        return this.topStoriesBean;
    }

    /**
     * Gets the top story bean.
     * 
     * @return the top story bean
     */
    /*
     * public TopStoryBean getTopStoryBean() { return this.topStoriesBean; }
     */

}
