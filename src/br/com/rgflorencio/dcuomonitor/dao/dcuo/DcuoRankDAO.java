/*
 * @(#)DcuoRankDAO.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a c�pia e-ou a reprodu��o deste c�digo.
 */
package br.com.rgflorencio.dcuomonitor.dao.dcuo;

import br.com.rgflorencio.dcuomonitor.dao.DAOException;
import br.com.rgflorencio.dcuomonitor.dao.TransactionalDAO;
import br.com.rgflorencio.dcuomonitor.model.DcuoRank;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public interface DcuoRankDAO extends TransactionalDAO {

    /**
     * TODO DOCUMENT ME!
     * @param rankId
     * @return
     * @throws DAOException
     */
    DcuoRank findById(int rankId) throws DAOException;

    /**
     * TODO DOCUMENT ME!
     * @param rank
     * @throws DAOException
     */
    DcuoRank persist(DcuoRank rank) throws DAOException;
}
