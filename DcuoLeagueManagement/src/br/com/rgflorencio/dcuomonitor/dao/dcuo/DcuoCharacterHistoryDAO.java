/*
 * @(#)DcuoCharacterHistoryDAO.java 1.00 25/08/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.dao.dcuo;

import java.util.List;

import br.com.rgflorencio.dcuomonitor.dao.DAOException;
import br.com.rgflorencio.dcuomonitor.dao.TransactionalDAO;
import br.com.rgflorencio.dcuomonitor.model.DcuoCharacterHistory;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 25/08/2015 - sandro.vieira - Implementacao.
 */
public interface DcuoCharacterHistoryDAO extends TransactionalDAO {

    /**
     * TODO DOCUMENT ME!
     * @param currentId
     * @param previousId
     * @return
     * @throws DAOException
     */
    List<String> findEnteredNameByEventInterval(int currentId, int previousId) throws DAOException;

    /**
     * TODO DOCUMENT ME!
     * @param currentId
     * @param previousId
     * @return
     * @throws DAOException
     */
    List<String> findExitedNameByEventInterval(int currentId, int previousId) throws DAOException;

    /**
     * TODO DOCUMENT ME!
     * @param currentId
     * @param previousId
     * @return
     * @throws DAOException
     */
    List<String> findIncreasedCRNameByEventInterval(int currentId, int previousId) throws DAOException;

    /**
     * TODO DOCUMENT ME!
     * @param currentId
     * @param previousId
     * @return
     * @throws DAOException
     */
    List<String> findIncreasedSPNameByEventInterval(int currentId, int previousId) throws DAOException;

    /**
     * TODO DOCUMENT ME!
     * @param characterHistory
     * @throws DAOException
     */
    DcuoCharacterHistory persist(DcuoCharacterHistory characterHistory) throws DAOException;
}
