package com.ndtv.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author SRASHMI
 * 
 */
public class TopStoryBean implements Serializable {
    /** The Constant serialVersionUID. */
    private static  long serialVersionUID = 1L;
    /** The title. */
    private String title;
    /** The news. */
    private List<String> news;

    /**
     * Gets the news.
     * 
     * @return the news
     */
    public  List<String> getNews() {
        return news;
    }

    /**
     * Sets the news.
     * 
     * @param news1
     *            the new news
     */
    public  void setNews(List<String> news1) {
        this.news = news1;
    }

    /**
     * Gets the title.
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     * 
     * @param title1
     *            the new title
     */
    public  void setTitle(String title1) {
        this.title = title1;
    }
    /**
     * Gets the serialversionuid. s
     * 
     * @return the serialversionuid
     */
}
