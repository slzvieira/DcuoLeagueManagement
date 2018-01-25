/*
 * @(#)DcuoRegionDAO.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.dao.dcuo;

import br.com.slzvieira.dcuomonitor.dao.DAOException;
import br.com.slzvieira.dcuomonitor.dao.TransactionalDAO;
import br.com.slzvieira.dcuomonitor.model.DcuoRegion;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public interface DcuoRegionDAO extends TransactionalDAO {

    /**
     * TODO DOCUMENT ME!
     * @param regionName
     * @return
     * @throws DAOException
     */
    DcuoRegion findByName(String regionName) throws DAOException;

    /**
     * TODO DOCUMENT ME!
     * @param region
     * @throws DAOException
     */
    DcuoRegion persist(DcuoRegion region) throws DAOException;
}
