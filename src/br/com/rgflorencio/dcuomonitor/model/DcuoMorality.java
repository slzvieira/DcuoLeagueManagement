/*
 * @(#)DcuoMorality.java 1.00 30/06/2016 Copyright 2016 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.model;

/**
 * Moralidade de um char ou liga.
 * @author sandro.vieira
 * @version 1.0, 30/06/2016 - sandro.vieira - Implementacao.
 */
public enum DcuoMorality {

    HERO(1, 26050, "Herói"),
    VILLAIN(2, 26051, "Vilão");

    /** Id interno */
    private int id;

    /** Codigo Census */
    private int censusId;

    /** Descricao (nome) da moralidade. */
    private String description;
    
    /**
     * Obtem a moralidade a partir de seu id interno.
     * 
     * @param id Id interno
     * @return
     */
    public static DcuoMorality getById(int id) {
        for (DcuoMorality morality : values()) {
            if (morality.id == id) {
                return morality;
            }
        }
        return null;
    }

    /**
     * Obtem a moralidade a partir de seu codigo no Census.
     * 
     * @param censusId
     * @return
     */
    public static DcuoMorality getByCensusId(int censusId) {
        for (DcuoMorality morality : values()) {
            if (morality.censusId == censusId) {
                return morality;
            }
        }
        return null;
    }

    /**
     * Inicializa os items desta enum com os parametros especificados
     * 
     * @param id Id interno
     * @param censusId Codigo Census
     * @param description Descricao (nome) da moralidade.
     */
    private DcuoMorality(int id, int censusId, String description) {
        this.id = id;
        this.censusId = censusId;
        this.description = description;
    }

    /**
     * Obtem o Id interno da moralidade
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Obtem o codigo census da moralidade
     * @return the censusId
     */
    public int getCensusId() {
        return censusId;
    }

    /**
     * Obtem a Descricao (nome) da moralidade.
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
