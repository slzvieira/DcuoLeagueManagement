/*
 * @(#)DcuoSummaryStatus.java 1.00 21/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.model;

import java.util.Date;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 21/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoSummaryStatus {

    /** TODO DOCUMENT ME! */
    private int entryId;

    /** TODO DOCUMENT ME! */
    private Date entryDate;

    /** TODO DOCUMENT ME! */
    private int totalMembers;

    /** TODO DOCUMENT ME! */
    private double combatRating;

    /** TODO DOCUMENT ME! */
    private double combatRatingPvp;

    /** TODO DOCUMENT ME! */
    private double skillPoints;

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
     * @return the entryDate
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * TODO DOCUMENT ME!
     * @param entryDate the entryDate to set
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the totalMembers
     */
    public int getTotalMembers() {
        return totalMembers;
    }

    /**
     * TODO DOCUMENT ME!
     * @param totalMembers the totalMembers to set
     */
    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the combatRating
     */
    public double getCombatRating() {
        return combatRating;
    }

    /**
     * TODO DOCUMENT ME!
     * @param combatRating the combatRating to set
     */
    public void setCombatRating(double combatRating) {
        this.combatRating = combatRating;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the combatRatingPvp
     */
    public double getCombatRatingPvp() {
        return combatRatingPvp;
    }

    /**
     * TODO DOCUMENT ME!
     * @param combatRatingPvp the combatRatingPvp to set
     */
    public void setCombatRatingPvp(double combatRatingPvp) {
        this.combatRatingPvp = combatRatingPvp;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the skillPoints
     */
    public double getSkillPoints() {
        return skillPoints;
    }

    /**
     * TODO DOCUMENT ME!
     * @param skillPoints the skillPoints to set
     */
    public void setSkillPoints(double skillPoints) {
        this.skillPoints = skillPoints;
    }
}
