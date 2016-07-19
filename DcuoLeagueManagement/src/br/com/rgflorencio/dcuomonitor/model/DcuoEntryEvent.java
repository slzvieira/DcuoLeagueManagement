/*
 * @(#)DcuoEntryEvent.java 1.00 23/06/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Mapping of the table tab_entry.
 * @author sandro.vieira
 * @version 1.0, 23/06/2015 - sandro.vieira - Implementacao.
 */
public class DcuoEntryEvent implements Serializable {

    /** Serialization version. */
    private static final long serialVersionUID = 1L;

    /** TODO DOCUMENT ME! */
    private static final SimpleDateFormat DATE_MASK = new SimpleDateFormat("dd/MM/yyyy - EEEE");

    /** TODO DOCUMENT ME! */
    private DcuoEntry currentEntry;

    /** TODO DOCUMENT ME! */
    private DcuoEntry previousEntry;

    /**
     * TODO DOCUMENT ME!
     * @return the currentEntry
     */
    public DcuoEntry getCurrentEntry() {
        return currentEntry;
    }

    /**
     * TODO DOCUMENT ME!
     * @param currentEntry the currentEntry to set
     */
    public void setCurrentEntry(DcuoEntry currentEntry) {
        this.currentEntry = currentEntry;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the previousEntry
     */
    public DcuoEntry getPreviousEntry() {
        return previousEntry;
    }

    /**
     * TODO DOCUMENT ME!
     * @param previousEntry the previousEntry to set
     */
    public void setPreviousEntry(DcuoEntry previousEntry) {
        this.previousEntry = previousEntry;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        if (getCurrentEntry() == null || getCurrentEntry().getDateTime() == null) {
            return "";
        }
        return DATE_MASK.format(getCurrentEntry().getDateTime());
    }
}
