/*
 * @(#)DcuoCharacterMySqlDAO.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.dao.dcuo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.rgflorencio.dcuomonitor.dao.DAOException;
import br.com.rgflorencio.dcuomonitor.dao.dcuo.DcuoCharacterDAO;
import br.com.rgflorencio.dcuomonitor.model.DcuoCharacter;
import br.com.rgflorencio.dcuomonitor.model.DcuoCharacterEntry;
import br.com.rgflorencio.dcuomonitor.model.DcuoCharacterStatus;
import br.com.rgflorencio.dcuomonitor.model.DcuoGender;
import br.com.rgflorencio.dcuomonitor.model.DcuoSummaryStatus;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoCharacterMySqlDAO extends AbstractMySqlDAO implements DcuoCharacterDAO {

    private static final String SQL_SELECT_FULL =
            "SELECT char_code, char_census_id, char_name, power_code, pve_cr_val, pvp_cr_val, skill_val, rank_code," +
            " level_val, mvmnt_code, origin_code, gender_code, region_code, person_code, active_ind, deleted_ind " +
            "FROM tab_character";
    
    private static final String SQL_SELECT_BY_LEAGUE =
            "SELECT c.char_code, c.char_census_id, c.char_name, p.power_name, c.pve_cr_val, c.pvp_cr_val, c.skill_val," + 
            " c.rank_code, c.level_val, m.mvmnt_name, o.origin_name, c.gender_code, r.region_name, pe.person_name, rk.rank_name " + 
            "FROM tab_character c " +
            "JOIN tab_power p ON c.power_code = p.power_code " + 
            "JOIN tab_movement m ON c.mvmnt_code = m.mvmnt_code " + 
            "JOIN tab_origin o ON c.origin_code = o.origin_code " + 
            "JOIN tab_region r ON c.region_code = r.region_code " + 
            "JOIN tab_person pe ON c.person_code = pe.person_code " +
            "JOIN tab_rank rk ON c.rank_code = rk.rank_code " +
            "WHERE" +
            " c.league_code = ? AND c.deleted_ind = 0 " +
            "ORDER BY char_name";

    private static final String SQL_SELECT_ENTRIES_BY_CHARACTER_ID = 
            "SELECT e.entry_date, ch.pve_cr_val, ch.pvp_cr_val, ch.skill_val FROM tab_entry e LEFT JOIN (" +
            "SELECT entry_code, pve_cr_val, pvp_cr_val, skill_val FROM tab_character_history " +
            "WHERE char_code = ?) ch ON e.entry_code = ch.entry_code WHERE e.entry_date BETWEEN ? AND ? ORDER BY e.entry_date";

    /** TODO DOCUMENT ME! */
    private static final String SQL_SELECT_SUMMARY_STATUS = 
            "SELECT COUNT(1) total, AVG(pve_cr_val) pve_cr_val," +
            " AVG(pvp_cr_val) pvp_cr_val, AVG(skill_val) skill_val " +
            "FROM tab_character " +
            "WHERE active_ind = 1 AND deleted_ind = 0";

    private static final String SQL_UPDATE = 
            "UPDATE tab_character SET char_name = ?, league_code = ?, power_code = ?, pve_cr_val = ?, pvp_cr_val = ?," +
            " skill_val = ?, rank_code = ?, level_val = ?, mvmnt_code = ?, origin_code = ?, gender_code = ?," +
            " region_code = ?, person_code = ?, active_ind = ?, deleted_ind = ? WHERE char_code = ?";

    private static final String SQL_INSERT =
            "INSERT INTO tab_character (char_census_id, char_name, league_code, power_code, pve_cr_val, pvp_cr_val," +
            " skill_val, rank_code, level_val, mvmnt_code, origin_code, gender_code, region_code," +
            " person_code, active_ind, deleted_ind) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_STATUS_ACTIVE = 
            "UPDATE tab_character SET league_code = NULL WHERE league_code = ? AND char_code NOT IN " +
            "(SELECT char_code FROM tab_character_history WHERE entry_code IN (SELECT MAX(entry_code) FROM tab_entry))";

    /**
     * TODO DOCUMENT ME!
     * 
     * @param charId
     * @return 
     * @throws DAOException 
     */
    public DcuoCharacter findById(int charId) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoCharacter character = null;

        try {

            cn = getConnection();
            ps = cn.prepareStatement(SQL_SELECT_FULL + " WHERE char_code = ?");
            ps.setInt(1, charId);
            rs = ps.executeQuery();

            if (rs.next()) {
                character = getNextRow(rs);
            }

            return character;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao pesquisar character.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * 
     * @param charCensusId
     * @return
     * @throws DAOException
     */
    public DcuoCharacter findByCensusId(long charCensusId) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoCharacter character = null;

        try {

            cn = getConnection();
            ps = cn.prepareStatement(SQL_SELECT_FULL + " WHERE char_census_id = ?");
            ps.setLong(1, charCensusId);
            rs = ps.executeQuery();

            if (rs.next()) {
                character = getNextRow(rs);
            }

            return character;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao pesquisar character.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * 
     * @param name
     * @return
     * @throws DAOException
     */
    public DcuoCharacter findByName(String name) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoCharacter character = null;

        try {

            cn = getConnection();
            ps = cn.prepareStatement(SQL_SELECT_FULL + " WHERE char_name = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();

            if (rs.next()) {
                character = getNextRow(rs);
            }

            return character;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao pesquisar character.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @return
     * @throws DAOException
     */
    public List<DcuoCharacter> findAll() throws DAOException {

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        List<DcuoCharacter> characterList = new ArrayList<DcuoCharacter>();

        try {

            cn = getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(SQL_SELECT_FULL + " ORDER BY char_name");

            while (rs.next()) {
                characterList.add(getNextRow(rs));
            }

            return characterList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao pesquisar character.", sqle);
        } finally {
            closeResources(cn, st, rs);
        }
    }

    /* (non-Javadoc)
     * @see br.com.rgflorencio.dcuomonitor.dao.dcuo.DcuoCharacterDAO#findByLeagueId(int)
     */
    public List<DcuoCharacterStatus> findByLeagueId(int leagueId) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DcuoCharacterStatus> characterStatusList = new ArrayList<DcuoCharacterStatus>();
        DcuoCharacterStatus characterStatus;

        try {

            cn = getConnection();
            ps = cn.prepareStatement(SQL_SELECT_BY_LEAGUE);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();

            while (rs.next()) {

                characterStatus = new DcuoCharacterStatus();

                characterStatus.setId(rs.getInt("char_code"));
                characterStatus.setCensusId(rs.getLong("char_census_id"));
                characterStatus.setName(rs.getString("char_name"));
                characterStatus.setPowerName(rs.getString("power_name"));
                characterStatus.setCombatRating(rs.getInt("pve_cr_val"));
                characterStatus.setCombatRatingPvp(rs.getInt("pvp_cr_val"));
                characterStatus.setSkillPoints(rs.getInt("skill_val"));
                characterStatus.setRankId(rs.getInt("rank_code"));
                characterStatus.setLevel(rs.getInt("level_val"));
                characterStatus.setMovementName(rs.getString("mvmnt_name"));
                characterStatus.setOriginName(rs.getString("origin_name"));
                characterStatus.setGender(DcuoGender.getByFlag(rs.getInt("gender_code")));
                characterStatus.setRegionName(rs.getString("region_name"));
                characterStatus.setPersonName(rs.getString("person_name"));
                characterStatus.setRankName(rs.getString("rank_name"));

                characterStatusList.add(characterStatus);
            }

            return characterStatusList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao pesquisar character.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param startDate 
     * @param endDate 
     * @return
     * @throws DAOException 
     */
    public List<DcuoCharacterEntry> findAllEntriesByCharacterId(int characterId, Date startDate, Date endDate) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoCharacterEntry characterEntry;
        List<DcuoCharacterEntry> characterEntryList = new ArrayList<DcuoCharacterEntry>();

        try {

            cn = getConnection();
            ps = cn.prepareStatement(SQL_SELECT_ENTRIES_BY_CHARACTER_ID);
            ps.setInt(1, characterId);
            ps.setTimestamp(2, new Timestamp(startDate.getTime()));
            ps.setTimestamp(3, new Timestamp(endDate.getTime()));
            rs = ps.executeQuery();

            while (rs.next()) {

                characterEntry = new DcuoCharacterEntry();
                characterEntry.setDate(rs.getTimestamp("entry_date"));

                characterEntry.setCombatRating(rs.getInt("pve_cr_val"));
                characterEntry.setCombatRatingPvP(rs.getInt("pvp_cr_val"));
                characterEntry.setSkillPoints(rs.getInt("skill_val"));

                characterEntryList.add(characterEntry);
            }

            return characterEntryList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter entradas do char especificado.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @return
     * @throws DAOException
     */
    public DcuoSummaryStatus findSummaryStatus() throws DAOException {

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        DcuoSummaryStatus summaryStatus = null;

        try {

            cn = getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(SQL_SELECT_SUMMARY_STATUS);
            
            if (rs.next()) {
                summaryStatus = new DcuoSummaryStatus();
                summaryStatus.setTotalMembers(rs.getInt("total"));
                summaryStatus.setCombatRating(rs.getDouble("pve_cr_val"));
                summaryStatus.setCombatRatingPvp(rs.getDouble("pvp_cr_val"));
                summaryStatus.setSkillPoints(rs.getDouble("skill_val"));
            }

            return summaryStatus;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter detalhes da liga.", sqle);
        } finally {
            closeResources(cn, st, rs);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * 
     * @param character
     * @return
     * @throws DAOException
     */
    public DcuoCharacter persist(DcuoCharacter character) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        DcuoCharacter existendCharacter = findByCensusId(character.getCensusId());

        try {

            cn = getConnection();

            if (existendCharacter != null) {

                character.setId(existendCharacter.getId());
                ps = cn.prepareStatement(SQL_UPDATE);

                ps.setString(1, character.getName());
                ps.setInt(2, character.getLeagueId());
                ps.setInt(3, character.getPowerId());
                ps.setInt(4, character.getCombatRating());
                ps.setInt(5, character.getCombatRatingPvP());
                ps.setInt(6, character.getSkillPoints());
                ps.setInt(7, character.getRankId());
                ps.setInt(8, character.getLevel());
                ps.setInt(9, character.getMovementId());
                ps.setInt(10, character.getOriginId());
                ps.setInt(11, character.getGender().getFlag());
                ps.setInt(12, character.getRegionId());
                ps.setInt(13, character.getPersonId());
                ps.setBoolean(14, character.isActive());
                ps.setBoolean(15, character.isDeleted());
                ps.setInt(16, character.getId());

                ps.executeUpdate();

            } else {

                ps = cn.prepareStatement(SQL_INSERT);

                ps.setLong(1, character.getCensusId());
                ps.setString(2, character.getName());
                ps.setInt(3, character.getLeagueId());
                ps.setInt(4, character.getPowerId());
                ps.setInt(5, character.getCombatRating());
                ps.setInt(6, character.getCombatRatingPvP());
                ps.setInt(7, character.getSkillPoints());
                ps.setInt(8, character.getRankId());
                ps.setInt(9, character.getLevel());
                ps.setInt(10, character.getMovementId());
                ps.setInt(11, character.getOriginId());
                ps.setInt(12, character.getGender().getFlag());
                ps.setInt(13, character.getRegionId());
                ps.setInt(14, character.getPersonId());
                ps.setBoolean(15, character.isActive());
                ps.setBoolean(16, character.isDeleted());

                ps.executeUpdate();
                character.setId(findLastInsertId());
            }

            return character;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao persistir character.", sqle);
        } finally {
            closeResources(cn, ps, null);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @throws DAOException
     */
    public void updateStatus() throws DAOException {

        Connection cn = null;
        Statement st = null;

        try {

            cn = getConnection();
            st = cn.createStatement();

            st.executeUpdate(SQL_UPDATE_STATUS_ACTIVE);

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao atualizar status dos membros da liga.", sqle);
        } finally {
            closeResources(cn, st, null);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * 
     * @param rs
     * @return
     * @throws SQLException
     */
    private DcuoCharacter getNextRow(ResultSet rs) throws SQLException {

        DcuoCharacter character = new DcuoCharacter();

        character.setId(rs.getInt("char_code"));
        character.setCensusId(rs.getLong("char_census_id"));
        character.setName(rs.getString("char_name"));
        character.setPowerId(rs.getInt("power_code"));
        character.setCombatRating(rs.getInt("pve_cr_val"));
        character.setCombatRatingPvP(rs.getInt("pvp_cr_val"));
        character.setSkillPoints(rs.getInt("skill_val"));
        character.setRankId(rs.getInt("rank_code"));
        character.setLevel(rs.getInt("level_val"));
        character.setMovementId(rs.getInt("mvmnt_code"));
        character.setOriginId(rs.getInt("origin_code"));
        character.setGender(DcuoGender.getByFlag(rs.getInt("gender_code")));
        character.setRegionId(rs.getInt("region_code"));
        character.setPersonId(rs.getInt("person_code"));
        character.setActive(rs.getBoolean("active_ind"));
        character.setDeleted(rs.getBoolean("deleted_ind"));

        return character;
    }
}
