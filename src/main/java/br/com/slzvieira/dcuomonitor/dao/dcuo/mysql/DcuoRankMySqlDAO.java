/*
 * @(#)DcuoRankMySqlDAO.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
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
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoRankDAO;
import br.com.slzvieira.dcuomonitor.model.DcuoRank;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoRankMySqlDAO extends AbstractMySqlDAO implements DcuoRankDAO {

    /**
     * TODO DOCUMENT ME!
     * 
     * @param id ID do rank.
     * @return
     * @throws DAOException Falha ao realizar consulta.
     */
    public DcuoRank findById(int id) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoRank rank = null;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("SELECT rank_name FROM tab_rank WHERE rank_code = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                rank = new DcuoRank();
                rank.setId(id);
                rank.setName(rs.getString("rank_name"));
            }

            return rank;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter nome da rank.", sqle);
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
    public DcuoRank findByName(String name) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoRank rank = null;

        try {

            cn = getConnection();

            if (name == null) {
                ps = cn.prepareStatement("SELECT rank_code FROM tab_rank WHERE rank_name IS NULL");
            } else {
                ps = cn.prepareStatement("SELECT rank_code FROM tab_rank WHERE rank_name = ?");
                ps.setString(1, name);
            }

            rs = ps.executeQuery();

            if (rs.next()) {
                rank = new DcuoRank();
                rank.setId(rs.getInt("rank_code"));
                rank.setName(name);
            }

            return rank;

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
    public List<DcuoRank> findAll() throws DAOException {

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        DcuoRank rank = null;
        List<DcuoRank> rankList = new ArrayList<DcuoRank>();

        try {

            cn = getConnection();
            st = cn.createStatement();
            rs = st.executeQuery("SELECT rank_code, rank_name FROM tab_rank ORDER BY rank_name");

            while (rs.next()) {
                rank = new DcuoRank();
                rank.setId(rs.getInt("rank_code"));
                rank.setName(rs.getString("rank_name"));
                rankList.add(rank);
            }

            return rankList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter lista de ranks.", sqle);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param rank
     * @return
     * @throws DAOException 
     */
    public DcuoRank persist(DcuoRank rank) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        int newId = 0;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("INSERT INTO tab_rank (rank_code, rank_name) VALUES (?, ?)");
            ps.setInt(1, rank.getId());
            ps.setString(2, rank.getName());
            ps.executeUpdate();
            rank.setId(newId);

            return rank;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao persistir rank.", sqle);
        } finally {
            closeResources(cn, ps, null);
        }
    }
}
