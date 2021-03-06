/*
 * @(#)ImportService.java 1.00 21/10/2014 Copyright 2014 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a c�pia e-ou a reprodu��o deste c�digo.
 */
package br.com.slzvieira.dcuomonitor.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import br.com.slzvieira.dcuomonitor.PropertiesManager;
import br.com.slzvieira.dcuomonitor.dao.DAOException;
import br.com.slzvieira.dcuomonitor.dao.census.CensusDAOFactory;
import br.com.slzvieira.dcuomonitor.dao.census.CensusLeagueDAO;
import br.com.slzvieira.dcuomonitor.dao.census.ruleset.FeatListRuleSet;
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoCharacterDAO;
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoCharacterHistoryDAO;
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoDAOFactory;
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoEntryDAO;
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoMovementDAO;
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoOriginDAO;
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoPersonDAO;
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoPowerDAO;
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoRankDAO;
import br.com.slzvieira.dcuomonitor.dao.dcuo.DcuoRegionDAO;
import br.com.slzvieira.dcuomonitor.model.DcuoCharacter;
import br.com.slzvieira.dcuomonitor.model.DcuoCharacterHistory;
import br.com.slzvieira.dcuomonitor.model.DcuoCharacterInputData;
import br.com.slzvieira.dcuomonitor.model.DcuoEntry;
import br.com.slzvieira.dcuomonitor.model.DcuoGender;
import br.com.slzvieira.dcuomonitor.model.DcuoLeague;
import br.com.slzvieira.dcuomonitor.model.DcuoMovement;
import br.com.slzvieira.dcuomonitor.model.DcuoOrigin;
import br.com.slzvieira.dcuomonitor.model.DcuoPerson;
import br.com.slzvieira.dcuomonitor.model.DcuoPower;
import br.com.slzvieira.dcuomonitor.model.DcuoRank;
import br.com.slzvieira.dcuomonitor.model.DcuoRegion;
import br.com.slzvieira.dcuomonitor.model.Feat;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 21/10/2014 - sandro.vieira - Implementacao.
 */
public class ImportService {

    /** TODO DOCUMENT ME! */
//    private static final String SOURCE_URL = "http://census.soe.com/xml/get/dcuo:v1/character?name=MagnoBrutus&c:join=characters_active_feat^on:character_id^to:character_id^list:1^outer:1(feat^on:feat_id^to:feat_id^outer:1(feat_category^on:feat_category_id^to:feat_category_id^outer:1(feat_category^on:parent_category_id^to:feat_category_id^outer:1)))";
//    private static final String SOURCE_URL = "http://census.soe.com/xml/get/dcuo:v1/feat?c:limit=10000&c:join=feat_category^on:feat_category_id^to:feat_category_id(feat_category^on:parent_category_id^to:feat_category_id^outer:1)";
//    private static final String SOURCE_URL = "http://census.daybreakgames.com/xml/get/dcuo:v1/guild_roster?guild_id=225550976038289409&c:limit=1000&c:join=character^on:character_id^to:character_id(power_type^on:power_type_id^to:power_type_id,origin^on:origin_id^to:origin_id,movement_mode^on:movement_mode_id^to:movement_mode_id,gender^on:gender_id^to:gender_id,personality^on:personality_id^to:personality_id,region^on:region_id^to:region_id)";

    /** Proxy definition. */
    private static final String PROXY_HOST = "192.168.0.19";

    private static final int PROXY_PORT = 3128;

    private static final String PROXY_USER = "sandro";

    private static final String PROXY_PASS = "cpa123";

    /**
     * TODO DOCUMENT ME!
     * @param args
     */
    public static void main(String[] args) {

        try {

//            Calendar baseCalendar = Calendar.getInstance();
//            baseCalendar.set(2015, 4, 18, 9, 0, 0);
//            Date baseDate = baseCalendar.getTime();

            ImportService service = new ImportService();

            for (int i = 124; i < 127; i++) {
                System.out.println(i + service.loadQuantityCharsByCr(i));
            }

////            List<DcuoCharacterInputData> characterList = service.loadGuildListFromFile(new File("C:\\Sandro\\Dropbox\\Documents\\dcuo\\MyProject\\guild_roster_2015_05_18.xml"));
//            List<DcuoCharacterInputData> characterList = service.loadGuildList(System.out);
//            service.saveGuildList(characterList, null, System.out);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @return
     * @throws SAXException 
     */
    public Collection<Feat> importFeatList(String leagueCensusId) throws IOException, SAXException {

        System.setProperty("http.proxySet", "true");
        System.setProperty("java.net.useSystemProxies", "true");
        System.setProperty("http.proxyHost", PROXY_HOST);
        System.setProperty("http.proxyPort", Integer.toString(PROXY_PORT));
        System.setProperty("http.proxyUser", PROXY_USER);
        System.setProperty("http.proxyPassword", PROXY_PASS);

        Authenticator.setDefault(new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(PROXY_USER, PROXY_PASS.toCharArray());
            }
        });

        URL url = new URL(PropertiesManager.getInstance().getGuildRosterUrl(leagueCensusId));

        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.connect();

        List<Feat> featSet = new ArrayList<Feat>();
        Digester digester = new Digester();
        digester.push(featSet);
        digester.addRuleSet(new FeatListRuleSet());
        
        InputStream inputStream = (InputStream) httpConnection.getContent();
        digester.parse(inputStream);
        Collections.sort(featSet);

        inputStream.close();
        httpConnection.disconnect();
        return featSet;
    }

    /**
     * TODO DOCUMENT ME!
     * @param league
     * @throws ServiceException
     */
    public void loadGuildDataCurrent(DcuoLeague league) throws ServiceException {

        try {

            CensusLeagueDAO censusDAO = CensusDAOFactory.getInstance().getCensusLeagueDAO();
            List<DcuoCharacterInputData> characterList = censusDAO.findCharacterListByCensusId(Long.toString(league.getCensusId()));
            saveGuildList(characterList, league, new Date());

        } catch (DAOException daoe) {
            throw new ServiceException(daoe);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * 
     * @param league
     * @param file
     * @param baseDate
     * @throws ServiceException
     */
    public void loadGuildDataFromFile(DcuoLeague league, File file, Date baseDate)
            throws ServiceException {

        try {

            CensusLeagueDAO censusDAO = CensusDAOFactory.getInstance().getCensusLeagueDAO();
            List<DcuoCharacterInputData> characterList = censusDAO.findCharacterListByCensusId(
                Long.toString(league.getCensusId()), file);
            saveGuildList(characterList, league, baseDate);

        } catch (DAOException daoe) {
            throw new ServiceException(daoe);
        }
    }

    public String loadQuantityCharsByCr(Integer crPve) throws IOException, SAXException {

        MessageFormat urlPattern = new MessageFormat("http://census.soe.com/xml/count/dcuo:v1/character?world_id=2&deleted=false&combat_rating={0}");
        URL url = new URL(urlPattern.format(new Object[] {crPve}));

        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.connect();

        InputStream inputStream = (InputStream) httpConnection.getContent();
        return readContent(inputStream);
    }

    /**
     * TODO DOCUMENT ME!
     * 
     * @param entryList
     * @param league
     * @param baseDate
     * 
     * @throws ServiceException
     */
    public void saveGuildList(List<DcuoCharacterInputData> entryList, DcuoLeague league, Date baseDate)
            throws ServiceException {

        DcuoEntryDAO entryDAO = DcuoDAOFactory.getInstance().getDcuoEntryDAO();
        DcuoCharacterDAO characterDAO = DcuoDAOFactory.getInstance().getDcuoCharacterDAO();
        DcuoCharacterHistoryDAO characterHistoryDAO = DcuoDAOFactory.getInstance().getDcuoCharacterHistoryDAO();
        DcuoEntry entry = new DcuoEntry();
        DcuoCharacter character = null;
        DcuoCharacterHistory characterHistory = null;

        int movementId;
        int originId;
        int regionId;
        int personalityId;
        int powerTypeId;
        int rankId;

        try {

            if (baseDate == null) {
                /* Obtem a nova entrada. */
                entry.setDateTime(new Date());
            } else {
                entry.setDateTime(baseDate);
            }
            
            entry.setLeagueId(league.getId());

            entryDAO.startTransaction();
            entry = entryDAO.persist(entry);

            for (DcuoCharacterInputData characterInputData : entryList) {

//                out.println("Persisting char " + characterInputData.getName() + "...");
                
                movementId = findMovementId(characterInputData.getMovementMode());
                originId = findOriginId(characterInputData.getOrigin());
                regionId = findRegion(characterInputData.getRegion());
                personalityId = findPersonality(characterInputData.getPersonality());
                powerTypeId = findPowerType(characterInputData.getPowerType());
                rankId = findRankId(characterInputData.getRank());

                character = new DcuoCharacter();
                character.setCensusId(characterInputData.getCharacterId());

                if (characterInputData.isDeleted() && characterInputData.getName() != null && characterInputData.getName().length() > 29) {
                    character.setName(characterInputData.getName().substring(0, characterInputData.getName().length() - 29));
                } else {
                    character.setName(characterInputData.getName());
                }

                character.setLeagueId(league.getId());
                character.setPowerId(powerTypeId);
                character.setCombatRating(characterInputData.getCombatRating());
                character.setCombatRatingPvP(characterInputData.getPvpCombatRating());
                character.setSkillPoints(characterInputData.getSkillPoints());
                character.setRankId(rankId);
                character.setLevel(characterInputData.getLevel());
                character.setMovementId(movementId);
                character.setOriginId(originId);
                character.setGender(DcuoGender.getByName(characterInputData.getGender()));
                character.setRegionId(regionId);
                character.setPersonId(personalityId);
                character.setDeleted(characterInputData.isDeleted());
                characterDAO.persist(character);

                characterHistory = new DcuoCharacterHistory();
                characterHistory.setCharId(character.getId());
                characterHistory.setEntryId(entry.getId());
                characterHistory.setLeagueId(league.getId());
                characterHistory.setPowerId(powerTypeId);
                characterHistory.setCombatRating(characterInputData.getCombatRating());
                characterHistory.setCombatRatingPvp(characterInputData.getPvpCombatRating());
                characterHistory.setSkillPoints(characterInputData.getSkillPoints());
                characterHistory.setRankId(rankId);
                characterHistory.setMovementId(movementId);
                characterHistoryDAO.persist(characterHistory);
            }

//            out.println("Atualizando status dos membros da liga...");
            characterDAO.updateStatusByLeagueId(league.getId());
//            out.println("Status atualizados. Importa��o conclu�da com sucesso.");
            entryDAO.commitTransaction();

        } catch (DAOException daoe) {
            entryDAO.rollbackTransaction();
            throw new ServiceException("Falha ao registrar personagem. Importacao abortada.", daoe); 
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param movementMode
     * @return
     * @throws DAOException
     */
    private int findMovementId(String movementMode) throws DAOException {

        DcuoMovementDAO dao = DcuoDAOFactory.getInstance().getDcuoMovementDAO();
        DcuoMovement movement = dao.findByName(movementMode);

        if (movement == null) {
            movement = new DcuoMovement();
            movement.setName(movementMode);
            dao.persist(movement);
        }

        return movement.getId();
    }

    /**
     * TODO DOCUMENT ME!
     * @param originName
     * @return
     * @throws DAOException
     */
    private int findOriginId(String originName) throws DAOException {

        DcuoOriginDAO dao = DcuoDAOFactory.getInstance().getDcuoOriginDAO();
        DcuoOrigin origin = dao.findByName(originName);

        if (origin == null) {
            origin = new DcuoOrigin();
            origin.setName(originName);
            dao.persist(origin);
        }

        return origin.getId();
    }

    /**
     * TODO DOCUMENT ME!
     * @param regionName
     * @return
     * @throws DAOException
     */
    private int findRegion(String regionName) throws DAOException {

        DcuoRegionDAO dao = DcuoDAOFactory.getInstance().getDcuoRegionDAO();
        DcuoRegion region = dao.findByName(regionName);

        if (region == null) {
            region = new DcuoRegion();
            region.setName(regionName);
            dao.persist(region);
        }

        return region.getId();
    }

    /**
     * TODO DOCUMENT ME!
     * @param personalityName
     * @return
     * @throws DAOException
     */
    private int findPersonality(String personalityName) throws DAOException {

        DcuoPersonDAO dao = DcuoDAOFactory.getInstance().getDcuoPersonDAO();
        DcuoPerson person = dao.findByName(personalityName);

        if (person == null) {
            person = new DcuoPerson();
            person.setName(personalityName);
            dao.persist(person);
        }

        return person.getId();
    }

    /**
     * TODO DOCUMENT ME!
     * @param powerName
     * @return
     * @throws DAOException
     */
    private int findPowerType(String powerName) throws DAOException {

        DcuoPowerDAO dao = DcuoDAOFactory.getInstance().getDcuoPowerDAO();
        DcuoPower power = dao.findByName(powerName);

        if (power == null) {
            power = new DcuoPower();
            power.setName(powerName);
            dao.persist(power);
        }

        return power.getId();
    }

    /**
     * TODO DOCUMENT ME!
     * @param rankId
     * @return
     * @throws DAOException
     */
    private int findRankId(int rankId) throws DAOException {

        DcuoRankDAO dao = DcuoDAOFactory.getInstance().getDcuoRankDAO();
        DcuoRank rank = dao.findById(rankId);

        if (rank == null) {
            rank = new DcuoRank();
            rank.setId(rankId);
            dao.persist(rank);
        }

        return rankId;
    }

    /**
     * TODO DOCUMENT ME!
     * @return
     * @throws IOException 
     */
    String readContent(InputStream is) throws IOException {
        InputStreamReader reader = new InputStreamReader(is);
        StringBuilder builder = new StringBuilder();

        char[] buffer = new char[4096];
        int readed = 0;

        while ((readed = reader.read(buffer)) > 0) {
            builder.append(buffer, 0, readed);
        }

        reader.close();
        return builder.toString();
    }
}
