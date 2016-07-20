/*
 * @(#)DcuoCharacterHistoryDerbyDAO.java 1.00 19/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.dao.dcuo.derby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rgflorencio.dcuomonitor.dao.DAOException;
import br.com.rgflorencio.dcuomonitor.dao.dcuo.DcuoCharacterHistoryDAO;
import br.com.rgflorencio.dcuomonitor.model.DcuoCharacterHistory;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 19/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoCharacterHistoryDerbyDAO extends AbstractDerbyDAO implements DcuoCharacterHistoryDAO {

    private static final String SQL_INSERT =
            "INSERT INTO tab_character_history (char_code, entry_code, power_code, pve_cr_val," +
            " pvp_cr_val, skill_val, rank_code, mvmnt_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_INCREASED_CR =
            "SELECT c.char_name FROM tab_character c JOIN tab_character_history h1 ON" +
            " c.char_code = h1.char_code AND h1.entry_code = ? JOIN tab_character_history h2 ON" +
            " c.char_code = h2.char_code AND h2.entry_code = ? WHERE h1.pve_cr_val < h2.pve_cr_val " +
            "ORDER BY c.char_name";

    private static final String FIND_INCREASED_SP =
            "SELECT c.char_name FROM tab_character c JOIN tab_character_history h1 ON"+
            " c.char_code = h1.char_code AND h1.entry_code = ? JOIN tab_character_history h2 ON" +
            " c.char_code = h2.char_code AND h2.entry_code = ? WHERE h1.skill_val < h2.skill_val " +
            "ORDER BY c.char_name";

    private static final String FIND_ENTERED =
            "SELECT char_name FROM tab_character WHERE char_code NOT IN (" +
            "SELECT char_code FROM tab_character_history WHERE entry_code = ?) AND char_code IN (" +
            "SELECT char_code FROM tab_character_history WHERE entry_code = ?) " +
            "ORDER BY char_name";

    private static final String FIND_EXITED =
            "SELECT char_name FROM tab_character WHERE char_code IN (" +
            "SELECT char_code FROM tab_character_history WHERE entry_code = ?) AND char_code NOT IN (" +
            "SELECT char_code FROM tab_character_history WHERE entry_code = ?) " +
            "ORDER BY char_name";

    /**
     * TODO DOCUMENT ME!
     * @param characterHistory
     * @return
     * @throws DAOException 
     */
    public DcuoCharacterHistory persist(DcuoCharacterHistory characterHistory) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;

        try {

            cn = getConnection();
            ps = cn.prepareStatement(SQL_INSERT);

            ps.setInt(1, characterHistory.getCharId());
            ps.setInt(2, characterHistory.getEntryId());
            ps.setInt(3, characterHistory.getPowerId());
            ps.setInt(4, characterHistory.getCombatRating());
            ps.setInt(5, characterHistory.getCombatRatingPvp());
            ps.setInt(6, characterHistory.getSkillPoints());
            ps.setInt(7, characterHistory.getRankId());
            ps.setInt(8, characterHistory.getMovementId());

            ps.executeUpdate();
            characterHistory.setId(findLastInsertId());

            return characterHistory;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao persistir historico.", sqle);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param currentEventId
     * @param previousEventId
     * @return
     * @throws DAOException 
     */
    public List<String> findIncreasedCRNameByEventInterval(int currentEventId, int previousEventId) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> nameList = new ArrayList<>();

        try {

            cn = getConnection();
            ps = cn.prepareStatement(FIND_INCREASED_CR);
            ps.setInt(1, previousEventId);
            ps.setInt(2, currentEventId);
            rs = ps.executeQuery();

            while (rs.next()) {
                nameList.add(rs.getString("char_name"));
            }

            return nameList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter eventos de CR.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }


    /**
     * TODO DOCUMENT ME!
     * @param currentEventId
     * @param previousEventId
     * @return
     * @throws DAOException 
     */
    public List<String> findIncreasedSPNameByEventInterval(int currentEventId, int previousEventId) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> nameList = new ArrayList<>();

        try {

            cn = getConnection();
            ps = cn.prepareStatement(FIND_INCREASED_SP);
            ps.setInt(1, previousEventId);
            ps.setInt(2, currentEventId);
            rs = ps.executeQuery();

            while (rs.next()) {
                nameList.add(rs.getString("char_name"));
            }

            return nameList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter eventos de CR.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }


    /**
     * TODO DOCUMENT ME!
     * @param currentEventId
     * @param previousEventId
     * @return
     * @throws DAOException 
     */
    public List<String> findEnteredNameByEventInterval(int currentEventId, int previousEventId) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> nameList = new ArrayList<>();

        try {

            cn = getConnection();
            ps = cn.prepareStatement(FIND_ENTERED);
            ps.setInt(1, previousEventId);
            ps.setInt(2, currentEventId);
            rs = ps.executeQuery();

            while (rs.next()) {
                nameList.add(rs.getString("char_name"));
            }

            return nameList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter eventos de CR.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }


    /**
     * TODO DOCUMENT ME!
     * @param currentEventId
     * @param previousEventId
     * @return
     * @throws DAOException 
     */
    public List<String> findExitedNameByEventInterval(int currentEventId, int previousEventId) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> nameList = new ArrayList<>();

        try {

            cn = getConnection();
            ps = cn.prepareStatement(FIND_EXITED);
            ps.setInt(1, previousEventId);
            ps.setInt(2, currentEventId);
            rs = ps.executeQuery();

            while (rs.next()) {
                nameList.add(rs.getString("char_name"));
            }

            return nameList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter eventos de CR.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }
}
