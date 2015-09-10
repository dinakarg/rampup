package com.ndtv.action;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ndtv.model.ImageBean;

//import com.virtusa.training.model.ImageGalleryBean;

/**
 * The Class ImageGalleryAction.
 */
public class ImageGalleryAction extends BaseAction {

    /** The log. */
    private static final Logger LOG = LoggerFactory
            .getLogger(ImageGalleryAction.class);
    /**
     * Image gallery.
     *
     * @return the image bean
     */
    public final ImageBean imageGallery() {
        ImageBean ib = new ImageBean();
        Node node = getCurrentNode();
        if (null != node) {
            try {
                ib.setTitle(node.getProperty("title").getString());
                ib.setDescription(node.getProperty("description").getString());
                ib.setLinkURL(node.getProperty("linkURL").getString());
                ib.setImage(node.getProperty("image").getString());
                ib.setTitle1(node.getProperty("title1").getString());
                ib.setDescription1(node.getProperty("description1")
                  .getString());
                ib.setLinkURL1(node.getProperty("linkURL1").getString());
                ib.setImage1(node.getProperty("image1").getString());
                ib.setTitle2(node.getProperty("title2").getString());
                ib.setDescription2(node.getProperty("description2")
                .getString());
                ib.setLinkURL2(node.getProperty("linkURL2").getString());
                ib.setImage2(node.getProperty("image2").getString());
            } catch (RepositoryException e) {
                LOG.error("RepositoryException has occured", e);
            }
        }
        return ib;
    }
}
