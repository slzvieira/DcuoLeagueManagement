/*
 * @(#)MainPaneController.java 1.00 19/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import javax.swing.JOptionPane;

import br.com.rgflorencio.dcuomonitor.dao.DAOException;
import br.com.rgflorencio.dcuomonitor.dao.dcuo.DcuoCharacterDAO;
import br.com.rgflorencio.dcuomonitor.dao.dcuo.DcuoDAOFactory;
import br.com.rgflorencio.dcuomonitor.dao.dcuo.DcuoEntryDAO;
import br.com.rgflorencio.dcuomonitor.dao.dcuo.DcuoLeagueDAO;
import br.com.rgflorencio.dcuomonitor.model.DcuoCharacterStatus;
import br.com.rgflorencio.dcuomonitor.model.DcuoEntryEvent;
import br.com.rgflorencio.dcuomonitor.model.DcuoLeague;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 19/05/2015 - sandro.vieira - Implementacao.
 */
public class MainPaneController extends BorderPane {

    @FXML
    ListView<DcuoLeague> lstLeague;
    
    @FXML
    private ListView<DcuoCharacterStatus> lstMember;

    @FXML
    private ListView<DcuoEntryEvent> lstEvent;

    @FXML
    private ListView<String> lstGeneral;

    @FXML
    private StackPane pnlMain;

    @FXML
    private Label lblStatus;

    private NewLeaguePaneController nodeNewLeague;
    private LeagueDetailPaneController nodeLeagueDetail;
    private ImportPaneController nodeImport;

    @FXML
    void lstMembroKeyReleased(KeyEvent event) {

//        if (event.getCode() != KeyCode.DELETE) {
//            return;
//        }
//
//        lstMember.getItems().remove(lstMember.getSelectionModel().getSelectedIndex());
    }

    @FXML
    public void initialize() {

        try {

            loadPanes();
            loadLeagueList();

        } catch (DAOException daoe) {
//            lblStatus.setText("Falha ao obter lista de membros.");
            daoe.printStackTrace();
        } catch (IOException ioe) {
//            lblStatus.setText("Erro interno.");
            ioe.printStackTrace();
        }

//            refreshMemberList();
//            refreshEventList();

//            List<String> titleList = Arrays.asList("Estatísticas", "Ranking", "Importação");
//            ObservableList<String> leagueOptionList = FXCollections.observableArrayList(titleList);
//            lstLeague.setItems(leagueOptionList);

//            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
//            colPower.setCellValueFactory(new PropertyValueFactory<>("powerName"));
//            colLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
//            colCr.setCellValueFactory(new PropertyValueFactory<>("combatRating"));
//            colCrPvp.setCellValueFactory(new PropertyValueFactory<>("combatRatingPvp"));
//            colSkill.setCellValueFactory(new PropertyValueFactory<>("skillPoints"));
//            colRank.setCellValueFactory(new PropertyValueFactory<>("rankId"));

//            lstMember.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
//
//                nodeMember.setOpacity(1);
//                pnlMain.getChildren().clear();
//                pnlMain.getChildren().add(nodeMember);
//
//                displayCharacterDetails(n);
//                displayCharacterChart(n);
//            });

//            lstEvent.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
//
//                nodeEvent.setOpacity(1);
//                pnlMain.getChildren().clear();
//                pnlMain.getChildren().add(nodeEvent);
//                displayEventDetails(n);
//            });

        lstLeague.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {

            try {

                unloadLeague();
                pnlMain.getChildren().clear();

                if (lstLeague.getSelectionModel().getSelectedItem() == null) {
                    return;
                }

                if (lstLeague.getSelectionModel().getSelectedItem().getId() == 0) {
                    pnlMain.getChildren().add(this.nodeNewLeague);
                    return;
                }

                loadLeague(lstLeague.getSelectionModel().getSelectedItem());
                pnlMain.getChildren().add(this.nodeLeagueDetail);

            } catch (DAOException daoe) {
                JOptionPane.showMessageDialog(null, "Falha ao obter lista de membros.");
                daoe.printStackTrace();
            }
                
//            switch (lstLeague.getSelectionModel().getSelectedItem().getId()) {
//                case "Estatísticas":
//                    nodeLeague.setOpacity(1);
//                    pnlMain.getChildren().add(nodeLeague);
//                    displayLeagueDetails();
//                    break;
//                case "Ranking":
//                    nodeRanking.setOpacity(1);
//                    pnlMain.getChildren().add(nodeRanking);
//                    refreshRankingList();
//                    break;
//                case "Importação":
//                    nodeImport.setOpacity(1);
//                    pnlMain.getChildren().add(nodeImport);
//                    break;
//            }
        });

        lstGeneral.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {
            pnlMain.getChildren().clear();
            switch (lstGeneral.getSelectionModel().getSelectedIndex()) {
//                case 0: pnlMain.getChildren().add(this.???); // Ranking
                case 1: pnlMain.getChildren().add(this.nodeImport); // Importacao
            }
        });
    }

    private void loadPanes() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/NewLeaguePane.fxml"));
        loader.setRoot(new NewLeaguePaneController());
        this.nodeNewLeague = loader.load();
        ((NewLeaguePaneController) loader.getController()).setRoot(this);

        loader = new FXMLLoader(getClass().getResource("/resources/LeagueDetailPane.fxml"));
        loader.setRoot(new LeagueDetailPaneController());
        this.nodeLeagueDetail = loader.load();
        ((LeagueDetailPaneController) loader.getController()).setRoot(this);

        EventPaneController eventController;
        MemberPaneController memberController;
        RankingPaneController rankingController;

//      "/resources/EventPane.fxml"
//      "/resources/MemberPane.fxml"
//      "/resources/RankingPane.fxml"

        loader = new FXMLLoader(getClass().getResource("/resources/ImportPane.fxml"));
        loader.setRoot(new ImportPaneController());
        this.nodeImport = loader.load();
//        this.nodeImport.setRoot(this);
        ((ImportPaneController) loader.getController()).setRoot(this);
    }

    /**
     * TODO DOCUMENT ME!
     * @throws DAOException
     */
    void loadLeagueList() throws DAOException {

        DcuoLeagueDAO leagueDAO = DcuoDAOFactory.getInstance().getDcuoLeagueDAO();
        List<DcuoLeague> leagueList = leagueDAO.findAll();

        /* Adicionando Liga fake "[Adicionar nova liga]" */
        DcuoLeague fakeLeague = new DcuoLeague();
        fakeLeague.setName("[Adicionar nova liga]");
        leagueList.add(0, fakeLeague);

        lstLeague.setItems(FXCollections.observableArrayList(leagueList));
        lstLeague.getSelectionModel().select(-1);
    }

    /**
     * TODO DOCUMENT ME!
     */
    private void unloadLeague() {
        lstMember.setItems(FXCollections.observableArrayList(new ArrayList<>(0)));
        lstGeneral.setItems(FXCollections.observableArrayList(new ArrayList<>(0)));
    }

    /**
     * TODO DOCUMENT ME!
     * @param selectedItem
     */
    private void loadLeague(DcuoLeague league) throws DAOException {

        DcuoCharacterDAO characterDAO = DcuoDAOFactory.getInstance().getDcuoCharacterDAO();
        List<DcuoCharacterStatus> characterList = characterDAO.findByLeagueId(league.getId());
        lstMember.setItems(FXCollections.observableArrayList(characterList));

        DcuoEntryDAO entryDAO = DcuoDAOFactory.getInstance().getDcuoEntryDAO();
        List<DcuoEntryEvent> entryEventList = entryDAO.findEventsByLeagueId(league.getId());
        lstEvent.setItems(FXCollections.observableArrayList(entryEventList));

        List<String> generalList = new ArrayList<>(2);
        generalList.add("Ranking");
        generalList.add("Importação");
        lstGeneral.setItems(FXCollections.observableArrayList(generalList));
    }
}
