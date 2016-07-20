/*
 * @(#)DcuoEntryDAO.java 1.00 25/08/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.dao.dcuo;

import java.util.Date;
import java.util.List;

import br.com.rgflorencio.dcuomonitor.dao.DAOException;
import br.com.rgflorencio.dcuomonitor.dao.TransactionalDAO;
import br.com.rgflorencio.dcuomonitor.model.DcuoEntry;
import br.com.rgflorencio.dcuomonitor.model.DcuoEntryEvent;
import br.com.rgflorencio.dcuomonitor.model.DcuoSummaryStatus;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 25/08/2015 - sandro.vieira - Implementacao.
 */
public interface DcuoEntryDAO extends TransactionalDAO {

    /**
     * TODO DOCUMENT ME!
     * @return
     * @throws DAOException
     */
    List<DcuoEntryEvent> findAllEvents() throws DAOException;

    /**
     * TODO DOCUMENT ME!
     * @param startDate 
     * @param endDate 
     * @return
     * @throws DAOException
     */
    List<DcuoSummaryStatus> findAllSummaryStatus(Date startDate, Date endDate) throws DAOException;

    /**
     * TODO DOCUMENT ME!
     * @param entry
     * @return
     * @throws DAOException
     */
    DcuoEntry persist(DcuoEntry entry) throws DAOException;
}
