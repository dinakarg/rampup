package com.ndtv.action;

import javax.jcr.Node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.ndtv.model.TopnavBean;

/**
 * The Class TopnavSightly.
 */
public class TopnavSightly extends WCMUse {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory
            .getLogger(TopnavSightly.class);

    /** The topnav bean. */
    private TopnavBean topnavBean = null;

    /**
     * Activate.
     * @throws Exception
     *             the exception
     */
    @Override
    public final void activate() throws Exception {
        Node currentNode = getResource().adaptTo(Node.class);

        topnavBean = new TopnavBean();
        if (currentNode.hasProperty("title1")) {
            topnavBean.setTitle1(currentNode.getProperty("title1").getString());

            LOG.info(topnavBean.getTitle1());

        }
        if (currentNode.hasProperty("title2")) {
            topnavBean.setTitle2(currentNode.getProperty("title2").getString());
            LOG.info(topnavBean.getTitle2());
        }
        if (currentNode.hasProperty("title3")) {
            topnavBean.setTitle3(currentNode.getProperty("title3").getString());
            LOG.info(topnavBean.getTitle3());
        }
        if (currentNode.hasProperty("url1")) {
            topnavBean.setUrl1(currentNode.getProperty("url1").getString());
            LOG.info(topnavBean.getUrl1());
        }
        if (currentNode.hasProperty("url2")) {
            topnavBean.setUrl2(currentNode.getProperty("url2").getString());
            LOG.info(topnavBean.getUrl2());
        }
        if (currentNode.hasProperty("url3")) {
            topnavBean.setUrl3(currentNode.getProperty("url3").getString());
            LOG.info(topnavBean.getUrl3());
        }
    }

    /**
     * Gets the topnav bean.
     * @return the topnav bean
     */
    public final TopnavBean getTopnavBean() {
        return this.topnavBean;
    }

}
