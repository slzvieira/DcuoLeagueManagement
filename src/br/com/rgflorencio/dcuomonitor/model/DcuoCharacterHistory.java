/*
 * @(#)DcuoCharacterHistory.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.model;

import java.io.Serializable;

/**
 * Mapping of the table tab_character_history.
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoCharacterHistory implements Serializable {

    /** Serialization version. */
    private static final long serialVersionUID = 1L;

    /** char_hist_code INT PRIMARY KEY AUTO_INCREMENT. */
    private int id;

    /** char_code      INT. */
    private int charId;

    /** entry_code     INT. */
    private int entryId;

    /** league_code    INT. */
    private int leagueId;

    /** power_code     INT. */
    private int powerId;

    /** pve_cr_val     INT. */
    private int combatRating;

    /** pvp_cr_val     INT. */
    private int combatRatingPvp;

    /** skill_val      INT. */
    private int skillPoints;

    /** rank_code      INT. */
    private int rankId;

    /** mvmnt_code     INT. */
    private int movementId;

    /**
     * TODO DOCUMENT ME!
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * TODO DOCUMENT ME!
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the charId
     */
    public int getCharId() {
        return charId;
    }

    /**
     * TODO DOCUMENT ME!
     * @param charId the charId to set
     */
    public void setCharId(int charId) {
        this.charId = charId;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the entryId
     */
    public int getEntryId() {
        return entryId;
    }

    /**
     * TODO DOCUMENT ME!
     * @param entryId the entryId to set
     */
    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the leagueId
     */
    public int getLeagueId() {
        return leagueId;
    }

    /**
     * TODO DOCUMENT ME!
     * @param leagueId the leagueId to set
     */
    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the powerId
     */
    public int getPowerId() {
        return powerId;
    }

    /**
     * TODO DOCUMENT ME!
     * @param powerId the powerId to set
     */
    public void setPowerId(int powerId) {
        this.powerId = powerId;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the combatRating
     */
    public int getCombatRating() {
        return combatRating;
    }

    /**
     * TODO DOCUMENT ME!
     * @param combatRating the combatRating to set
     */
    public void setCombatRating(int combatRating) {
        this.combatRating = combatRating;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the combatRatingPvp
     */
    public int getCombatRatingPvp() {
        return combatRatingPvp;
    }

    /**
     * TODO DOCUMENT ME!
     * @param combatRatingPvp the combatRatingPvp to set
     */
    public void setCombatRatingPvp(int combatRatingPvp) {
        this.combatRatingPvp = combatRatingPvp;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the skillPoints
     */
    public int getSkillPoints() {
        return skillPoints;
    }

    /**
     * TODO DOCUMENT ME!
     * @param skillPoints the skillPoints to set
     */
    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the rankId
     */
    public int getRankId() {
        return rankId;
    }

    /**
     * TODO DOCUMENT ME!
     * @param rankId the rankId to set
     */
    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the movementId
     */
    public int getMovementId() {
        return movementId;
    }

    /**
     * TODO DOCUMENT ME!
     * @param movementId the movementId to set
     */
    public void setMovementId(int movementId) {
        this.movementId = movementId;
    }
}
