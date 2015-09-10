/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or
 *  distributed without the express permission of Virtusa Corporation
 */

package com.ndtv.action;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ndtv.model.HeroBean;

/**
 * HeroAction.
 * 
 * @author PRUTHVIV
 * 
 */
public class HeroAction extends BaseAction {

    /** The log. */
    private Logger log = LoggerFactory.getLogger(HeroAction.class);

    /**
     * Hero.
     * 
     * @return the hero bean
     */
    public final HeroBean hero() {
        HeroBean heroBean = new HeroBean();
        Node node = getCurrentNode();
        if (null != node) {
            try {
                if (node.hasProperty("title") && node.hasProperty("path")) {
                    heroBean.setTitle(node.getProperty("title").getString());
                    heroBean.setPath(node.getProperty("path").getString());
                }
            } catch (RepositoryException e) {
                log.error("RepositoryException ", e);
            }
        }
        return heroBean;
    }
}
