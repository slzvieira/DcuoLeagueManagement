/*
 * @(#)MemberPaneController.java 1.00 29/06/2016 Copyright 2016 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.ui;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import br.com.rgflorencio.dcuomonitor.dao.DAOException;
import br.com.rgflorencio.dcuomonitor.dao.dcuo.DcuoDAOFactory;
import br.com.rgflorencio.dcuomonitor.dao.dcuo.DcuoCharacterDAO;
import br.com.rgflorencio.dcuomonitor.model.DcuoCharacterEntry;
import br.com.rgflorencio.dcuomonitor.model.DcuoCharacterStatus;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 29/06/2016 - sandro.vieira - Implementacao.
 */
public class MemberPaneController extends BorderPane {
    
    private static final SimpleDateFormat SERIES_TITLE_FORMAT = new SimpleDateFormat("dd/MM");

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblCr;

    @FXML
    private Label lblMovement;

    @FXML
    private Label lblPower;

    @FXML
    private Label lblPvpCr;

    @FXML
    private DatePicker datFeatureEnd;

    @FXML
    private Label lblSkill;

    @FXML
    private Label lblMentor;

    @FXML
    private CheckBox chkCrPvP;

    @FXML
    private DatePicker datFeatureStart;

    @FXML
    private LineChart<String, Number> chtMembro;

    @FXML
    private Label lblName;

    @FXML
    private CheckBox chkCr;

    @FXML
    private CheckBox chkSkill;

    @FXML
    void chkFeatureActionPerformed(ActionEvent event) {
//        displayCharacterChart(lstMember.getSelectionModel().getSelectedItem());
    }

    @FXML
    void datFeatureActionPerformed(ActionEvent event) {
//        displayCharacterChart(lstMember.getSelectionModel().getSelectedItem());
    }

    /**
     * TODO DOCUMENT ME!
     * @param character
     */
    private void displayCharacterChart(DcuoCharacterStatus character) {

        try {

            if (character == null) {
                chtMembro.getData().clear();
                return;
            }

            if (!chkCr.isSelected() && !chkCrPvP.isSelected() && !chkSkill.isSelected()) {
                chtMembro.getData().clear();
                return;
            }

            DcuoCharacterDAO dao = DcuoDAOFactory.getInstance().getDcuoCharacterDAO();
            List<DcuoCharacterEntry> characterEntryList = dao.findAllEntriesByCharacterId(character.getId(),
                getFeatureStartDate(datFeatureStart), getFeatureEndDate(datFeatureEnd));

            XYChart.Series<String, Number> crSeries = new XYChart.Series<>();
            XYChart.Series<String, Number> pvpCrSeries = new XYChart.Series<>();
            XYChart.Series<String, Number> skillSeries = new XYChart.Series<>();
            String entryTitle;

            crSeries.setName("Combat Rating");
            pvpCrSeries.setName("Combat Rating PvP");
            skillSeries.setName("Skill Points");

            DcuoAxisScale scale = new DcuoAxisScale();

            for (DcuoCharacterEntry characterEntry : characterEntryList) {

                if (chkCr.isSelected()) scale.registerValue(characterEntry.getCombatRating());
                if (chkCrPvP.isSelected()) scale.registerValue(characterEntry.getCombatRatingPvP());
                if (chkSkill.isSelected()) scale.registerValue(characterEntry.getSkillPoints());

                entryTitle = SERIES_TITLE_FORMAT.format(characterEntry.getDate());

                crSeries.getData().add(new XYChart.Data<String, Number>(entryTitle, characterEntry.getCombatRating()));
                pvpCrSeries.getData().add(
                    new XYChart.Data<String, Number>(entryTitle, characterEntry.getCombatRatingPvP()));
                skillSeries.getData()
                    .add(new XYChart.Data<String, Number>(entryTitle, characterEntry.getSkillPoints()));
            }

            /* Confiruges the scale. */
            NumberAxis axis = (NumberAxis) chtMembro.getYAxis();
            axis.setAutoRanging(false);
            axis.setLowerBound(scale.getLowerBound());
            axis.setUpperBound(scale.getUpperBound());
            axis.setTickUnit(scale.getTickUnit());

            chtMembro.getData().clear();
            if (chkCr.isSelected()) chtMembro.getData().add(crSeries);
            if (chkCrPvP.isSelected()) chtMembro.getData().add(pvpCrSeries);
            if (chkSkill.isSelected()) chtMembro.getData().add(skillSeries);

        } catch (DAOException e) {
            lblStatus.setText("Falha ao obter dados.");
            e.printStackTrace();
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @return
     */
    private Date getFeatureStartDate(DatePicker datePicker) {

        if (datePicker.getValue() == null) {
            datePicker.setValue(LocalDate.now().minusDays(15));
        }

        return Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * TODO DOCUMENT ME!
     * @return
     */
    private Date getFeatureEndDate(DatePicker datePicker) {

        if (datePicker.getValue() == null) {
            datePicker.setValue(LocalDate.now());
        }

        Date date = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }
}
