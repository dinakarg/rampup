package com.ndtv.action;

import javax.jcr.Node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.ndtv.model.TopnavBean;
import com.ndtv.util.CommonUtils;

/**
 * The Class TopnavSightly.
 */
public class TopnavComponent extends WCMUse {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory
            .getLogger(TopnavComponent.class);

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
if (currentNode != null) {
            topnavBean.setTitle1(CommonUtils.returnEmptyIfNull(CommonUtils
                    .getNodePropertyValue(currentNode, TopnavAction.TITLE1)));

            LOG.info(topnavBean.getTitle1());


            topnavBean.setTitle2(CommonUtils.returnEmptyIfNull(CommonUtils
                    .getNodePropertyValue(currentNode, TopnavAction.TITLE2)));
            LOG.info(topnavBean.getTitle2());

            topnavBean.setTitle3(CommonUtils.returnEmptyIfNull(CommonUtils
                    .getNodePropertyValue(currentNode, TopnavAction.TITLE3)));
            LOG.info(topnavBean.getTitle3());

            topnavBean.setUrl1(CommonUtils.returnEmptyIfNull(CommonUtils
                    .getNodePropertyValue(currentNode, TopnavAction.URL1)));
            LOG.info(topnavBean.getUrl1());

            topnavBean.setUrl2(CommonUtils.returnEmptyIfNull(CommonUtils
                    .getNodePropertyValue(currentNode, TopnavAction.URL2)));
            LOG.info(topnavBean.getUrl2());

            topnavBean.setUrl3(CommonUtils.returnEmptyIfNull(CommonUtils
                    .getNodePropertyValue(currentNode, TopnavAction.URL3)));
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
