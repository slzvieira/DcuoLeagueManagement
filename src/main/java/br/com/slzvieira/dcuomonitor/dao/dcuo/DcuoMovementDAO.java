/*
 * @(#)DcuoMovementDAO.java 1.00 25/08/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.dao.dcuo;

import br.com.slzvieira.dcuomonitor.dao.DAOException;
import br.com.slzvieira.dcuomonitor.dao.TransactionalDAO;
import br.com.slzvieira.dcuomonitor.model.DcuoMovement;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 25/08/2015 - sandro.vieira - Implementacao.
 */
public interface DcuoMovementDAO extends TransactionalDAO {

    /**
     * TODO DOCUMENT ME!
     * @param movementName
     * @return
     * @throws DAOException
     */
    DcuoMovement findByName(String movementName) throws DAOException;

    /**
     * TODO DOCUMENT ME!
     * @param movement
     * @throws DAOException
     */
    DcuoMovement persist(DcuoMovement movement) throws DAOException;
}
