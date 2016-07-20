/*
 * @(#)EventPaneController.java 1.00 29/06/2016 Copyright 2016 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 29/06/2016 - sandro.vieira - Implementacao.
 */
public class EventPaneController extends BorderPane {

    @FXML
    private TextArea txtIncreaseSP;

    @FXML
    private Label lblEventDayOfWeek;

    @FXML
    private TextArea txtExitLeague;

    @FXML
    private Label lblEventDayOfMonth;

    @FXML
    private TextArea txtIncreaseCR;

    @FXML
    private TextArea txtEnterLeague;
}
