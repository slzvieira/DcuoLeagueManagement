/*
 * @(#)CensusDAOFactory(){} 1.00 25/08/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.dao.census;


/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 25/08/2015 - sandro.vieira - Implementacao.
 */
public class CensusDAOFactory {

    /** The singleton instance. */
    private static final CensusDAOFactory INSTANCE = new CensusDAOFactory();

    /**
     * Gets an instance of this factory.
     * @return The singleton instance.
     */
    public static CensusDAOFactory getInstance() {
        return INSTANCE;
    }

    /**
     * The default constructor.
     * Avoid the instantiation of this class.
     */
    private CensusDAOFactory() {
        // do nothing
    }

    public CensusLeagueDAO getCensusLeagueDAO() {
        return newInstance(CensusLeagueDAO.class);
    }

    @SuppressWarnings({ "unchecked" })
    private <T> T newInstance(Class<? super T> clazz) {
        try {
            return (T) Class.forName(clazz.getName()).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Falha ao instanciar DAO.", e);
        }
    }
}
