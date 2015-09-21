
package com.ndtv.service.model;
/**
 * The Class RSSFeed.
 */
public class RSSFeedBean {

    /** The title. */
    private String title;

    /** The description. */
    private String description;

    /** The link. */
    private String link;

    /** The author. */
    private String author;

    /** The guild. */
    private String guild;

    /**
     * Instantiates a new RSS feed.
     * 
     * @param item
     *            the item
     * @param title
     *            the title
     * @param description
     *            the description
     * @param link
     *            the link
     * @param author
     *            the author
     * @param guild
     *            the guild
     */
    public RSSFeedBean(String title, String description, String link,
            String author, String guild) {
        super();

        this.title = title;
        this.description = description;
        this.link = link;
        this.author = author;
        this.guild = guild;
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
     * @param title
     *            the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the link.
     * 
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the link.
     * 
     * @param link
     *            the new link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Gets the guild.
     * 
     * @return the guild
     */
    public String getGuild() {
        return guild;
    }

    /**
     * Sets the guild.
     * 
     * @param guild
     *            the guild to set
     */
    public void setGuild(String guild) {
        this.guild = guild;
    }

    /**
     * Gets the author.
     * 
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author.
     * 
     * @param author
     *            the new author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "title=" + title + ", description=" + description
				+ ", link=" + link + ", author=" + author + ", guild=" + guild
				+ "";
		
	}

	/**
     * Gets the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     * 
     * @param description
     *            the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}