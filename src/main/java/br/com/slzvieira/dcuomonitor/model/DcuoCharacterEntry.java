/*
 * @(#)DcuoCharacterEntry.java 1.00 20/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.model;

import java.util.Date;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 20/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoCharacterEntry {

    /** entry_date */
    private Date date;

    /** pve_cr_val */
    private Integer combatRating;

    /** pvp_cr_val */
    private Integer combatRatingPvP;

    /** skill_val */
    private Integer skillPoints;

    /**
     * TODO DOCUMENT ME!
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * TODO DOCUMENT ME!
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the combatRating
     */
    public Integer getCombatRating() {
        return combatRating;
    }

    /**
     * TODO DOCUMENT ME!
     * @param combatRating the combatRating to set
     */
    public void setCombatRating(Integer combatRating) {
        this.combatRating = combatRating;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the combatRatingPvP
     */
    public Integer getCombatRatingPvP() {
        return combatRatingPvP;
    }

    /**
     * TODO DOCUMENT ME!
     * @param combatRatingPvP the combatRatingPvP to set
     */
    public void setCombatRatingPvP(Integer combatRatingPvP) {
        this.combatRatingPvP = combatRatingPvP;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the skillPoints
     */
    public Integer getSkillPoints() {
        return skillPoints;
    }

    /**
     * TODO DOCUMENT ME!
     * @param skillPoints the skillPoints to set
     */
    public void setSkillPoints(Integer skillPoints) {
        this.skillPoints = skillPoints;
    }
}
