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
 * @author SRASHMI
 * 
 */
public class TopStoryAction extends BaseAction {
    /** The log. */
    private Logger log = LoggerFactory.getLogger(TopStoryAction.class);

    /**
     * Top stories.
     * 
     * @return the top story bean
     */
    public  TopStoryBean topStories() {
        TopStoryBean topStoriesBean = new TopStoryBean();
        Node node = getCurrentNode();
        if (null != node) {
            try {
                if (node.hasProperty("title")) {
                    topStoriesBean.setTitle(node.getProperty("title")
                            .getString());
                }
            } catch (RepositoryException e) {
                log.error("RepositoryException ", e);
            }
            try {
                Value[] news = {};
                List<String> newsList = new ArrayList<String>();
                if (node.hasProperty("news")) {
                    if (node.getProperty("news").isMultiple()) {
                        news = node.getProperty("news").getValues();
                        System.out.println(news[0]);
                    }
                    for (Value str : news) {
                        newsList.add(str.toString());
                    }
                    topStoriesBean.setNews(newsList);
                }
            } catch (RepositoryException e) {
                log.error("RepositoryException ", e);
            }
        }
        return topStoriesBean;
    }
}
