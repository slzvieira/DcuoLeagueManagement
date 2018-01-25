/*
 * @(#)DcuoWorld.java 1.00 30/06/2016 Copyright 2016 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.model;

/**
 * TODO DOCUMENT ME!
 * 
 * @author sandro.vieira
 * @version 1.0, 30/06/2016 - sandro.vieira - Implementacao.
 */
public enum DcuoWorld {

    US(1, 2),
    EU(2, 4),
    XBOX_US(3, 5001),
    XBOX_EU(4, 5002);

    /** Id interno */
    private int id;

    /** Codigo Census */
    private int censusId;

    /**
     * Obtem a moralidade a partir de seu id interno.
     * 
     * @param id Id interno
     * @return
     */
    public static DcuoWorld getById(int id) {
        for (DcuoWorld world : values()) {
            if (world.id == id) {
                return world;
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
    public static DcuoWorld getByCensusId(int censusId) {
        for (DcuoWorld world : values()) {
            if (world.censusId == censusId) {
                return world;
            }
        }
        return null;
    }

    /**
     * Inicializa os items desta enum com os parametros especificados
     * @param id Id interno
     * @param censusId Codigo Census
     */
    private DcuoWorld(int id, int censusId) {
        this.id = id;
        this.censusId = censusId;
    }

    /**
     * Obtem o id interno
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Obtem o codigo census
     * @return the censusId
     */
    public int getCensusId() {
        return censusId;
    }
}
