/*
 * @(#)NewLeaguePaneController.java 1.00 12/07/2016 Copyright 2016 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.swing.JOptionPane;

import br.com.rgflorencio.dcuomonitor.dao.DAOException;
import br.com.rgflorencio.dcuomonitor.dao.census.CensusDAOFactory;
import br.com.rgflorencio.dcuomonitor.dao.census.CensusLeagueDAO;
import br.com.rgflorencio.dcuomonitor.dao.dcuo.DcuoDAOFactory;
import br.com.rgflorencio.dcuomonitor.dao.dcuo.DcuoLeagueDAO;
import br.com.rgflorencio.dcuomonitor.model.DcuoLeague;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 12/07/2016 - sandro.vieira - Implementacao.
 */
public class NewLeaguePaneController extends AnchorPane implements SecondaryController {

    @FXML
    private Label lblCensusIdTitle;

    @FXML
    private Label lblCensusId;

    @FXML
    private Label lblNameTitle;

    @FXML
    private Label lblName;

    @FXML
    private Label lblFactionTitle;

    @FXML
    private Label lblFaction;

    @FXML
    private Label lblWorldTitle;

    @FXML
    private Label lblWorld;

    @FXML
    private TextField txtCensusId;

    @FXML
    private Button btnCheckLeague;

    @FXML
    private Button btnRegisterLeague;

    private MainPaneController mainController;
    private DcuoLeague league;

    public void setRoot(MainPaneController mainPaneController) {
        this.mainController = mainPaneController;
    }

    @FXML
    void btnCheckLeagueOnAction(ActionEvent event) {

        try {

            CensusLeagueDAO dao = CensusDAOFactory.getInstance().getCensusLeagueDAO();
            league = dao.findByCensusId(txtCensusId.getText());

            if (league == null) {
                JOptionPane.showMessageDialog(null, "Liga nao encontrada.");
                return;
            }

            lblCensusIdTitle.setVisible(true);
            lblNameTitle.setVisible(true);
            lblFactionTitle.setVisible(true);
            lblWorldTitle.setVisible(true);

            lblCensusId.setText(Long.toString(league.getCensusId()));
            lblName.setText(league.getName());
            lblFaction.setText(league.getMorality() == null ? "" : league.getMorality().getDescription());
            lblWorld.setText(league.getWorld() == null ? "" : league.getWorld().toString());

            btnRegisterLeague.setVisible(true);

        } catch (DAOException daoe) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel obter os dados para a liga especificada.");
            daoe.printStackTrace();
        }
    }

    @FXML
    void btnRegisterLeagueOnAction(ActionEvent event) {

        if (league == null) {
            JOptionPane.showMessageDialog(null, "É necessário verificar os dados da liga antes de registrá-la.");
            return;
        }

        try {

            DcuoLeagueDAO dao = DcuoDAOFactory.getInstance().getDcuoLeagueDAO();
            DcuoLeague persistedLeague = dao.findByCensusCode(league.getCensusId());

            if (persistedLeague != null) {
                JOptionPane.showMessageDialog(null, "Esta liga já está registrada.");
                return;
            }

            dao.persist(league);
            mainController.loadLeagueList();
            JOptionPane.showMessageDialog(null, "Liga registrada com sucesso.");

        } catch (DAOException daoe) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao acessar a base de dados.");
            daoe.printStackTrace();
        }
    }
}
