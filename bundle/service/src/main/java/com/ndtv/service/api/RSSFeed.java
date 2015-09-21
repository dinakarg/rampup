package com.ndtv.service.api;

import java.util.List;


import com.ndtv.service.model.RSSFeedBean;

public interface RSSFeed {



	List<RSSFeedBean> getRssFeeds(int count);
	
	
	

}
