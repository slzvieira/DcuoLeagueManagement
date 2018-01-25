/*
 * @(#)CensusLeagueDAO.java 1.00 13/07/2016 Copyright 2016 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.dao.census;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.RuleSet;
import org.xml.sax.SAXException;

import br.com.slzvieira.dcuomonitor.PropertiesManager;
import br.com.slzvieira.dcuomonitor.dao.DAOException;
import br.com.slzvieira.dcuomonitor.dao.census.ruleset.DcuoCharacterEntryRuleSet;
import br.com.slzvieira.dcuomonitor.dao.census.ruleset.DcuoLeagueRuleSet;
import br.com.slzvieira.dcuomonitor.model.DcuoCharacterInputData;
import br.com.slzvieira.dcuomonitor.model.DcuoLeague;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 13/07/2016 - sandro.vieira - Implementacao.
 */
public class CensusLeagueDAO {

    /**
     * Busca uma liga a partir de seu census id.
     * @param censusId
     * @return
     * @throws DAOException
     */
    public DcuoLeague findByCensusId(String censusId) throws DAOException {

        String urlPath = PropertiesManager.getInstance().getGuildUrl(censusId);
        List<DcuoLeague> leagueList = new ArrayList<>();

        fillList(urlPath, leagueList, new DcuoLeagueRuleSet());
        return leagueList.isEmpty() ? null : leagueList.get(0);
    }

    /**
     * TODO DOCUMENT ME!
     * @param censusId
     * @return
     * @throws DAOException
     */
    public List<DcuoCharacterInputData> findCharacterListByCensusId(String censusId) throws DAOException {

        String urlPath = PropertiesManager.getInstance().getGuildRosterUrl(censusId);
        List<DcuoCharacterInputData> characterList = new ArrayList<>();

        fillList(urlPath, characterList, new DcuoCharacterEntryRuleSet());
        return characterList;
    }

    /**
     * TODO DOCUMENT ME!
     * @param censusId
     * @return
     * @throws DAOException
     */
    public List<DcuoCharacterInputData> findCharacterListByCensusId(String censusId, File file) throws DAOException {

        String urlPath = PropertiesManager.getInstance().getGuildRosterUrl(censusId);
        List<DcuoCharacterInputData> characterList = new ArrayList<>();

        fillListFromFile(urlPath, characterList, new DcuoCharacterEntryRuleSet(), file);
        return characterList;
    }

    /**
     * TODO DOCUMENT ME!
     * @param censusId
     * @return
     * @throws DAOException
     */
    private <T> void fillList(String urlPath, List<T> emptyList, RuleSet ruleSet) throws DAOException {

        HttpURLConnection httpConnection = null;
        InputStream inputStream = null;

        try {

//          System.setProperty("http.proxySet", "true");
//          System.setProperty("java.net.useSystemProxies", "true");
//          System.setProperty("http.proxyHost", PROXY_HOST);
//          System.setProperty("http.proxyPort", Integer.toString(PROXY_PORT));
//          System.setProperty("http.proxyUser", PROXY_USER);
//          System.setProperty("http.proxyPassword", PROXY_PASS);
//
//          Authenticator.setDefault(new Authenticator() {
//              public PasswordAuthentication getPasswordAuthentication() {
//                  return new PasswordAuthentication(PROXY_USER, PROXY_PASS.toCharArray());
//              }
//          });

            URL url = new URL(urlPath);
            httpConnection = (HttpURLConnection) url.openConnection();

            Digester digester = new Digester();
            digester.push(emptyList);
            digester.addRuleSet(ruleSet);

            inputStream = (InputStream) httpConnection.getContent();
            digester.parse(inputStream);

        } catch (IOException ioe) {
            throw new DAOException("Falha de comunicacao com o Census.", ioe);
        } catch (SAXException saxe) {
            throw new DAOException("Dados de retorno inconsistentes.", saxe);
        } finally {
            closeResources(httpConnection, inputStream);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param censusId
     * @return
     * @throws DAOException
     */
    private <T> void fillListFromFile(String urlPath, List<T> emptyList, RuleSet ruleSet, File file) throws DAOException {

        try {

            Digester digester = new Digester();
            digester.push(emptyList);
            digester.addRuleSet(ruleSet);
            digester.parse(file);

        } catch (IOException ioe) {
            throw new DAOException("Falha de comunicacao com o Census.", ioe);
        } catch (SAXException saxe) {
            throw new DAOException("Dados de retorno inconsistentes.", saxe);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param cn
     * @param is
     */
    private void closeResources(HttpURLConnection cn, InputStream is) {

        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {}
        }

        if (cn != null) {
            cn.disconnect();
        }
    }
}
