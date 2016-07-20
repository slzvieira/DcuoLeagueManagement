/*
 * @(#)AbstractDcuoDAO.java 1.00 15/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.dao.dcuo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.rgflorencio.dcuomonitor.PropertiesManager;
import br.com.rgflorencio.dcuomonitor.dao.DAOException;
import br.com.rgflorencio.dcuomonitor.dao.TransactionalDAO;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 15/05/2015 - sandro.vieira - Implementacao.
 */
public abstract class AbstractDcuoDAO implements TransactionalDAO {

    private static final String DB_DRIVER = PropertiesManager.getInstance().getProperty("db.driver");
    private static final String DB_URL = PropertiesManager.getInstance().getProperty("db.url");
    private static final String DB_USER = PropertiesManager.getInstance().getProperty("db.user");
    private static final String DB_PASS = PropertiesManager.getInstance().getProperty("db.password");
    
    /** TODO DOCUMENT ME! */
    private static Connection connection;
    
    /** TODO DOCUMENT ME! */
    private static boolean autoCommit = true;

    /**
     * TODO DOCUMENT ME!
     * @return
     * @throws Exception
     */
    protected Connection getConnection() throws DAOException {

        try {

            if (connection == null || connection.isClosed()) {

                Class.forName(DB_DRIVER);

                if (DB_USER == null || DB_PASS == null) {
                    connection = DriverManager.getConnection(DB_URL);
                } else {
                    connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                }
            }
            
            return connection;

        } catch (ClassNotFoundException cnfe) {
            throw new DAOException("Falha ao carregar driver de acesso.", cnfe);
        } catch (SQLException sqle) {
            throw new DAOException("Falha ao abrir conexao.", sqle);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @throws DAOException
     */
    public void startTransaction() throws DAOException {
        
        try {
            Connection connection = getConnection();
            autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot start the transaction.", e);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @throws DAOException
     */
    public void commitTransaction() throws DAOException {
        
        try {
            Connection connection = getConnection();
            connection.commit();
            connection.setAutoCommit(autoCommit);
        } catch (SQLException sqle) {
            throw new DAOException("Cannot start the transaction.", sqle);
        }
    }

    /**
     * TODO DOCUMENT ME!
     */
    public void rollbackTransaction() {
        
        try {
            Connection connection = getConnection();
            connection.rollback();
            connection.setAutoCommit(autoCommit);
        } catch (SQLException | DAOException e) {
            throw new RuntimeException("Cannot start the transaction.", e);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param cn
     * @param st
     * @param rs
     */
    protected void closeResources(Connection cn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqle) {}
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException sqle) {}
        }
//        if (cn != null) {
//            try {
//                cn.close();
//            } catch (SQLException sqle) {}
//        }
    }
}
