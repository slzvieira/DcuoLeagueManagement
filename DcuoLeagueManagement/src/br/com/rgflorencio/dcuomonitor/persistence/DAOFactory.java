/*
 * @(#)DAOFactory(){} 1.00 25/08/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.persistence;

import br.com.rgflorencio.dcuomonitor.PropertiesManager;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 25/08/2015 - sandro.vieira - Implementacao.
 */
public class DAOFactory {

    /** The singleton instance. */
    private static final DAOFactory INSTANCE = new DAOFactory();

    /** TODO DOCUMENT ME! */
    private static final String DB_IMPLEMENTATION = PropertiesManager.getInstance().getProperty("db.impl");

    /**
     * Gets an instance of this factory.
     * @return The singleton instance.
     */
    public static DAOFactory getInstance() {
        return INSTANCE;
    }

    /**
     * The default constructor.
     * Avoid the instantiation of this class.
     */
    private DAOFactory() {
        // do nothing
    }

    public DcuoCharacterDAO getDcuoCharacterDAO() {
        return newInstance(DcuoCharacterDAO.class);
    }

    public DcuoCharacterHistoryDAO getDcuoCharacterHistoryDAO() {
        return newInstance(DcuoCharacterHistoryDAO.class);
    }

    public DcuoEntryDAO getDcuoEntryDAO() {
        return newInstance(DcuoEntryDAO.class);
    }

    public DcuoMovementDAO getDcuoMovementDAO() {
        return newInstance(DcuoMovementDAO.class);
    }

    public DcuoOriginDAO getDcuoOriginDAO() {
        return newInstance(DcuoOriginDAO.class);
    }

    public DcuoPersonDAO getDcuoPersonDAO() {
        return newInstance(DcuoPersonDAO.class);
    }

    public DcuoPowerDAO getDcuoPowerDAO() {
        return newInstance(DcuoPowerDAO.class);
    }

    public DcuoRankDAO getDcuoRankDAO() {
        return newInstance(DcuoRankDAO.class);
    }

    public DcuoRegionDAO getDcuoRegionDAO() {
        return newInstance(DcuoRegionDAO.class);
    }

    public DcuoRoleDAO getDcuoRoleDAO() {
        return newInstance(DcuoRoleDAO.class);
    }

    @SuppressWarnings("unchecked")
    private <T> T newInstance(Class<? super T> clazz) {

        try {
            String interfaceName = clazz.getName();

            StringBuilder builder = new StringBuilder(interfaceName);
            builder.insert(interfaceName.lastIndexOf("."), "." + DB_IMPLEMENTATION.toLowerCase());
            builder.insert(builder.length() - 3, DB_IMPLEMENTATION);

            return (T) Class.forName(builder.toString()).newInstance();

        } catch (Exception e) {
            throw new RuntimeException("Falha ao instancia DAO.", e);
        }
    }
}
