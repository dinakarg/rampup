package com.ndtv.action;

import com.adobe.cq.sightly.WCMUse;
import com.ndtv.model.HeroBean;
import com.ndtv.util.CommonUtils;

import javax.jcr.Node;

/**
 * The Class HeroTextComponent.
 */
public class HeroSightly extends WCMUse {

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
        Node node = getResource().adaptTo(Node.class);
        heroBean = new HeroBean();
        heroBean.setTitle(CommonUtils.returnEmptyIfNull(CommonUtils
                .getNodePropertyValue(node,HeroAction.HERO_TITLE)));
        heroBean.setTitle(CommonUtils.returnEmptyIfNull(CommonUtils
                .getNodePropertyValue(node, HeroAction.HERO_PATH)));
        // try {
        // if (currentNode.hasProperty(CommonUtils.HERO_TITLE)) {
        // heroBean.setTitle(currentNode.getProperty(
        // CommonUtils.HERO_TITLE).getString());
        // log.info(heroBean.getTitle());
        // }
        // if (currentNode.hasProperty(CommonUtils.HERO_PATH)) {
        // heroBean.setPath(currentNode.getProperty(CommonUtils.HERO_PATH)
        // .getString());
        // log.info(heroBean.getPath());
        // }
        // } catch (RepositoryException e) {
        // log.error("RepositoryException " + e.getMessage(), e);
        // }
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
