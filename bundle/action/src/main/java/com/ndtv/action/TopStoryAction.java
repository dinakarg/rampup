/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 */
package com.ndtv.action;

import java.util.ArrayList;
import java.util.List;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ndtv.model.TopStoryBean;
import com.ndtv.util.CommonUtils;

/**
 * The Class TopStoryAction.
 * 
 * @author SRASHMI
 */
public class TopStoryAction extends BaseAction {

    /** The Constant TITLE1. */
    public static final String TITLE = "title";

    /** The Constant NEWS2. */
    public static final String NEWS = "news";
    /** The log. */
    private static final Logger LOG = LoggerFactory
            .getLogger(TopStoryAction.class);

    /**
     * Top stories.
     * 
     * @return the top story bean
     */
    public TopStoryBean topStories() {
        TopStoryBean topStoriesBean = new TopStoryBean();
        Node node = getCurrentNode();
        if (null != node) {
            topStoriesBean.setTitle(CommonUtils.returnEmptyIfNull(CommonUtils
                    .getNodePropertyValue(node, TITLE))); // null

            try {
                if (node.hasProperty(NEWS)) {

                    if (node.getProperty(NEWS).isMultiple()) {

                        topStoriesBean.setNews(CommonUtils
                                .getNodePropertyValues(node, NEWS));

                    } else {
                        List<String> topStories = new ArrayList<String>();
                        topStories.add(CommonUtils.getNodePropertyValue(node,
                                NEWS));
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
        return topStoriesBean;
    }
}
