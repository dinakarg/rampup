package com.ndtv.model;

import java.io.Serializable;

/**
 * The Class HeroBean.
 */
public class HeroBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The title. */
    private String title;

    /** The path. */
    private String path;

    /**
     * Gets the title.
     * 
     * @return the title
     */
    public final String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     * 
     * @param paramTitle
     *            the new paramTitle
     */
    public final void setTitle(final String paramTitle) {
        this.title = paramTitle;
    }

    /**
     * Gets the path.
     * 
     * @return the path
     */
    public final String getPath() {
        return path;
    }

    /**
     * Sets the path.
     * 
     * @param paramPath
     *            the new paramPath
     */
    public final void setPath(final String paramPath) {
        this.path = paramPath;
    }

}
