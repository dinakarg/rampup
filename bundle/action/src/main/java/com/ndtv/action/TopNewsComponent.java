package com.ndtv.action;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.ndtv.model.TopStoryBean;
import com.ndtv.util.CommonUtils;

/**
 * @author SRASHMI
 * 
 */
public class TopNewsComponent extends WCMUse {

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

        Node node = getResource().adaptTo(Node.class);
        topStoriesBean = new TopStoryBean();
        if (null != node) {

            topStoriesBean.setTitle(CommonUtils.getNodePropertyValue(node,
                    TopStoryAction.TITLE));

            try {
                if (node.hasProperty(TopStoryAction.NEWS)) {

                    if (node.getProperty(TopStoryAction.NEWS).isMultiple()) {

                        topStoriesBean.setNews(CommonUtils
                                .getNodePropertyValues(node,
                                        TopStoryAction.NEWS));

                    } else {
                        List<String> topStories = new ArrayList<String>();
                        topStories.add(CommonUtils.getNodePropertyValue(node,
                                TopStoryAction.NEWS));
                        topStoriesBean.setNews(topStories);
                    }
                }
            } catch (PathNotFoundException e) {
                LOG.error(
                        "Error while retrieving the property value from the node"
                                + e.getMessage(), e);
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

}
