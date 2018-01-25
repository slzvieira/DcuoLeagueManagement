/*
 * @(#)DcuoEntry.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Mapping of the table tab_entry.
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoEntry implements Serializable {

    /** Serialization version. */
    private static final long serialVersionUID = 1L;

    /** entry_code INT PRIMARY KEY AUTO_INCREMENT. */
    private int id;

    /** entry_date DATETIME. */
    private Date dateTime;

    /** league_code INT */
    private int leagueId;

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
     * @return the dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * TODO DOCUMENT ME!
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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
}
