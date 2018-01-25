/*
 * @(#)DcuoPersonDAO.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a c�pia e-ou a reprodu��o deste c�digo.
 */
package br.com.slzvieira.dcuomonitor.dao.dcuo;

import br.com.slzvieira.dcuomonitor.dao.DAOException;
import br.com.slzvieira.dcuomonitor.dao.TransactionalDAO;
import br.com.slzvieira.dcuomonitor.model.DcuoPerson;


/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public interface DcuoPersonDAO extends TransactionalDAO {

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
