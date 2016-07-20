/*
 * @(#)DcuoLeagueDAO.java 1.00 30/06/2016 Copyright 2016 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.dao.dcuo;

import java.util.List;

import br.com.rgflorencio.dcuomonitor.dao.DAOException;
import br.com.rgflorencio.dcuomonitor.model.DcuoLeague;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 30/06/2016 - sandro.vieira - Implementacao.
 */
public interface DcuoLeagueDAO {

    /**
     * Busca uma liga a partir de seu census id.
     * @param censusId
     * @return
     * @throws DAOException
     */
    DcuoLeague findByCensusCode(long censusId) throws DAOException;

    /**
     * Persiste (insere ou altera) os dados da liga especificada.
     * 
     * @param league
     * @return
     * @throws DAOException
     */
    DcuoLeague persist(DcuoLeague league) throws DAOException;

    /**
     * Obtem a lista de todas as ligas, ordenada por nome.
     * @return
     * @throws DAOException
     */
    List<DcuoLeague> findAll() throws DAOException;
}
