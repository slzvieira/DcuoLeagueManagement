/*
 * @(#)ServiceException.java 1.00 21/10/2014 Copyright 2014 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.service;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 21/10/2014 - sandro.vieira - Implementacao.
 */
public class ServiceException extends Exception {

    /** TODO DOCUMENT ME! */
    private static final long serialVersionUID = 1L;

    /**
     * TODO DOCUMENT ME!
     */
    public ServiceException() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * TODO DOCUMENT ME!
     * @param message
     * @param cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * TODO DOCUMENT ME!
     * @param message
     */
    public ServiceException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * TODO DOCUMENT ME!
     * @param cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
