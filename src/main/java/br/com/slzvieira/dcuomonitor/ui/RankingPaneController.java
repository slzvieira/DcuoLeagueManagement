/*
 * @(#)RankingPaneController.java 1.00 29/06/2016 Copyright 2016 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 29/06/2016 - sandro.vieira - Implementacao.
 */
public class RankingPaneController extends BorderPane implements SecondaryController {

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colCrPvp;

    @FXML
    private TableView<?> tblRanking;

    @FXML
    private TableColumn<?, ?> colLevel;

    @FXML
    private TableColumn<?, ?> colSkill;

    @FXML
    private TableColumn<?, ?> colRank;

    @FXML
    private TableColumn<?, ?> colCr;

    @FXML
    private TableColumn<?, ?> colPower;

    private MainPaneController rootController;
    
    @Override
    public void setRoot(MainPaneController root) {
        rootController = root;
    }
}
