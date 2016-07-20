/*
 * @(#)DcuoRegionMySqlDAO.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.dao.dcuo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.rgflorencio.dcuomonitor.dao.DAOException;
import br.com.rgflorencio.dcuomonitor.dao.dcuo.DcuoRegionDAO;
import br.com.rgflorencio.dcuomonitor.model.DcuoRegion;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoRegionMySqlDAO extends AbstractMySqlDAO implements DcuoRegionDAO {

    /**
     * TODO DOCUMENT ME!
     * 
     * @param id ID do region.
     * @return
     * @throws DAOException Falha ao realizar consulta.
     */
    public DcuoRegion findById(int id) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoRegion region = null;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("SELECT region_name FROM tab_region WHERE region_code = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                region = new DcuoRegion();
                region.setId(id);
                region.setName(rs.getString("region_name"));
            }

            return region;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter nome da region.", sqle);
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
    public DcuoRegion findByName(String name) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoRegion region = null;

        try {

            cn = getConnection();

            if (name == null) {
                ps = cn.prepareStatement("SELECT region_code FROM tab_region WHERE region_name IS NULL");
            } else {
                ps = cn.prepareStatement("SELECT region_code FROM tab_region WHERE region_name = ?");
                ps.setString(1, name);
            }

            rs = ps.executeQuery();

            if (rs.next()) {
                region = new DcuoRegion();
                region.setId(rs.getInt("region_code"));
                region.setName(name);
            }

            return region;

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
    public List<DcuoRegion> findAll() throws DAOException {

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        DcuoRegion region = null;
        List<DcuoRegion> regionList = new ArrayList<DcuoRegion>();

        try {

            cn = getConnection();
            st = cn.createStatement();
            rs = st.executeQuery("SELECT region_code, region_name FROM tab_region ORDER BY region_name");

            while (rs.next()) {
                region = new DcuoRegion();
                region.setId(rs.getInt("region_code"));
                region.setName(rs.getString("region_name"));
                regionList.add(region);
            }

            return regionList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter lista de regions.", sqle);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param region
     * @return
     * @throws DAOException 
     */
    public DcuoRegion persist(DcuoRegion region) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        int newId = 0;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("INSERT INTO tab_region (region_name) VALUES (?)");
            ps.setString(1, region.getName());
            ps.executeUpdate();

            newId = findLastInsertId();
            region.setId(newId);

            return region;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao persistir region.", sqle);
        } finally {
            closeResources(cn, ps, null);
        }
    }
}
