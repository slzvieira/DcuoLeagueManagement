/*
 * @(#)DcuoPersonDerbyDAO.java 1.00 18/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.dao.dcuo.derby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.slzvieira.dcuomonitor.dao.DAOException;
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoPersonDAO;
import br.com.slzvieira.dcuomonitor.model.DcuoPerson;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 18/05/2015 - sandro.vieira - Implementacao.
 */
public class DcuoPersonDerbyDAO extends AbstractDerbyDAO implements DcuoPersonDAO {

    /**
     * TODO DOCUMENT ME!
     * 
     * @param id ID da personalidade.
     * @return
     * @throws DAOException Falha ao realizar consulta.
     */
    public DcuoPerson findById(int id) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoPerson person = null;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("SELECT person_name FROM tab_person WHERE person_code = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                person = new DcuoPerson();
                person.setId(id);
                person.setName(rs.getString("person_name"));
            }

            return person;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter nome da personalidade.", sqle);
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
    public DcuoPerson findByName(String name) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DcuoPerson origin = null;

        try {

            cn = getConnection();

            if (name == null) {
                ps = cn.prepareStatement("SELECT person_code FROM tab_person WHERE person_name IS NULL");
            } else {
                ps = cn.prepareStatement("SELECT person_code FROM tab_person WHERE person_name = ?");
                ps.setString(1, name);
            }

            rs = ps.executeQuery();

            if (rs.next()) {
                origin = new DcuoPerson();
                origin.setId(rs.getInt("person_code"));
                origin.setName(name);
            }

            return origin;

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
    public List<DcuoPerson> findAll() throws DAOException {

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        DcuoPerson person = null;
        List<DcuoPerson> personList = new ArrayList<DcuoPerson>();

        try {

            cn = getConnection();
            st = cn.createStatement();
            rs = st.executeQuery("SELECT person_code, person_name FROM tab_person ORDER BY person_name");

            while (rs.next()) {
                person = new DcuoPerson();
                person.setId(rs.getInt("person_code"));
                person.setName(rs.getString("person_name"));
                personList.add(person);
            }

            return personList;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao obter lista de personalidades.", sqle);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param person
     * @return
     * @throws DAOException 
     */
    public DcuoPerson persist(DcuoPerson person) throws DAOException {

        Connection cn = null;
        PreparedStatement ps = null;
        int newId = 0;

        try {

            cn = getConnection();
            ps = cn.prepareStatement("INSERT INTO tab_person (person_name) VALUES (?)");
            ps.setString(1, person.getName());
            ps.executeUpdate();

            newId = findLastInsertId();
            person.setId(newId);

            return person;

        } catch (SQLException sqle) {
            throw new DAOException("Falha ao persistir personalidade.", sqle);
        } finally {
            closeResources(cn, ps, null);
        }
    }
}
