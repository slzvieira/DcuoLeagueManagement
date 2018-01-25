/*
 * @(#)DcuoLeague.java 1.00 30/06/2016 Copyright 2016 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.model;

import java.io.Serializable;

/**
 * Mapeamento da tabela tab_league.
 * 
 * @author sandro.vieira
 * @version 1.0, 30/06/2016 - sandro.vieira - Implementacao.
 */
public class DcuoLeague implements Serializable {

    /** Constante de serializacao */
    private static final long serialVersionUID = 1L;

    /** Codigo interno da liga. league_code INT PRIMARY KEY AUTO_INCREMENT */
    private int id;

    /** Codigo da liga no Census. league_census_id BIGINT */
    private long censusId;

    /** Nome da liga. league_name VARCHAR(30) */
    private String name;
    
    /** Mundo da liga. world_code INT NOT NULL */
    private DcuoWorld world;
    
    /** Moralidade da liga. alignment_code INT NOT NULL */
    private DcuoMorality morality;

    /**
     * Retorna o Codigo interno da liga.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Assinala o Codigo interno da liga.
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o Codigo da liga no Census.
     * @return the censusId
     */
    public long getCensusId() {
        return censusId;
    }

    /**
     * Assinala o Codigo da liga no Census.
     * @param censusId the censusId to set
     */
    public void setCensusId(long censusId) {
        this.censusId = censusId;
    }

    /**
     * Retorna o Nome da liga.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Assinala o Nome da liga.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the worldCensusId
     */
    public int getWorldCensusId() {
        return world == null ? 0 : world.getCensusId();
    }

    /**
     * TODO DOCUMENT ME!
     * @param worldCensusId the worldCensusId to set
     */
    public void setWorldCensusId(int worldCensusId) {
        world = DcuoWorld.getByCensusId(worldCensusId);
    }

    /**
     * Retorna o Mundo da liga.
     * @return the world
     */
    public DcuoWorld getWorld() {
        return world;
    }

    /**
     * Assinala o Mundo da liga.
     * @param world the world to set
     */
    public void setWorld(DcuoWorld world) {
        this.world = world;
    }

    /**
     * TODO DOCUMENT ME!
     * @return the moralityCensusId
     */
    public int getMoralityCensusId() {
        return morality == null ? 0 : morality.getCensusId();
    }

    /**
     * TODO DOCUMENT ME!
     * @param moralityCensusId the moralityCensusId to set
     */
    public void setMoralityCensusId(int moralityCensusId) {
        morality = DcuoMorality.getByCensusId(moralityCensusId);
    }

    /**
     * Retorna a Moralidade da liga.
     * @return the morality
     */
    public DcuoMorality getMorality() {
        return morality;
    }

    /**
     * Assinala a Moralidade da liga.
     * @param morality the morality to set
     */
    public void setMorality(DcuoMorality morality) {
        this.morality = morality;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return getName();
    }
}
