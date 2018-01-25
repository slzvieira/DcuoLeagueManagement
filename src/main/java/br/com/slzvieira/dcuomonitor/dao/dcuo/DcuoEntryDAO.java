/*
 * @(#)DcuoEntryDAO.java 1.00 25/08/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.dao.dcuo;

import java.util.Date;
import java.util.List;

import br.com.slzvieira.dcuomonitor.dao.DAOException;
import br.com.slzvieira.dcuomonitor.dao.TransactionalDAO;
import br.com.slzvieira.dcuomonitor.model.DcuoEntry;
import br.com.slzvieira.dcuomonitor.model.DcuoEntryEvent;
import br.com.slzvieira.dcuomonitor.model.DcuoSummaryStatus;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 25/08/2015 - sandro.vieira - Implementacao.
 */
public interface DcuoEntryDAO extends TransactionalDAO {

    /**
     * TODO DOCUMENT ME!
     * @param leagueId 
     * @return
     * @throws DAOException
     */
    List<DcuoEntryEvent> findEventsByLeagueId(int leagueId) throws DAOException;

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
