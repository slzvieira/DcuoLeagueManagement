/*
 * @(#)DcuoOriginMySqlDAO.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.dao.dcuo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.slzvieira.dcuomonitor.dao.DAOException;
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoOriginDAO;
import br.com.slzvieira.dcuomonitor.model.DcuoOrigin;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoOriginMySqlDAO extends AbstractMySqlDAO implements DcuoOriginDAO {

    /**
     * TODO DOCUMENT ME!
     * 
     * @param id ID da origem de poder.
     * @return
     * @throws DAOException Falha ao realizar consulta.
     */
    public DcuoOrigin findById(int id) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoOrigin origin = null;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("SELECT origin_name FROM tab_origin WHERE origin_code = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                origin = new DcuoOrigin();
                origin.setId(id);
                origin.setName(rs.getString("origin_name"));
            }

            return origin;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter nome da origem de poder.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param name
     * @return
     * @throws DAOException 
     */
    public DcuoOrigin findByName(String name) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoOrigin origin = null;

        try {

            cn = getConnection();

            if (name == null) {
                ps = cn.prepareStatement("SELECT origin_code FROM tab_origin WHERE origin_name IS NULL");
            } else {
                ps = cn.prepareStatement("SELECT origin_code FROM tab_origin WHERE origin_name = ?");
                ps.setString(1, name);
            }

            rs = ps.executeQuery();

            if (rs.next()) {
                origin = new DcuoOrigin();
                origin.setId(rs.getInt("origin_code"));
                origin.setName(name);
            }

            return origin;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao pesquisar item.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @return
     * @throws DAOException 
     */
    public List<DcuoOrigin> findAll() throws DAOException {

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        DcuoOrigin origin = null;
        List<DcuoOrigin> originList = new ArrayList<DcuoOrigin>();

        try {

            cn = getConnection();
            st = cn.createStatement();
            rs = st.executeQuery("SELECT origin_code, origin_name FROM tab_origin ORDER BY origin_name");

            while (rs.next()) {
                origin = new DcuoOrigin();
                origin.setId(rs.getInt("origin_code"));
                origin.setName(rs.getString("origin_name"));
                originList.add(origin);
            }

            return originList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter lista de origens de poder.", sqle);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param origin
     * @return
     * @throws DAOException 
     */
    public DcuoOrigin persist(DcuoOrigin origin) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        int newId = 0;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("INSERT INTO tab_origin (origin_name) VALUES (?)");
            ps.setString(1, origin.getName());
            ps.executeUpdate();

            newId = findLastInsertId();
            origin.setId(newId);

            return origin;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao persistir origem de poder.", sqle);
        } finally {
            closeResources(cn, ps, null);
        }
    }
}
