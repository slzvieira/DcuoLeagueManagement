/*
 * @(#)DcuoMovementDerbyDAO.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.persistence.derby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.rgflorencio.dcuomonitor.model.DcuoMovement;
import br.com.rgflorencio.dcuomonitor.persistence.DAOException;
import br.com.rgflorencio.dcuomonitor.persistence.DcuoMovementDAO;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoMovementDerbyDAO extends AbstractDerbyDAO implements DcuoMovementDAO {

    /**
     * TODO DOCUMENT ME!
     * 
     * @param id ID do modo de movimento desejado.
     * @return
     * @throws DAOException Falha ao realizar consulta.
     */
    public DcuoMovement findById(int id) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoMovement movement = null;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("SELECT mvmnt_name FROM tab_movement WHERE mvmnt_code = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                movement = new DcuoMovement();
                movement.setId(id);
                movement.setName(rs.getString("mvmnt_name"));
            }

            return movement;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter nome do modo de movimento.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param name
     * @return
     * @throws DAOException 
     */
    public DcuoMovement findByName(String name) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoMovement movement = null;

        try {

            cn = getConnection();

            if (name == null) {
                ps = cn.prepareStatement("SELECT mvmnt_code FROM tab_movement WHERE mvmnt_name IS NULL");
            } else {
                ps = cn.prepareStatement("SELECT mvmnt_code FROM tab_movement WHERE mvmnt_name = ?");
                ps.setString(1, name);
            }

            rs = ps.executeQuery();

            if (rs.next()) {
                movement = new DcuoMovement();
                movement.setId(rs.getInt("mvmnt_code"));
                movement.setName(name);
            }

            return movement;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao pesquisar item.", sqle);
        } finally {
            closeResources(cn, ps, rs);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @return
     * @throws DAOException 
     */
    public List<DcuoMovement> findAll() throws DAOException {

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        DcuoMovement movement = null;
        List<DcuoMovement> movementList = new ArrayList<DcuoMovement>();

        try {

            cn = getConnection();
            st = cn.createStatement();
            rs = st.executeQuery("SELECT mvmnt_code, mvmnt_name FROM tab_movement ORDER BY mvmnt_name");

            while (rs.next()) {
                movement = new DcuoMovement();
                movement.setId(rs.getInt("mvmnt_code"));
                movement.setName(rs.getString("mvmnt_name"));
                movementList.add(movement);
            }

            return movementList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter lista de modos de movimento.", sqle);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param movement
     * @return
     * @throws DAOException 
     */
    public DcuoMovement persist(DcuoMovement movement) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        int newId = 0;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("INSERT INTO tab_movement (mvmnt_name) VALUES (?)");
            ps.setString(1, movement.getName());
            ps.executeUpdate();

            newId = findLastInsertId();
            movement.setId(newId);

            return movement;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao persistir modo de movimento.", sqle);
        } finally {
            closeResources(cn, ps, null);
        }
    }
}
