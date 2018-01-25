/*
 * @(#)DcuoEntryMySqlDAO.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.dao.dcuo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.slzvieira.dcuomonitor.dao.DAOException;
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoEntryDAO;
import br.com.slzvieira.dcuomonitor.model.DcuoEntry;
import br.com.slzvieira.dcuomonitor.model.DcuoEntryEvent;
import br.com.slzvieira.dcuomonitor.model.DcuoSummaryStatus;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoEntryMySqlDAO extends AbstractMySqlDAO implements DcuoEntryDAO {

    /** TODO DOCUMENT ME! */
    private static final String SQL_SELECT_SUMMARY_STATUS = 
            "SELECT e.entry_code, e.entry_date, COUNT(1) total, AVG(ch.pve_cr_val) pve_cr_val," +
            " AVG(ch.pvp_cr_val) pvp_cr_val, AVG(ch.skill_val) skill_val " +
            "FROM tab_character_history ch JOIN tab_entry e ON ch.entry_code = e.entry_code " +
            "GROUP BY e.entry_code, e.entry_date HAVING e.entry_date BETWEEN ? AND ? ORDER BY e.entry_date";

    private static final String SQL_SELECT_DESCRIPTION_BY_EVENT_INTERVAL =
            "SELECT char_name, 'entrou para a Liga' evento FROM tab_character WHERE char_code NOT IN (" +
            "SELECT char_code FROM tab_character_history WHERE entry_code = ?) AND char_code IN (" +
            "SELECT char_code FROM tab_character_history WHERE entry_code = ?) UNION " +
            "SELECT char_name, 'saiu da Liga' evento FROM tab_character WHERE char_code IN (" +
            "SELECT char_code FROM tab_character_history WHERE entry_code = ?) AND char_code NOT IN (" +
            "SELECT char_code FROM tab_character_history WHERE entry_code = ?)";

    
    /**
     * TODO DOCUMENT ME!
     * 
     * @param id ID da entrada desejada.
     * @return
     * @throws DAOException Falha ao realizar consulta.
     */
    public DcuoEntry findById(int id) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoEntry entry = null;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("SELECT entry_date, league_code FROM tab_entry WHERE entry_code = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                entry = new DcuoEntry();
                entry.setId(id);
                entry.setDateTime(new Date(rs.getTimestamp("entry_date").getTime()));
                entry.setLeagueId(rs.getInt("league_code"));
            }

            return entry;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter nome da entrada.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @return
     * @throws DAOException 
     */
    public List<DcuoEntry> findAll() throws DAOException {

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        DcuoEntry entry = null;
        List<DcuoEntry> entryList = new ArrayList<>();

        try {

            cn = getConnection();
            st = cn.createStatement();
            rs = st.executeQuery("SELECT entry_code, entry_date, league_code FROM tab_entry ORDER BY entry_date");

            while (rs.next()) {
                entry = new DcuoEntry();
                entry.setId(rs.getInt("entry_code"));
                entry.setDateTime(new Date(rs.getTimestamp("entry_date").getTime()));
                entry.setLeagueId(rs.getInt("league_code"));
                entryList.add(entry);
            }

            return entryList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter lista de entradas.", sqle);
        }
    }

    /* (non-Javadoc)
     * @see br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoEntryDAO#findAllSummaryStatus(java.util.Date, java.util.Date)
     */
    public List<DcuoSummaryStatus> findAllSummaryStatus(Date startDate, Date endDate) throws DAOException {
        
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoSummaryStatus summary;
        List<DcuoSummaryStatus> summaryList = new ArrayList<>();

        try {

            cn = getConnection();
            ps = cn.prepareStatement(SQL_SELECT_SUMMARY_STATUS);
            ps.setTimestamp(1, new Timestamp(startDate.getTime()));
            ps.setTimestamp(2, new Timestamp(endDate.getTime()));
            rs = ps.executeQuery();

            while (rs.next()) {

                summary = new DcuoSummaryStatus();
                summary.setEntryId(rs.getInt("entry_code"));
                summary.setEntryDate(rs.getTimestamp("entry_date"));
                summary.setTotalMembers(rs.getInt("total"));
                summary.setCombatRating(rs.getDouble("pve_cr_val"));
                summary.setCombatRatingPvp(rs.getDouble("pvp_cr_val"));
                summary.setSkillPoints(rs.getDouble("skill_val"));

                summaryList.add(summary);
            }

            return summaryList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter status sumarizado.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }

    /* (non-Javadoc)
     * @see br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoEntryDAO#findEventsByLeagueId(int)
     */
    public List<DcuoEntryEvent> findEventsByLeagueId(int leagueId) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DcuoEntryEvent> itemList = new ArrayList<>();
        DcuoEntryEvent item;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("SELECT entry_code, entry_date FROM tab_entry WHERE league_code = ? ORDER BY entry_date DESC");
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();

            if (!rs.next()) {
                return itemList;
            }

            /* Fills the more rescent event. */
            item = new DcuoEntryEvent();
            item.setCurrentEntry(new DcuoEntry());
            item.getCurrentEntry().setId(rs.getInt("entry_code"));
            item.getCurrentEntry().setDateTime(rs.getTimestamp("entry_date"));
            item.getCurrentEntry().setLeagueId(leagueId);

            while (rs.next()) {

                item.setPreviousEntry(new DcuoEntry());
                item.getPreviousEntry().setId(rs.getInt("entry_code"));
                item.getPreviousEntry().setDateTime(rs.getTimestamp("entry_date"));
                item.getPreviousEntry().setLeagueId(leagueId);
                itemList.add(item);

                item = new DcuoEntryEvent();
                item.setCurrentEntry(new DcuoEntry());
                item.getCurrentEntry().setId(rs.getInt("entry_code"));
                item.getCurrentEntry().setDateTime(rs.getTimestamp("entry_date"));
                item.getCurrentEntry().setLeagueId(leagueId);
            }

            item.setPreviousEntry(new DcuoEntry());
            item.getPreviousEntry().setId(0);
            item.getPreviousEntry().setDateTime(null);
            item.getPreviousEntry().setLeagueId(leagueId);
            itemList.add(item);
            return itemList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter lista de eventos.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param entry
     * @return
     * @throws DAOException 
     */
    public DcuoEntry persist(DcuoEntry entry) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        int newId = 0;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("INSERT INTO tab_entry (entry_date, league_code) VALUES (?, ?)");
            ps.setTimestamp(1, new Timestamp(entry.getDateTime().getTime()));
            ps.setInt(2, entry.getLeagueId());
            ps.executeUpdate();

            newId = findLastInsertId();
            entry.setId(newId);

            return entry;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao persistir entrada.", sqle);
        } finally {
            closeResources(cn, ps, null);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * 
     * @param currentEventId
     * @param previousEventId
     * 
     * @return
     * @throws DAOException
     */
    public List<String> findDescriptionByEventInterval(int currentEventId, int previousEventId) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> detailList = new ArrayList<>();

        try {

            cn = getConnection();
            ps = cn.prepareStatement(SQL_SELECT_DESCRIPTION_BY_EVENT_INTERVAL);
            ps.setInt(1, previousEventId);
            ps.setInt(2, currentEventId);
            ps.setInt(3, previousEventId);
            ps.setInt(4, currentEventId);
            rs = ps.executeQuery();

            while (rs.next()) {
                detailList.add(rs.getString("char_name") + " " + rs.getString("evento"));
            }

            return detailList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter detalhes do evento.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }
}
