/*
 * @(#)DcuoPower.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.model;

import java.io.Serializable;

/**
 * Mapping of the table tab_power.
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoPower implements Serializable {

    /** Serialization version. */
    private static final long serialVersionUID = 1L;

    /** power_code INT PRIMARY KEY AUTO_INCREMENT. */
    private int id;

    /** power_name VARCHAR(30). */
    private String name;

    /** role_code  INT. */
    private int roleId;

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

    /**
     * TODO DOCUMENT ME!
     * @return the roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * TODO DOCUMENT ME!
     * @param roleId the roleId to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
