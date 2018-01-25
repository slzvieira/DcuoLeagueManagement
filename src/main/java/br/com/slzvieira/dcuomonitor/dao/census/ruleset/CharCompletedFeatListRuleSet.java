/*
 * @(#)CharCompletedFeatListRuleSet.java 1.00 22/10/2014 Copyright 2014 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.dao.census.ruleset;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.RuleSetBase;

import br.com.slzvieira.dcuomonitor.model.Feat;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 22/10/2014 - sandro.vieira - Implementacao.
 */
public class CharCompletedFeatListRuleSet extends RuleSetBase {

    private static final String BASE_PATH = "character_list/character/character_id_join_characters_active_feat_list/character_id_join_characters_active_feat/feat_id_join_feat";
    
    /* (non-Javadoc)
     * @see RuleSet#addRuleInstances(Digester)
     */
    public void addRuleInstances(Digester digester) {
        digester.addObjectCreate(BASE_PATH, Feat.class);
        digester.addSetProperties(BASE_PATH + "/feat_category_id_join_feat_category/parent_category_id_join_feat_category/category_name", "en", "parentCategory");
        digester.addSetProperties(BASE_PATH + "/feat_category_id_join_feat_category/category_name", "en", "category");
        digester.addSetProperties(BASE_PATH + "/name", "en", "name");
        digester.addSetProperties(BASE_PATH + "/description", "en", "description");
        digester.addSetProperties(BASE_PATH, "image_path", "imagePath");
        digester.addSetProperties(BASE_PATH, "predicate", "predicate");
        digester.addSetNext(BASE_PATH + "/name", "add", Feat.class.getName());
    }
}
