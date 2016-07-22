/*
 * @(#)ImportPaneController.java 1.00 01/06/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.ui;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import javax.swing.JOptionPane;

import br.com.rgflorencio.dcuomonitor.model.DcuoLeague;
import br.com.rgflorencio.dcuomonitor.service.ImportService;
import br.com.rgflorencio.dcuomonitor.service.ServiceException;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 01/06/2015 - sandro.vieira - Implementacao.
 */
public class ImportPaneController extends BorderPane implements SecondaryController {

    @FXML
    private Label lblLeagueName;

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

    private MainPaneController rootController;
    private FileChooser fileChooser = new FileChooser();
    private File initialDirectory;

    public void setRoot(MainPaneController rootController) {
        this.rootController = rootController;
    }

    @FXML
    void btnFileNameOnAction(ActionEvent event) {

        if (initialDirectory != null) {
            fileChooser.setInitialDirectory(initialDirectory);
        }

        File file = fileChooser.showOpenDialog(txtFileName.getScene().getWindow());

        if (file != null) {
            initialDirectory = file.getParentFile();
            txtFileName.setText(file.getAbsolutePath());
        }
    }

    @FXML
    void btnImportOnAction(ActionEvent event) {
        
        try {

            btnImport.getScene().setCursor(Cursor.WAIT);

            List<String> errorMessageList = validateImportFields();
            if (errorMessageList != null && !errorMessageList.isEmpty()) {
                StringBuilder builder = new StringBuilder(errorMessageList.get(0));
                for (int i = 1; i < errorMessageList.size(); i++) {
                    builder.append("\n");
                    builder.append(errorMessageList.get(i));
                }
                JOptionPane.showMessageDialog(null, builder.toString());
                return;
            }

            DcuoLeague league = rootController.lstLeague.getSelectionModel().getSelectedItem();
            ImportService service = new ImportService();

            if (chkImportTypeOnLine.isSelected()) {
                service.loadGuildDataCurrent(league);
            } else {
                File baseFile = new File(txtFileName.getText());
                Date baseDate = Date.from(txtBaseDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                service.loadGuildDataFromFile(league, baseFile, baseDate);
            }

            JOptionPane.showMessageDialog(null, "Importação realizada com sucesso");

        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Falha ao importar dados");
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

    private List<String> validateImportFields() {

        List<String> errorMessageList = new ArrayList<>();

        DcuoLeague league = rootController.lstLeague.getSelectionModel().getSelectedItem();
        if (league == null) {
            errorMessageList.add("Selecione uma liga para importação.");
        }

        if (chkImportTypeOnLine.isSelected()) {
            return errorMessageList;
        }

        File file = new File(txtFileName.getText());
        if (!file.isFile()) {
            errorMessageList.add("Informe corretamente o nome do arquivo de importação.");
        }

        LocalDate baseDate = txtBaseDate.getValue();
        if (baseDate == null) {
            errorMessageList.add("Informe a data base referente aos dados contidos no arquivo.");
        }

        return errorMessageList;
    }
}
