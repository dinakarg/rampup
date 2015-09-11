package com.ndtv.action;

import com.adobe.cq.sightly.WCMUse;
import com.ndtv.model.HeroBean;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class HeroTextComponent.
 */
public class HeroSightly extends WCMUse {
    /** The hero text bean. */
    private Logger log = LoggerFactory.getLogger(HeroAction.class);

    /** The hero bean. */
    private HeroBean heroBean = null;

    /**
     * getting title and path.
     * 
     * @throws Exception
     *             the exception
     */
    @Override
    public final void activate() throws Exception {
        Node currentNode = getResource().adaptTo(Node.class);
        heroBean = new HeroBean();
        try {
            if (currentNode.hasProperty("title")) {
                heroBean.setTitle(currentNode.getProperty("title").getString());
                log.info(heroBean.getTitle());
            }
            if (currentNode.hasProperty("path")) {
                heroBean.setPath(currentNode.getProperty("path").getString());
                log.info(heroBean.getPath());
            }
        } catch (RepositoryException e) {
            log.error("RepositoryException " + e.getMessage(), e);
        }
    }

    /**
     * Gets the hero bean.
     * 
     * @return the hero bean
     */
    public final HeroBean getHeroBean() {
        return this.heroBean;
    }

}
