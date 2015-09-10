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
public class SightlyNewsAction extends WCMUse {
    /** The log. */
    private Logger log = LoggerFactory.getLogger(SightlyNewsAction.class);
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
                if (node.hasProperty("title")) {
                    topStoriesBean.setTitle(node.getProperty("title")
                            .getString());
                }
            } catch (RepositoryException e) {
                log.error("RepositoryException ", e);
            }
            try {
                List<String> newsList = new ArrayList<String>();
                if (node.hasProperty("news")) {
                    Value[] news = {};
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
    }

    /**
     * Gets the top story bean.
     * 
     * @return the top story bean
     */
    public TopStoryBean getTopStoryBean() {
        return this.topStoriesBean;
    }
}
