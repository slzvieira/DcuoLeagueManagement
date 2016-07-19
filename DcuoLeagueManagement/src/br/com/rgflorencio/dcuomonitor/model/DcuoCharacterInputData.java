/*
 * @(#)DcuoCharacterInputData.java 1.00 21/10/2014 Copyright 2014 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.model;

import java.io.Serializable;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 21/10/2014 - sandro.vieira - Implementacao.
 */
public class DcuoCharacterInputData implements Serializable, Comparable<DcuoCharacterInputData> {

    /** Serialization version. */
    private static final long serialVersionUID = 1L;

    /** TODO DOCUMENT ME! */
    private int rank;

    /** TODO DOCUMENT ME! */
    private long characterId;

    /** TODO DOCUMENT ME! */
    private String name;

    /** TODO DOCUMENT ME! */
    private int level;

    /** TODO DOCUMENT ME! */
    private int combatRating;

    /** TODO DOCUMENT ME! */
    private int pvpCombatRating;

    /** TODO DOCUMENT ME! */
    private int skillPoints;

    /** TODO DOCUMENT ME! */
    private String movementMode;

    /** TODO DOCUMENT ME! */
    private String origin;

    /** TODO DOCUMENT ME! */
    private String gender;

    /** TODO DOCUMENT ME! */
    private String region;

    /** TODO DOCUMENT ME! */
    private String personality;

    /** TODO DOCUMENT ME! */
    private String powerType;

    /** TODO DOCUMENT ME! */
    private boolean deleted;

    /**
     * TODO DOCUMENT ME!
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * TODO DOCUMENT ME!
     * @param rank the rank to set
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the characterId
     */
    public long getCharacterId() {
        return characterId;
    }

    /**
     * TODO DOCUMENT ME!
     * @param characterId the characterId to set
     */
    public void setCharacterId(long characterId) {
        this.characterId = characterId;
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
     * @return the pvpCombatRating
     */
    public int getPvpCombatRating() {
        return pvpCombatRating;
    }

    /**
     * TODO DOCUMENT ME!
     * @param pvpCombatRating the pvpCombatRating to set
     */
    public void setPvpCombatRating(int pvpCombatRating) {
        this.pvpCombatRating = pvpCombatRating;
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
     * @return the movementMode
     */
    public String getMovementMode() {
        return movementMode;
    }

    /**
     * TODO DOCUMENT ME!
     * @param movementMode the movementMode to set
     */
    public void setMovementMode(String movementMode) {
        this.movementMode = movementMode;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * TODO DOCUMENT ME!
     * @param origin the origin to set
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * TODO DOCUMENT ME!
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * TODO DOCUMENT ME!
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the personality
     */
    public String getPersonality() {
        return personality;
    }

    /**
     * TODO DOCUMENT ME!
     * @param personality the personality to set
     */
    public void setPersonality(String personality) {
        this.personality = personality;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the powerType
     */
    public String getPowerType() {
        return powerType;
    }

    /**
     * TODO DOCUMENT ME!
     * @param powerType the powerType to set
     */
    public void setPowerType(String powerType) {
        this.powerType = powerType;
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return String.format("%s (%d/%d)", name, combatRating, skillPoints);
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(DcuoCharacterInputData other) {
        return this.getName().compareTo(other.getName());
    }
}
