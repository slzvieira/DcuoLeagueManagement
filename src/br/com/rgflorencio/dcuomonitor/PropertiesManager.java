/*
 * @(#)PropertiesManager.java 1.00 01/06/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 01/06/2015 - sandro.vieira - Implementacao.
 */
public class PropertiesManager extends Properties {

    /** The serialization constant. */
    private static final long serialVersionUID = 1L;

    /** The singleton instance. */
    private static final PropertiesManager INSTANCE = new PropertiesManager();

    /**
     * TODO DOCUMENT ME!
     * @return
     */
    public static PropertiesManager getInstance() {
        return INSTANCE;
    }

    /**
     * Avoid external instantiation.
     */
    private PropertiesManager() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/resources/LeagueManager.properties");
            load(is);
        } catch (IOException ioe) {
            throw new RuntimeException("Arquivo de properties invalido ou nao encontrado.", ioe);
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @return
     */
    public String getGuildUrl(String guildId) {
        return MessageFormat.format(getProperty("census.guild.query"), getProperty("census.domain"), guildId);
    }

    /**
     * TODO DOCUMENT ME!
     * @return
     */
    public String getGuildRosterUrl(String guildId) {
        return MessageFormat.format(getProperty("census.guild.roster.query"), getProperty("census.domain"), guildId);
    }
}
