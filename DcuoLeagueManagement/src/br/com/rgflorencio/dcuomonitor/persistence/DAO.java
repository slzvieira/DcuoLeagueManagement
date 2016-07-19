/*
 * @(#)DAO.java 1.00 25/08/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.persistence;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 25/08/2015 - sandro.vieira - Implementacao.
 */
public interface DAO {

    /**
     * TODO DOCUMENT ME!
     * @throws DAOException
     */
    public void startTransaction() throws DAOException;

    /**
     * TODO DOCUMENT ME!
     * @throws DAOException
     */
    public void commitTransaction() throws DAOException;

    /**
     * TODO DOCUMENT ME!
     */
    public void rollbackTransaction();
}
