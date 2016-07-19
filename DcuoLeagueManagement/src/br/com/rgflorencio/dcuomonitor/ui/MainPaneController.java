/*
 * @(#)MainPaneController.java 1.00 19/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.ui;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 19/05/2015 - sandro.vieira - Implementacao.
 */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import br.com.rgflorencio.dcuomonitor.model.DcuoCharacterEntry;
import br.com.rgflorencio.dcuomonitor.model.DcuoCharacterStatus;
import br.com.rgflorencio.dcuomonitor.model.DcuoEntryEvent;
import br.com.rgflorencio.dcuomonitor.model.DcuoSummaryStatus;
import br.com.rgflorencio.dcuomonitor.persistence.DAOException;
import br.com.rgflorencio.dcuomonitor.persistence.DAOFactory;
import br.com.rgflorencio.dcuomonitor.persistence.DcuoCharacterDAO;
import br.com.rgflorencio.dcuomonitor.persistence.DcuoCharacterHistoryDAO;
import br.com.rgflorencio.dcuomonitor.persistence.DcuoEntryDAO;

public class MainPaneController extends BorderPane {

    private static final SimpleDateFormat SERIES_TITLE_FORMAT = new SimpleDateFormat("dd/MM");
    private static final SimpleDateFormat EVENT_TITLE_FORMAT = new SimpleDateFormat("d 'de' MMMM");
    private static final SimpleDateFormat EVENT_SUBTITLE_FORMAT = new SimpleDateFormat("EEEE");
    
    @FXML
    private ListView<DcuoCharacterStatus> lstMember;

    @FXML
    private ListView<String> lstLeague;

    @FXML
    private ListView<DcuoEntryEvent> lstEvent;

    @FXML
    private Label lblName;

    @FXML
    private Label lblCr;

    @FXML
    private Label lblPvpCr;

    @FXML
    private Label lblSkill;

    @FXML
    private Label lblPower;

    @FXML
    private Label lblMentor;

    @FXML
    private Label lblMovement;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblTotalMembers;

    @FXML
    private Label lblCrAverage;

    @FXML
    private Label lblCrPvPAverage;

    @FXML
    private Label lblSkillAverage;

    @FXML
    private Label lblEventDayOfMonth;

    @FXML
    private Label lblEventDayOfWeek;

    @FXML
    private CheckBox chkCr;

    @FXML
    private CheckBox chkCrPvP;

    @FXML
    private CheckBox chkSkill;

    @FXML
    private CheckBox chkCrAverage;

    @FXML
    private CheckBox chkCrPvPAverage;

    @FXML
    private CheckBox chkSkillAverage;

    @FXML
    private CheckBox chkTotal;

    @FXML
    private DatePicker datFeatureStart;

    @FXML
    private DatePicker datFeatureEnd;

    @FXML
    private DatePicker datStatisticStart;

    @FXML
    private DatePicker datStatisticEnd;

    @FXML
    private TextArea txtIncreaseCR;

    @FXML
    private TextArea txtIncreaseSP;

    @FXML
    private TextArea txtEnterLeague;

    @FXML
    private TextArea txtExitLeague;

    @FXML
    private LineChart<String, Number> chtMembro;

    @FXML
    private LineChart<String, Number> chtLeague;

    @FXML
    private StackPane pnlMain;

    @FXML
    private TableView<DcuoCharacterStatus> tblRanking;
    
    @FXML
    private TableColumn<DcuoCharacterStatus, String> colName;
    
    @FXML
    private TableColumn<DcuoCharacterStatus, String> colPower;
    
    @FXML
    private TableColumn<DcuoCharacterStatus, Integer> colLevel;
    
    @FXML
    private TableColumn<DcuoCharacterStatus, Integer> colCr;

    @FXML
    private TableColumn<DcuoCharacterStatus, Integer> colCrPvp;

    @FXML
    private TableColumn<DcuoCharacterStatus, Integer> colSkill;

    @FXML
    private TableColumn<DcuoCharacterStatus, Integer> colRank;

    @FXML
    public void initialize() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/ImportPane.fxml"));
            ImportPaneController importController = new ImportPaneController();
            loader.setRoot(importController);

            Node nodeMember = pnlMain.getChildren().get(0);
            Node nodeEvent = pnlMain.getChildren().get(1);
            Node nodeLeague = pnlMain.getChildren().get(2);
            Node nodeRanking = pnlMain.getChildren().get(3);
            Node nodeImport = loader.load();

            importController.setRoot(this);

            refreshMemberList();
            refreshEventList();

            List<String> titleList = Arrays.asList("Estatísticas", "Ranking", "Importação");
            ObservableList<String> leagueOptionList = FXCollections.observableArrayList(titleList);
            lstLeague.setItems(leagueOptionList);

            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colPower.setCellValueFactory(new PropertyValueFactory<>("powerName"));
            colLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
            colCr.setCellValueFactory(new PropertyValueFactory<>("combatRating"));
            colCrPvp.setCellValueFactory(new PropertyValueFactory<>("combatRatingPvp"));
            colSkill.setCellValueFactory(new PropertyValueFactory<>("skillPoints"));
            colRank.setCellValueFactory(new PropertyValueFactory<>("rankId"));

            lstMember.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {

                nodeMember.setOpacity(1);
                pnlMain.getChildren().clear();
                pnlMain.getChildren().add(nodeMember);

                displayCharacterDetails(n);
                displayCharacterChart(n);
            });

            lstEvent.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {

                nodeEvent.setOpacity(1);
                pnlMain.getChildren().clear();
                pnlMain.getChildren().add(nodeEvent);
                displayEventDetails(n);
            });

            lstLeague.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {

                pnlMain.getChildren().clear();

                switch (lstLeague.getSelectionModel().getSelectedItem()) {
                    case "Estatísticas":
                        nodeLeague.setOpacity(1);
                        pnlMain.getChildren().add(nodeLeague);
                        displayLeagueDetails();
                        break;
                    case "Ranking":
                        nodeRanking.setOpacity(1);
                        pnlMain.getChildren().add(nodeRanking);
                        refreshRankingList();
                        break;
                    case "Importação":
                        nodeImport.setOpacity(1);
                        pnlMain.getChildren().add(nodeImport);
                        break;
                }
            });

        } catch (DAOException daoe) {
            lblStatus.setText("Falha ao obter lista de membros.");
            daoe.printStackTrace();
        } catch (IOException ioe) {
            lblStatus.setText("Erro interno.");
            ioe.printStackTrace();
        }
    }

    @FXML
    void lstMembroKeyReleased(KeyEvent event) {

        if (event.getCode() != KeyCode.DELETE) {
            return;
        }

        lstMember.getItems().remove(lstMember.getSelectionModel().getSelectedIndex());
    }

    @FXML
    void chkFeatureActionPerformed(ActionEvent event) {
        displayCharacterChart(lstMember.getSelectionModel().getSelectedItem());
    }

    @FXML
    void datFeatureActionPerformed(ActionEvent event) {
        displayCharacterChart(lstMember.getSelectionModel().getSelectedItem());
    }

    @FXML
    void chkAverageActionPerformed(ActionEvent event) {
        displayLeagueDetails();
    }

    @FXML
    void datAverageActionPerformed(ActionEvent event) {
        displayLeagueChart();
    }

    /**
     * TODO DOCUMENT ME!
     * @throws DAOException
     */
    void refreshMemberList() throws DAOException {
        DcuoCharacterDAO characterDAO = DAOFactory.getInstance().getDcuoCharacterDAO();
        List<DcuoCharacterStatus> memberList = characterDAO.findAllActiveStatus();
        lstMember.setItems(FXCollections.observableArrayList(memberList));
    }

    /**
     * TODO DOCUMENT ME!
     * @throws DAOException
     */
    void refreshEventList() throws DAOException {
        DcuoEntryDAO entryDAO = DAOFactory.getInstance().getDcuoEntryDAO();
        List<DcuoEntryEvent> entryEventList = entryDAO.findAllEvents();
        lstEvent.setItems(FXCollections.observableArrayList(entryEventList));
    }

    /**
     * TODO DOCUMENT ME!
     * @param message
     */
    void showMessage(String message) {
        lblStatus.setText(message);
    }

    /**
     * TODO DOCUMENT ME!
     * @param event
     */
    private void displayCharacterDetails(DcuoCharacterStatus character) {

        lblName.setText(character.getName());

        lblCr.setText(Integer.toString(character.getCombatRating()));
        lblPvpCr.setText(Integer.toString(character.getCombatRatingPvp()));
        lblSkill.setText(Integer.toString(character.getSkillPoints()));

        lblPower.setText(character.getPowerName());
        lblMentor.setText(character.getOriginName());
        lblMovement.setText(character.getMovementName());

        displayCharacterChart(lstMember.getSelectionModel().getSelectedItem());
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

            DcuoCharacterDAO dao = DAOFactory.getInstance().getDcuoCharacterDAO();
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
     * @param entryEvent 
     */
    private void displayEventDetails(DcuoEntryEvent entryEvent) {

        try {

            lblEventDayOfMonth.setText(EVENT_TITLE_FORMAT.format(entryEvent.getCurrentEntry().getDateTime()));
            lblEventDayOfWeek.setText(EVENT_SUBTITLE_FORMAT.format(entryEvent.getCurrentEntry().getDateTime()));

            int currentEventId = entryEvent.getCurrentEntry().getId();
            int previousEventId = entryEvent.getPreviousEntry().getId();

            DcuoCharacterHistoryDAO dao = DAOFactory.getInstance().getDcuoCharacterHistoryDAO();
            List<String> nameList = dao.findIncreasedCRNameByEventInterval(currentEventId, previousEventId);
            fillTextArea(txtIncreaseCR, nameList);

            nameList = dao.findIncreasedSPNameByEventInterval(currentEventId, previousEventId);
            fillTextArea(txtIncreaseSP, nameList);

            nameList = dao.findEnteredNameByEventInterval(currentEventId, previousEventId);
            fillTextArea(txtEnterLeague, nameList);

            nameList = dao.findExitedNameByEventInterval(currentEventId, previousEventId);
            fillTextArea(txtExitLeague, nameList);

        } catch (DAOException e) {
            lblStatus.setText("Falha ao obter dados.");
            e.printStackTrace();
        }
    }

    /**
     * TODO DOCUMENT ME!
     */
    private void displayLeagueDetails() {

        try {

            DcuoCharacterDAO dao = DAOFactory.getInstance().getDcuoCharacterDAO();
            DcuoSummaryStatus summaryStatus = dao.findSummaryStatus();

            lblTotalMembers.setText(String.format("%,d", summaryStatus.getTotalMembers()));
            lblCrAverage.setText(String.format("%,.2f", summaryStatus.getCombatRating()));
            lblCrPvPAverage.setText(String.format("%,.2f", summaryStatus.getCombatRatingPvp()));
            lblSkillAverage.setText(String.format("%,.2f", summaryStatus.getSkillPoints()));

            displayLeagueChart();

        } catch (DAOException e) {
            lblStatus.setText("Falha ao obter dados.");
            e.printStackTrace();
        }
    }

    /**
     * TODO DOCUMENT ME!
     */
    private void refreshRankingList() {

        try {

            DcuoCharacterDAO dao = DAOFactory.getInstance().getDcuoCharacterDAO();
            tblRanking.setItems(FXCollections.observableArrayList(dao.findAllActiveStatus()));

            colName.setStyle("-fx-alignment: CENTER-RIGHT;");
//            colPower
//            colLevel
//            colCr
//            colCrPvp
//            colSkill
//            colRank

        } catch (DAOException e) {
            e.printStackTrace();
            showMessage(e.getMessage());
        }
    }

    /**
     * TODO DOCUMENT ME!
     */
    private void displayLeagueChart() {

        try {

            if (!chkCrAverage.isSelected() && !chkCrPvPAverage.isSelected() && !chkSkillAverage.isSelected()
                    && !chkTotal.isSelected()) {
                chtMembro.getData().clear();
                return;
            }

            DcuoEntryDAO dao = DAOFactory.getInstance().getDcuoEntryDAO();
            List<DcuoSummaryStatus> summaryList = dao.findAllSummaryStatus(getFeatureStartDate(datStatisticStart),
                getFeatureEndDate(datStatisticEnd));

            XYChart.Series<String, Number> crSeries = new XYChart.Series<>();
            XYChart.Series<String, Number> crPvPSeries = new XYChart.Series<>();
            XYChart.Series<String, Number> skillSeries = new XYChart.Series<>();
            XYChart.Series<String, Number> totalSeries = new XYChart.Series<>();

            crSeries.setName("Combat Rating Médio");
            crPvPSeries.setName("Combat Rating PvP Médio");
            skillSeries.setName("Skill Point Médio");
            totalSeries.setName("Total de Membros");

            String entryTitle;
            DcuoAxisScale scale = new DcuoAxisScale();

            for (DcuoSummaryStatus summary : summaryList) {

                if (chkCrAverage.isSelected()) scale.registerValue(summary.getCombatRating());
                if (chkCrPvPAverage.isSelected()) scale.registerValue(summary.getCombatRatingPvp());
                if (chkSkillAverage.isSelected()) scale.registerValue(summary.getSkillPoints());
                if (chkTotal.isSelected()) scale.registerValue(summary.getTotalMembers());

                entryTitle = SERIES_TITLE_FORMAT.format(summary.getEntryDate());
                crSeries.getData().add(new XYChart.Data<String, Number>(entryTitle, summary.getCombatRating()));
                crPvPSeries.getData().add(new XYChart.Data<String, Number>(entryTitle, summary.getCombatRatingPvp()));
                skillSeries.getData().add(new XYChart.Data<String, Number>(entryTitle, summary.getSkillPoints()));
                totalSeries.getData().add(new XYChart.Data<String, Number>(entryTitle, summary.getTotalMembers()));
            }

            /* Confiruges the scale. */
            NumberAxis axis = (NumberAxis) chtLeague.getYAxis();
            axis.setAutoRanging(false);
            axis.setLowerBound(scale.getLowerBound());
            axis.setUpperBound(scale.getUpperBound());
            axis.setTickUnit(scale.getTickUnit());

            chtLeague.getData().clear();
            if (chkCrAverage.isSelected()) chtLeague.getData().add(crSeries);
            if (chkCrPvPAverage.isSelected()) chtLeague.getData().add(crPvPSeries);
            if (chkSkillAverage.isSelected()) chtLeague.getData().add(skillSeries);
            if (chkTotal.isSelected()) chtLeague.getData().add(totalSeries);

        } catch (DAOException e) {
            lblStatus.setText("Falha ao obter dados.");
            e.printStackTrace();
        }
    }

    /**
     * TODO DOCUMENT ME!
     * @param someTextArea
     * @param nameList
     */
    private void fillTextArea(TextArea someTextArea, List<String> nameList) {

        someTextArea.clear();

        for (String event : nameList) {
            someTextArea.appendText(event);
            someTextArea.appendText("\n");
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
