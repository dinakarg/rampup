/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 */
package com.ndtv.action;

import java.util.ArrayList;
import java.util.List;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ndtv.model.TopStoryBean;

/**
 * The Class TopStoryAction.
 * 
 * @author SRASHMI
 */
public class TopStoryAction extends BaseAction {

    /** The Constant TITLE1. */
    private static final String TITLE1 = "title";

    /** The Constant NEWS2. */
    private static final String NEWS2 = "news";
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
            try {
                if (node.hasProperty(TITLE1)) {
                    topStoriesBean.setTitle(node.getProperty(TITLE1)
                            .getString());
                }
            } catch (RepositoryException e) {
                LOG.error(
                        "Error while retrieving the property value from the node"
                                + e.getMessage(), e);
            }
            try {
                Value[] news = {};
                List<String> newsList = new ArrayList<String>();
                if (node.hasProperty(NEWS2)) {
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
        return topStoriesBean;
    }
}
