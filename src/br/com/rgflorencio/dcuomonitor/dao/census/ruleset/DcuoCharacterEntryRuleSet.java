/*
 * @(#)DcuoCharacterEntryRuleSet.java 1.00 22/10/2014 Copyright 2014 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.dao.census.ruleset;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.RuleSetBase;

import br.com.rgflorencio.dcuomonitor.model.DcuoCharacterInputData;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 22/10/2014 - sandro.vieira - Implementacao.
 */
public class DcuoCharacterEntryRuleSet extends RuleSetBase {

    private static final String BASE_PATH = "guild_roster_list/guild_roster";

    /* (non-Javadoc)
     * @see RuleSet#addRuleInstances(Digester)
     */
    public void addRuleInstances(Digester digester) {

        digester.addObjectCreate(BASE_PATH, DcuoCharacterInputData.class);

        digester.addSetProperties(BASE_PATH, "rank", "rank");
        digester.addSetProperties(BASE_PATH + "/character_id_join_character", "character_id", "characterId");
        digester.addSetProperties(BASE_PATH + "/character_id_join_character", "name", "name");
        digester.addSetProperties(BASE_PATH + "/character_id_join_character", "level", "level");
        digester.addSetProperties(BASE_PATH + "/character_id_join_character", "combat_rating", "combatRating");
        digester.addSetProperties(BASE_PATH + "/character_id_join_character", "pvp_combat_rating", "pvpCombatRating");
        digester.addSetProperties(BASE_PATH + "/character_id_join_character", "skill_points", "skillPoints");
        digester.addSetProperties(BASE_PATH + "/character_id_join_character", "deleted", "deleted");
        digester.addSetProperties(BASE_PATH + "/character_id_join_character/movement_mode_id_join_movement_mode/name", "en", "movementMode");
        digester.addSetProperties(BASE_PATH + "/character_id_join_character/origin_id_join_origin/name", "en", "origin");
        digester.addSetProperties(BASE_PATH + "/character_id_join_character/gender_id_join_gender", "name", "gender");
        digester.addSetProperties(BASE_PATH + "/character_id_join_character/region_id_join_region/name", "en", "region");
        digester.addSetProperties(BASE_PATH + "/character_id_join_character/personality_id_join_personality/name", "en", "personality");
        digester.addSetProperties(BASE_PATH + "/character_id_join_character/power_type_id_join_power_type/name", "en", "powerType");

        digester.addSetNext(BASE_PATH, "add", DcuoCharacterInputData.class.getName());
    }
}
