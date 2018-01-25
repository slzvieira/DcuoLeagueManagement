/*
 * @(#)DcuoPowerDAO.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.dao.dcuo;

import br.com.rgflorencio.dcuomonitor.dao.DAOException;
import br.com.rgflorencio.dcuomonitor.dao.TransactionalDAO;
import br.com.rgflorencio.dcuomonitor.model.DcuoPower;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public interface DcuoPowerDAO extends TransactionalDAO {

    /**
     * TODO DOCUMENT ME!
     * @param powerName
     * @return
     * @throws DAOException
     */
    DcuoPower findByName(String powerName) throws DAOException;

    /**
     * TODO DOCUMENT ME!
     * @param power
     * @throws DAOException
     */
    DcuoPower persist(DcuoPower power) throws DAOException;
}
