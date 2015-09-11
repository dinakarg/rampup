/**
*TopnavAction.java Created by MANIKANTAC on Sep 10, 2015.
 *
 * Copyright (c) 2015 Virtusa Corporation. All Rights Reserved.<br><br>.
 */
package com.ndtv.action;

import javax.jcr.Node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.ndtv.model.TopnavBean;
import com.ndtv.util.CommonUtils;

/**
 * TODO Class description goes here.
 * @version
 * @author MANIKANTAC
 */
public class TopnavAction extends BaseAction {

/** The Constant URL3. */
public static final String URL3 = "url3";

/** The Constant TITLE3. */
public static final String TITLE3 = "title3";

/** The Constant URL2. */
public static final String URL2 = "url2";

/** The Constant TITLE2. */
public static final String TITLE2 = "title2";

/** The Constant URL1. */
public static final String URL1 = "url1";

/** The Constant TITLE1. */
public static final String TITLE1 = "title1";
/** The log. */
private static Logger log = LoggerFactory.getLogger(TopnavAction.class);
/**
* Topnav.
* @return the topnav bean
*/
public final TopnavBean topnav() {
TopnavBean topnavBean = new TopnavBean();
Node node = getCurrentNode();
if (null != node) {
topnavBean.setTitle1(CommonUtils.returnEmptyIfNull(CommonUtils
.getNodePropertyValue(node, TITLE1)));
log.info(topnavBean.getTitle1());
topnavBean.setUrl1(CommonUtils.returnEmptyIfNull(CommonUtils
.getNodePropertyValue(node, URL1)));
log.info(topnavBean.getUrl1());
topnavBean.setTitle2(CommonUtils.returnEmptyIfNull(CommonUtils
.getNodePropertyValue(node, TITLE2)));
log.info(topnavBean.getTitle2());
topnavBean.setUrl2(CommonUtils.returnEmptyIfNull(CommonUtils
.getNodePropertyValue(node, URL2)));
log.info(topnavBean.getUrl2());
topnavBean.setTitle3(CommonUtils.returnEmptyIfNull(CommonUtils
.getNodePropertyValue(node, TITLE3)));
log.info(topnavBean.getTitle3());
topnavBean.setUrl3(CommonUtils.returnEmptyIfNull(CommonUtils
.getNodePropertyValue(node, URL3)));
log.info(topnavBean.getUrl3());
}
return topnavBean;
}
}
