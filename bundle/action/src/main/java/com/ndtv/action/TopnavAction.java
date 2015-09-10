/** 
 * TopnavAction.java Created by MANIKANTAC on Sep 10, 2015.
 *
 * Copyright (c) 2015 Virtusa Corporation. All Rights Reserved.<br><br>.
 * 
 */
package com.ndtv.action;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ndtv.action.BaseAction;
import com.ndtv.model.TopnavBean;

/**
 * TODO Class description goes here.
 * @version 
 * @author MANIKANTAC
 */
public class TopnavAction extends BaseAction {
    /** The log. */
    private Logger log = LoggerFactory.getLogger(TopnavAction.class);

    /**
     * Topnav.
     * @return the topnav bean
     */
    public final TopnavBean topnav() {
        TopnavBean topnavBean = new TopnavBean();
        Node node = getCurrentNode();
        if (null != node) {
            try {
                if (node.hasProperty("title1")) {
                    topnavBean
                            .setTitle1(node.getProperty("title1").getString());
                }
                if (node.hasProperty("url1")) {
                    topnavBean.setUrl1(node.getProperty("url1").getString());
                }
                if (node.hasProperty("title2")) {
                    topnavBean
                            .setTitle2(node.getProperty("title2").getString());
                }
                if (node.hasProperty("url2")) {
                    topnavBean.setUrl2(node.getProperty("url2").getString());
                }
                if (node.hasProperty("title3")) {
                    topnavBean
                            .setTitle3(node.getProperty("title3").getString());
                }
                if (node.hasProperty("url3")) {
                    topnavBean.setUrl3(node.getProperty("url3").getString());
                }
            } catch (RepositoryException e) {
                log.error("RepositoryException ", e);
            }
        }
        return topnavBean;
    }
}
