/*
 * @(#)LeagueDetailPaneController.java 1.00 29/06/2016 Copyright 2016 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 29/06/2016 - sandro.vieira - Implementacao.
 */
public class LeagueDetailPaneController extends BorderPane implements SecondaryController {

    @FXML
    private Label lblSkillAverage;

    @FXML
    private Label lblTotalMembers;

    @FXML
    private Label lblCrAverage;

    @FXML
    private DatePicker datStatisticStart;

    @FXML
    private CheckBox chkCrPvPAverage;

    @FXML
    private CheckBox chkTotal;

    @FXML
    private CheckBox chkCrAverage;

    @FXML
    private CheckBox chkSkillAverage;

    @FXML
    private Label lblCrPvPAverage;

    @FXML
    private LineChart<?, ?> chtLeague;

    @FXML
    private DatePicker datStatisticEnd;

    private MainPaneController mainController;

    public void setRoot(MainPaneController mainPaneController) {
        this.mainController = mainPaneController;
    }

    @FXML
    void chkAverageActionPerformed(ActionEvent event) {

    }

    @FXML
    void datAverageActionPerformed(ActionEvent event) {

    }
}
