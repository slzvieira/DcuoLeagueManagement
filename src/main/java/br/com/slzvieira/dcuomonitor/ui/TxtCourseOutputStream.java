/*
 * @(#)TxtCourseOutputStream.java 1.00 01/06/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.slzvieira.dcuomonitor.ui;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javafx.scene.control.TextArea;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 01/06/2015 - sandro.vieira - Implementacao.
 */
public class TxtCourseOutputStream extends OutputStream {

    /** TODO DOCUMENT ME! */
    private TextArea txtOutput;

    /** TODO DOCUMENT ME! */
    private PrintStream printStream;

    /**
     * TODO DOCUMENT ME!
     * @param txtOutput
     */
    public TxtCourseOutputStream(TextArea txtOutput) {
        this.txtOutput = txtOutput;
        printStream = new PrintStream(this, true);
    }

    @Override
    public void write(int b) throws IOException {
        txtOutput.appendText(Character.toString((char) b));
    }

    /**
     * TODO DOCUMENT ME!
     * @return
     */
    public PrintStream getPrintStream() {
        return printStream;
    }
}
