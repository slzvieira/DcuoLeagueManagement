/*
 * @(#)DcuoRoleDerbyDAO.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.dao.dcuo.derby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.slzvieira.dcuomonitor.dao.DAOException;
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoRoleDAO;
import br.com.slzvieira.dcuomonitor.model.DcuoRole;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoRoleDerbyDAO extends AbstractDerbyDAO implements DcuoRoleDAO {

    /**
     * TODO DOCUMENT ME!
     * 
     * @param id ID do role.
     * @return
     * @throws DAOException Falha ao realizar consulta.
     */
    public DcuoRole findById(int id) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoRole role = null;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("SELECT role_name FROM tab_role WHERE role_code = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                role = new DcuoRole();
                role.setId(id);
                role.setName(rs.getString("role_name"));
            }

            return role;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter nome da role.", sqle);
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
    public DcuoRole findByName(String name) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoRole role = null;

        try {

            cn = getConnection();

            if (name == null) {
                ps = cn.prepareStatement("SELECT role_code FROM tab_role WHERE role_name IS NULL");
            } else {
                ps = cn.prepareStatement("SELECT role_code FROM tab_role WHERE role_name = ?");
                ps.setString(1, name);
            }

            rs = ps.executeQuery();

            if (rs.next()) {
                role = new DcuoRole();
                role.setId(rs.getInt("role_code"));
                role.setName(name);
            }

            return role;

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
    public List<DcuoRole> findAll() throws DAOException {

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        DcuoRole role = null;
        List<DcuoRole> roleList = new ArrayList<DcuoRole>();

        try {

            cn = getConnection();
            st = cn.createStatement();
            rs = st.executeQuery("SELECT role_code, role_name FROM tab_role ORDER BY role_name");

            while (rs.next()) {
                role = new DcuoRole();
                role.setId(rs.getInt("role_code"));
                role.setName(rs.getString("role_name"));
                roleList.add(role);
            }

            return roleList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter lista de roles.", sqle);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param role
     * @return
     * @throws DAOException 
     */
    public DcuoRole persist(DcuoRole role) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        int newId = 0;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("INSERT INTO tab_role (role_name) VALUES (?)");
            ps.setString(1, role.getName());
            ps.executeUpdate();

            newId = findLastInsertId();
            role.setId(newId);

            return role;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao persistir role.", sqle);
        } finally {
            closeResources(cn, ps, null);
        }
    }
}
