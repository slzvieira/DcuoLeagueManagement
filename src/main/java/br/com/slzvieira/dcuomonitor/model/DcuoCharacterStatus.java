/*
 * @(#)DcuoCharacterStatus.java 1.00 19/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.model;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 19/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoCharacterStatus {

    private int id;
    private long censusId;
    private String name;
    private String powerName;
    private int combatRating;
    private int combatRatingPvp;
    private int skillPoints;
    private int rankId;
    private int level;
    private String movementName;
    private String originName;
    private DcuoGender gender;
    private String regionName;
    private String personName;
    private String rankName;

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
     * @return the censusId
     */
    public long getCensusId() {
        return censusId;
    }

    /**
     * TODO DOCUMENT ME!
     * @param censusId the censusId to set
     */
    public void setCensusId(long censusId) {
        this.censusId = censusId;
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
     * @return the powerName
     */
    public String getPowerName() {
        return powerName;
    }

    /**
     * TODO DOCUMENT ME!
     * @param powerName the powerName to set
     */
    public void setPowerName(String powerName) {
        this.powerName = powerName;
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
     * @return the movementName
     */
    public String getMovementName() {
        return movementName;
    }

    /**
     * TODO DOCUMENT ME!
     * @param movementName the movementName to set
     */
    public void setMovementName(String movementName) {
        this.movementName = movementName;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the originName
     */
    public String getOriginName() {
        return originName;
    }

    /**
     * TODO DOCUMENT ME!
     * @param originName the originName to set
     */
    public void setOriginName(String originName) {
        this.originName = originName;
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
     * @return the regionName
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * TODO DOCUMENT ME!
     * @param regionName the regionName to set
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the personName
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * TODO DOCUMENT ME!
     * @param personName the personName to set
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the rankName
     */
    public String getRankName() {
        return rankName;
    }

    /**
     * TODO DOCUMENT ME!
     * @param rankName the rankName to set
     */
    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return getName(); // + "(" + getRankName() + ")";
    }
}
