/*
 * @(#)Main.java 1.00 19/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 19/05/2015 - sandro.vieira - Implementacao.
 */
public class Main extends Application {

    /**
     * TODO DOCUMENT ME!
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/MainPane.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.setTitle("DCUO League Manager");
        stage.show();
    }
}
