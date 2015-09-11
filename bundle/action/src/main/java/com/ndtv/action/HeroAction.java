/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or
 *  distributed without the express permission of Virtusa Corporation
 */

package com.ndtv.action;

import javax.jcr.Node;
import com.ndtv.model.HeroBean;
import com.ndtv.util.CommonUtils;


/**
 * HeroAction.
 * 
 * @author PRUTHVIV
 * 
 */
public class HeroAction extends BaseAction {
    /**
     * Hero.
     * 
     * @return the hero bean
     */
    public final HeroBean hero() {
        HeroBean heroBean = new HeroBean();
        Node node = getCurrentNode();

        if (null != node) {
            heroBean.setTitle(CommonUtils.returnEmptyIfNull(CommonUtils
                    .getNodePropertyValue(node, CommonUtils.HERO_TITLE)));
            heroBean.setTitle(CommonUtils.returnEmptyIfNull(CommonUtils
                    .getNodePropertyValue(node, CommonUtils.HERO_PATH)));
        }
        // try {
        // if (node.hasProperty(CommonUtils.HERO_TITLE)
        // && node.hasProperty(CommonUtils.HERO_PATH)) {
        // heroBean.setTitle(node.getProperty(CommonUtils.HERO_TITLE)
        // .getString());
        // heroBean.setPath(node.getProperty(CommonUtils.HERO_PATH)
        // .getString());
        // }
        // } catch (RepositoryException e) {
        // log.error("RepositoryException ", e);
        // }
        // }
        return heroBean;
    }
}
