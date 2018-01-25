/*
 * @(#)DcuoLeagueRuleSet.java 1.00 22/10/2014 Copyright 2014 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.dao.census.ruleset;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.RuleSetBase;

import br.com.slzvieira.dcuomonitor.model.DcuoLeague;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 13/07/2016 - sandro.vieira - Implementacao.
 */
public class DcuoLeagueRuleSet extends RuleSetBase {

    private static final String BASE_PATH = "guild_list/guild";

    /* (non-Javadoc)
     * @see RuleSet#addRuleInstances(Digester)
     */
    public void addRuleInstances(Digester digester) {

        digester.addObjectCreate(BASE_PATH, DcuoLeague.class);

        digester.addSetProperties(BASE_PATH, "guild_id", "censusId");
        digester.addSetProperties(BASE_PATH, "name", "name");
        digester.addSetProperties(BASE_PATH, "world_id", "worldCensusId");
        digester.addSetProperties(BASE_PATH, "character_alignment_id", "moralityCensusId");

        digester.addSetNext(BASE_PATH, "add", DcuoLeague.class.getName());
    }
}
