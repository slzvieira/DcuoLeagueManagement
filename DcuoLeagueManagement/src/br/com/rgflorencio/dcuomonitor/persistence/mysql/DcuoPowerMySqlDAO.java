/*
 * @(#)DcuoPowerMySqlDAO.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.persistence.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.rgflorencio.dcuomonitor.model.DcuoPower;
import br.com.rgflorencio.dcuomonitor.persistence.DAOException;
import br.com.rgflorencio.dcuomonitor.persistence.DcuoPowerDAO;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoPowerMySqlDAO extends AbstractMySqlDAO implements DcuoPowerDAO {

    /**
     * TODO DOCUMENT ME!
     * 
     * @param id ID do power.
     * @return
     * @throws DAOException Falha ao realizar consulta.
     */
    public DcuoPower findById(int id) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoPower power = null;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("SELECT power_name FROM tab_power WHERE power_code = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                power = new DcuoPower();
                power.setId(id);
                power.setName(rs.getString("power_name"));
            }

            return power;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter nome da power.", sqle);
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
    public DcuoPower findByName(String name) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoPower power = null;

        try {

            cn = getConnection();

            if (name == null) {
                ps = cn.prepareStatement("SELECT power_code FROM tab_power WHERE power_name IS NULL");
            } else {
                ps = cn.prepareStatement("SELECT power_code FROM tab_power WHERE power_name = ?");
                ps.setString(1, name);
            }

            rs = ps.executeQuery();

            if (rs.next()) {
                power = new DcuoPower();
                power.setId(rs.getInt("power_code"));
                power.setName(name);
            }

            return power;

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
    public List<DcuoPower> findAll() throws DAOException {

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        DcuoPower power = null;
        List<DcuoPower> powerList = new ArrayList<DcuoPower>();

        try {

            cn = getConnection();
            st = cn.createStatement();
            rs = st.executeQuery("SELECT power_code, power_name FROM tab_power ORDER BY power_name");

            while (rs.next()) {
                power = new DcuoPower();
                power.setId(rs.getInt("power_code"));
                power.setName(rs.getString("power_name"));
                powerList.add(power);
            }

            return powerList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter lista de powers.", sqle);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param power
     * @return
     * @throws DAOException 
     */
    public DcuoPower persist(DcuoPower power) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        int newId = 0;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("INSERT INTO tab_power (power_name) VALUES (?)");
            ps.setString(1, power.getName());
            ps.executeUpdate();

            newId = findLastInsertId();
            power.setId(newId);

            return power;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao persistir power.", sqle);
        } finally {
            closeResources(cn, ps, null);
        }
    }
}
