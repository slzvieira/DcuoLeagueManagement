/*
 * @(#)DcuoOriginDAO.java 1.00 25/08/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.dao.dcuo;

import br.com.rgflorencio.dcuomonitor.dao.DAOException;
import br.com.rgflorencio.dcuomonitor.dao.TransactionalDAO;
import br.com.rgflorencio.dcuomonitor.model.DcuoOrigin;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 25/08/2015 - sandro.vieira - Implementacao.
 */
public interface DcuoOriginDAO extends TransactionalDAO {

    /**
     * TODO DOCUMENT ME!
     * @param originName
     * @return
     * @throws DAOException
     */
    DcuoOrigin findByName(String originName) throws DAOException;

    /**
     * TODO DOCUMENT ME!
     * @param origin
     * @throws DAOException
     */
    DcuoOrigin persist(DcuoOrigin origin) throws DAOException;
}
