/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.ndtv.service.exception;

/**
 * The Class GenericException.
 */
public class GenericServiceException extends Exception {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new application exception.
     *
     * @param message the message
     */
    public GenericServiceException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new application exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public GenericServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new application exception.
     *
     * @param cause the cause
     */
    public GenericServiceException(final Throwable cause) {
        super(cause);
    }

}
