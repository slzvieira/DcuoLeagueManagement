/*
 * @(#)DcuoCharacter.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.model;

import java.io.Serializable;

/**
 * Mapping of the table tab_character.
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoCharacter implements Serializable {

    /** Serialization version. */
    private static final long serialVersionUID = 1L;

    /** char_code    INT PRIMARY KEY AUTO_INCREMENT. */
    private int id;

    /** char_dcuo_id BIGINT. */
    private long dcuoId;

    /** char_name    VARCHAR(30). */
    private String name;

    /** power_code   INT. */
    private int powerId;

    /** pve_cr_val   INT. */
    private int combatRating;

    /** pvp_cr_val   INT. */
    private int combatRatingPvP;

    /** skill_val    INT. */
    private int skillPoints;

    /** rank_code    INT. */
    private int rankId;

    /** level_val    INT. */
    private int level;

    /** mvmnt_code   INT. */
    private int movementId;

    /** origin_code  INT. */
    private int originId;

    /** gender_code  INT. */
    private DcuoGender gender;

    /** region_code  INT. */
    private int regionId;

    /** person_code  INT. */
    private int personId;

    /** TODO DOCUMENT ME! */
    private boolean active;

    /** TODO DOCUMENT ME! */
    private boolean deleted;

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
     * @return the dcuoId
     */
    public long getDcuoId() {
        return dcuoId;
    }

    /**
     * TODO DOCUMENT ME!
     * @param dcuoId the dcuoId to set
     */
    public void setDcuoId(long dcuoId) {
        this.dcuoId = dcuoId;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * TODO DOCUMENT ME!
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the combatRatingPvP
     */
    public int getCombatRatingPvP() {
        return combatRatingPvP;
    }

    /**
     * TODO DOCUMENT ME!
     * @param combatRatingPvP the combatRatingPvP to set
     */
    public void setCombatRatingPvP(int combatRatingPvP) {
        this.combatRatingPvP = combatRatingPvP;
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
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * TODO DOCUMENT ME!
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
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

    /**
     * TODO DOCUMENT ME!
     * @return the originId
     */
    public int getOriginId() {
        return originId;
    }

    /**
     * TODO DOCUMENT ME!
     * @param originId the originId to set
     */
    public void setOriginId(int originId) {
        this.originId = originId;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the gender
     */
    public DcuoGender getGender() {
        return gender;
    }

    /**
     * TODO DOCUMENT ME!
     * @param gender the gender to set
     */
    public void setGender(DcuoGender gender) {
        this.gender = gender;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the regionId
     */
    public int getRegionId() {
        return regionId;
    }

    /**
     * TODO DOCUMENT ME!
     * @param regionId the regionId to set
     */
    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the personId
     */
    public int getPersonId() {
        return personId;
    }

    /**
     * TODO DOCUMENT ME!
     * @param personId the personId to set
     */
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * TODO DOCUMENT ME!
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * TODO DOCUMENT ME!
     * @param deleted the deleted to set
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
