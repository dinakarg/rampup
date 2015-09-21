package com.ndtv.service.servlet;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ndtv.service.impl.RSSFeedImpl;
import com.ndtv.service.model.RSSFeedBean;

/**
 * 
 * @author SRASHMI
 * 
 * 
 */

@SlingServlet(paths = "/bin/mySearchServlet", methods = "GET", metatype = true)
public class RSSFeedServlet extends

org.apache.sling.api.servlets.SlingAllMethodsServlet {

	/**
     * 
     */
	/*
	 * @Reference RSSFeedImpl rss;
	 */

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory
			.getLogger(RSSFeedServlet.class);

	protected void doGet(SlingHttpServletRequest request,

	SlingHttpServletResponse response) throws ServerException,

	IOException {
		LOG.info("RSSFeedComponent called");

		int count = Integer.parseInt(request.getParameter("count"));
		System.out.println("count value -- " + count);
		response.setContentType("application/json");

		response.setCharacterEncoding("utf-8");
		RSSFeedImpl rss = new RSSFeedImpl();
		List<RSSFeedBean> feedss = rss.getRssFeeds(count);

		JSONObject obj = new JSONObject();

		try {
			obj.put("feeds", "{\"test\" : "+feedss+"}");
			// obj.put("name", count);
			for (RSSFeedBean s : feedss) {
				System.out.println(" -- " +s.getTitle());
				System.out.println(" -- " +s.getAuthor());
				System.out.println(" -- " +s.getDescription());
				System.out.println(" -- " +s.getLink());
				
			}
		} catch (JSONException e) {
			LOG.error("JSONException is ", e);
			LOG.info("count is " + count);
			System.out.println("count value" + count);
		}

		//System.out.println("count value" + count);
		//System.out.println("data is" + feedss);

		response.getWriter().print(obj.toString());

	}

}
