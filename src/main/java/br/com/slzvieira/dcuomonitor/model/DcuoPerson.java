/*
 * @(#)DcuoPerson.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.model;

import java.io.Serializable;

/**
 * Mapping of the table tab_person.
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoPerson implements Serializable {

    /** Serialization version. */
    private static final long serialVersionUID = 1L;

    /** person_code INT PRIMARY KEY AUTO_INCREMENT. */
    private int id;

    /** person_name VARCHAR(60). */
    private String name;

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
}
