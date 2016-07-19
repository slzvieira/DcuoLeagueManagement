/*
 * @(#)DcuoPersonDAO.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.persistence;

import br.com.rgflorencio.dcuomonitor.model.DcuoPerson;


/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public interface DcuoPersonDAO extends DAO {

    /**
     * TODO DOCUMENT ME!
     * @param personalityName
     * @return
     * @throws DAOException
     */
    DcuoPerson findByName(String personalityName) throws DAOException;

    /**
     * TODO DOCUMENT ME!
     * @param person
     * @throws DAOException
     */
    DcuoPerson persist(DcuoPerson person) throws DAOException;
}
