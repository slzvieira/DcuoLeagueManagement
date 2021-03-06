/*
 * @(#)AbstractDerbyDAO.java 1.00 25/08/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a c�pia e-ou a reprodu��o deste c�digo.
 */
package br.com.slzvieira.dcuomonitor.dao.dcuo.derby;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.slzvieira.dcuomonitor.dao.DAOException;
import br.com.slzvieira.dcuomonitor.dao.dcuo.AbstractDcuoDAO;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 25/08/2015 - sandro.vieira - Implementacao.
 */
public abstract class AbstractDerbyDAO extends AbstractDcuoDAO {

    /**
     * TODO DOCUMENT ME!
     * @return
     * @throws DAOException
     */
    protected int findLastInsertId() throws DAOException {

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            cn = getConnection();
            st = cn.createStatement();
            rs = st.executeQuery("VALUES IDENTITY_VAL_LOCAL()");

            if (rs.next()) {
                return rs.getInt(1);
            }

            return 0;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter novo ID.", sqle);
        } finally {
            closeResources(cn, st, rs);
        }
    }
}
