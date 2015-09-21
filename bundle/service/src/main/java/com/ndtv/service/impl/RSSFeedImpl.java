package com.ndtv.service.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ndtv.service.api.RSSFeed;
import com.ndtv.service.model.RSSFeedBean;

//This is a component so it can provide or consume services
@Component
@Service
public class RSSFeedImpl implements RSSFeed {
	private static final Logger LOG = LoggerFactory
			.getLogger(RSSFeedImpl.class);
	// Component Constants
	public static final String RSS_ITEM_ATTRIBUE = "item";
	public static final String RSS_TITLE_ATTRIBUE = "title";
	public static final String RSS_LINK_ATTRIBUE = "link";
	public static final String RSS_GUILD_ATTRIBUE = "guild";
	public static final String RSS_AUTHOR_ATTRIBUE = "author";
	public static final String RSS_DESCRIPTION_ATTRIBUE = "description";
	// Component Value/Property constants
	public static final String RSS_FEED_ULR = "rssFeedUrl";
	public static final String RSS_MAX_FEED_COUNT = "maxFeedCount";

	// Components (Configurable) Attributes
	// private String rssFeedUrl;
	// private String maxFeedCount;
	// Define a class member named key
	// private int key = 0;

	@Override
	// A basic setter method that sets key
	public List<RSSFeedBean> getRssFeeds(int count) {
		List<RSSFeedBean> feeds = null;
		try {

			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			URL u = new URL("http://www.vogella.com/article.rss");
			Document doc = builder.parse(u.openStream());
			NodeList nodes = doc.getElementsByTagName(RSS_ITEM_ATTRIBUE);

			if (nodes != null) {
				feeds = new ArrayList<RSSFeedBean>();
				for (int feedIndex = 0; feedIndex < count; feedIndex++) {
					// if(feedIndex < Integer.parseInt(this.maxFeedCount)){
					// if (feedIndex < Integer.parseInt(this.maxFeedCount)) {
					Element element = (Element) nodes.item(feedIndex);

					feeds.add(new RSSFeedBean(getElementValue(element,
							RSS_TITLE_ATTRIBUE), getElementValue(element,
							RSS_DESCRIPTION_ATTRIBUE), getElementValue(element,
							RSS_LINK_ATTRIBUE), getElementValue(element,
							RSS_AUTHOR_ATTRIBUE), getElementValue(element,
							RSS_GUILD_ATTRIBUE)));

				}
				// else {
				// break;
				// }
			}

		} catch (Exception ex) {
			LOG.error("Exception is ", ex);
			LOG.info("RSSFeedComponent called");
			System.out.println("data is" + feeds);

		}
		return feeds;
	}

	public String getElementValue(Element parent, String label) {
		return getCharacterDataFromElement((Element) parent
				.getElementsByTagName(label).item(0));
	}

	public String getCharacterDataFromElement(Element e) {
		if (e != null) {
			try {
				Node child = e.getFirstChild();
				if (child instanceof CharacterData) {
					CharacterData cd = (CharacterData) child;
					return cd.getData();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return " ";
	}

}
