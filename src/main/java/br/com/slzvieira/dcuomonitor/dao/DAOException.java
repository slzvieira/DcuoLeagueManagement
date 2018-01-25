/*
 * @(#)DAOException.java 1.00 15/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.dao;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 15/05/2015 - sandro.vieira - Implementacao.
 */
public class DAOException extends Exception {

    /** TODO DOCUMENT ME! */
    private static final long serialVersionUID = 1L;

    public DAOException() {
        super();
    }

    /**
     * TODO DOCUMENT ME!
     * @param message
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * TODO DOCUMENT ME!
     * @param cause
     */
    public DAOException(Throwable cause) {
        super(cause);
    }

    /**
     * TODO DOCUMENT ME!
     * @param message
     * @param cause
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
