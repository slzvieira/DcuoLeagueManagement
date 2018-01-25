/*
 * @(#)DcuoAxisScale.java 1.00 20/05/2015 Copyright 2015 CPA Consulting LTDA.
 * Todos os direitos reservados. CPA PROPRIETARY/CONFIDENTIAL.
 * Proibida a cópia e-ou a reprodução deste código.
 */
package br.com.rgflorencio.dcuomonitor.ui;

/**
 * TODO DOCUMENT ME!
 * @author sandro.vieira
 * @version 1.0, 19/05/2015 - sandro.vieira - Implementacao.
 */
class DcuoAxisScale {

    private Number minimum;
    private Number maximum;

    private double lowerBound;
    private double upperBound;
    private double tickUnit;

    private boolean computed = false;

    void registerValue(Number value) {
        
        if (value == null) {
            return;
        }
        
        if (minimum == null || value.doubleValue() < minimum.doubleValue()) {
            minimum = value;
            computed = false;
        }

        if (maximum == null || value.doubleValue() > maximum.doubleValue()) {
            maximum = value;
            computed = false;
        }
    }

    double getLowerBound() {
        if (!computed) compute();
        return lowerBound;
    }

    double getUpperBound() {
        if (!computed) compute();
        return upperBound;
    }

    double getTickUnit() {
        if (!computed) compute();
        return tickUnit;
    }

    private void compute() {

        if (minimum == null || maximum == null) {
            throw new IllegalArgumentException("Valores inconsistentes [min: " + minimum + "; max: " + maximum + "]");
        }

        double min = minimum.doubleValue();
        double max = maximum.doubleValue();

        if (max - min > 100) {
            tickUnit = 20;
        } else if (max - min > 50) {
            tickUnit = 10;
        } else if (max - min > 10) {
            tickUnit = 5;
        } else {
            tickUnit = 1;
        }

        lowerBound = min - (min % tickUnit);
        lowerBound -= tickUnit;

        upperBound = max - (max % tickUnit);
        upperBound += tickUnit;

        computed = true;
    }
}
