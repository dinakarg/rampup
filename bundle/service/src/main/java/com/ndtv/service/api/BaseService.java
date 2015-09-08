/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.ndtv.service.api;

import javax.jcr.LoginException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**.
 * The Class BaseService
 * @author kumarip
 *
 */
@Component
public abstract class BaseService {
    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory
            .getLogger(BaseService.class);

    /**
     * The admin session.
     */
    private Session adminSession = null;
    /**
     * The repository.
     */
    @Reference   
    protected SlingRepository repository;

    /**
     * @return Session
     * @throws RepositoryException
     */
    protected final Session getSession() {
        LOG.info("Retrieving JCR session");
        if (adminSession == null) {
            try {
                adminSession = repository.loginAdministrative(null);
            } catch (LoginException e) {
                LOG.info(e.getMessage());
            } catch (RepositoryException e) {
                LOG.info(e.getMessage());
            }
        }

        if (adminSession == null) {
            LOG.error("Admin session is null");
        }

        LOG.info("Returing JCR session ");
        return adminSession;
    }

}
