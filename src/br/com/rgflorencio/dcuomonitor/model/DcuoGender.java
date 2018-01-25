/*
 * @(#)DcuoGender.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.model;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public enum DcuoGender {

    MALE(0, "male"),
    FEMALE(1, "female");

    /** TODO DOCUMENT ME! */
    private int flag;

    /** TODO DOCUMENT ME! */
    private String name;

    /**
     * TODO DOCUMENT ME!
     * @param flag
     * @return
     */
    public static DcuoGender getByFlag(int flag) {
        return flag == MALE.getFlag() ? MALE : FEMALE;
    }

    /**
     * TODO DOCUMENT ME!
     * @param name
     * @return
     */
    public static DcuoGender getByName(String name) {
        return MALE.getName().equals(name) ? MALE : FEMALE;
    }

    /**
     * TODO DOCUMENT ME!
     * @param flag
     * @param name
     */
    private DcuoGender(int flag, String name) {
        this.flag = flag;
        this.name = name;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * TODO DOCUMENT ME!
     * @return
     */
    public boolean isMale() {
        return this.equals(MALE);
    }

    /**
     * TODO DOCUMENT ME!
     * @return the name
     */
    public String getName() {
        return name;
    }
}
