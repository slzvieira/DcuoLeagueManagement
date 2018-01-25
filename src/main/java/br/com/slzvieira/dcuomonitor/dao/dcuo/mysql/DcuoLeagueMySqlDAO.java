/*
 * @(#)DcuoLeagueMySqlDAO.java 1.00 30/06/2016 Copyright 2016 CPA Consulting LTDA.
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
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoLeagueDAO;
import br.com.slzvieira.dcuomonitor.model.DcuoLeague;
import br.com.slzvieira.dcuomonitor.model.DcuoMorality;
import br.com.slzvieira.dcuomonitor.model.DcuoWorld;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 30/06/2016 - sandro.vieira - Implementacao.
 */
public class DcuoLeagueMySqlDAO extends AbstractMySqlDAO implements DcuoLeagueDAO {

    /** Query SQL para busca de ligas. */
    private static final String SQL_SELECT_FULL = "SELECT league_code, league_census_id, league_name, world_code, alignment_code FROM tab_league";

    /** TODO DOCUMENT ME! */
    private static final String SQL_UPDATE = "UPDATE tab_league SET league_census_id = ?, league_name = ?, world_code = ?, alignment_code = ? WHERE league_code = ?";

    /** TODO DOCUMENT ME! */
    private static final String SQL_INSERT = "INSERT INTO tab_league (league_census_id, league_name, world_code, alignment_code) VALUES (?, ?, ?, ?)";

    /* (non-Javadoc)
     * @see br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoLeagueDAO#findAll()
     */
    public List<DcuoLeague> findAll() throws DAOException {

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        List<DcuoLeague> leagueList = new ArrayList<>();
        DcuoLeague league;

        try {

            cn = getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(SQL_SELECT_FULL + " ORDER BY league_name");

            while (rs.next()) {
                league = new DcuoLeague();
                league.setId(rs.getInt("league_code"));
                league.setCensusId(rs.getLong("league_census_id"));
                league.setName(rs.getString("league_name"));
                league.setWorld(DcuoWorld.getById(rs.getInt("world_code")));
                league.setMorality(DcuoMorality.getById(rs.getInt("alignment_code")));
                leagueList.add(league);
            }

            return leagueList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter lista de ligas.", sqle);
        } finally {
            closeResources(cn, st, rs);
        }
    }

    /* (non-Javadoc)
     * @see br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoLeagueDAO#findByDcuoId(long)
     */
    public DcuoLeague findByCensusCode(long censusId) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoLeague league = null;

        try {

            cn = getConnection();
            ps = cn.prepareStatement(SQL_SELECT_FULL + " WHERE league_census_id = ?");
            ps.setLong(1, censusId);
            rs = ps.executeQuery();

            if (rs.next()) {
                league = getNextRow(rs);
            }

            return league;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter dados da liga.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }

    /* (non-Javadoc)
     * @see DcuoLeagueDAO#persist(DcuoLeague)
     */
    public DcuoLeague persist(DcuoLeague league) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        DcuoLeague existentLeague = findByCensusCode(league.getCensusId());

        try {
            cn = getConnection();

            if (existentLeague != null) {

                league.setId(existentLeague.getId());
                ps = cn.prepareStatement(SQL_UPDATE);

                ps.setLong(1, league.getCensusId());
                ps.setString(2, league.getName());
                ps.setInt(3, league.getWorld().getId());
                ps.setLong(4, league.getMorality().getId());
                ps.setLong(5, league.getId());

                ps.executeUpdate();

            } else {

                ps = cn.prepareStatement(SQL_INSERT);

                ps.setLong(1, league.getCensusId());
                ps.setString(2, league.getName());
                ps.setInt(3, league.getWorld().getId());
                ps.setLong(4, league.getMorality().getId());

                ps.executeUpdate();
                league.setId(findLastInsertId());
            }

            return league;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao persistir liga.", sqle);
        } finally {
            closeResources(cn, ps, null);
        }
    }

    /**
     * Instancia e preenche um Vo a referente a proxima linha do resultset especificado
     * 
     * @param rs
     * @return
     * @throws SQLException
     */
    private DcuoLeague getNextRow(ResultSet rs) throws SQLException {

        DcuoLeague league = new DcuoLeague();

        league.setId(rs.getInt("league_code"));
        league.setCensusId(rs.getLong("league_census_id"));
        league.setName(rs.getString("league_name"));
        league.setWorld(DcuoWorld.getById(rs.getInt("world_code")));
        league.setMorality(DcuoMorality.getById(rs.getInt("alignment_code")));

        return league;
    }
}
