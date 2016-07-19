/*
 * @(#)ImportPaneController.java 1.00 01/06/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.ui;

import java.io.PrintStream;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import br.com.rgflorencio.dcuomonitor.model.DcuoCharacterInputData;
import br.com.rgflorencio.dcuomonitor.service.ImportService;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 01/06/2015 - sandro.vieira - Implementacao.
 */
public class ImportPaneController extends BorderPane {

    @FXML
    private ToggleGroup grpImportType;

    @FXML
    private RadioButton chkImportTypeLocal;

    @FXML
    private RadioButton chkImportTypeOnLine;

    @FXML
    private TextField txtFileName;

    @FXML
    private Button btnFileName;

    @FXML
    private DatePicker txtBaseDate;

    @FXML
    private TextArea txtCourse;

    @FXML
    private Button btnImport;

    /** TODO DOCUMENT ME! */
    private MainPaneController rootController;
    
//    public ImportPaneController(MainPaneController rootController) {
//        super();
//        this.rootController = rootController;
//    }

    void setRoot(MainPaneController rootController) {
        this.rootController = rootController;
    }

    @FXML
    void btnImportOnAction(ActionEvent event) {

        btnImport.getScene().setCursor(Cursor.WAIT);

        try (PrintStream stream = new TxtCourseOutputStream(txtCourse).getPrintStream()) {

//            Calendar baseCalendar = Calendar.getInstance();
//            baseCalendar.set(2015, 4, 18, 9, 0, 0);
//            Date baseDate = baseCalendar.getTime();

            ImportService service = new ImportService();

//            List<DcuoCharacterInputData> characterList = service.loadGuildListFromFile(new File(
//                "C:\\Sandro\\Dropbox\\Documents\\dcuo\\MyProject\\guild_roster_2015_05_18.xml"));
            List<DcuoCharacterInputData> characterList = service.loadGuildList(stream);
            service.saveGuildList(characterList, null, stream);

            rootController.refreshMemberList();
            rootController.refreshEventList();

        } catch (Exception e) {
//            rootController.showMessage(e.getMessage());
            e.printStackTrace();
        } finally {
            btnImport.getScene().setCursor(Cursor.DEFAULT);
        }
    }

    @FXML
    void chkImportTypeOnAction(ActionEvent event) {

        if (chkImportTypeOnLine.isSelected()) {
            txtFileName.setDisable(true);
            btnFileName.setDisable(true);
            txtBaseDate.setDisable(true);
        } else {
            txtFileName.setDisable(false);
            btnFileName.setDisable(false);
            txtBaseDate.setDisable(false);
        }
    }
}
