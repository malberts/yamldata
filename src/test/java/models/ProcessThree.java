package models;

import models.validation.ValidProcessThree;

/**
 * Process Three requires custom class validation.
 */
@ValidProcessThree
public class ProcessThree {
    private String valueOne;
    private String valueTwo;

    public String getValueOne() {
        return valueOne;
    }

    public ProcessThree setValueOne(String valueOne) {
        this.valueOne = valueOne;
        return this;
    }

    public String getValueTwo() {
        return valueTwo;
    }

    public ProcessThree setValueTwo(String valueTwo) {
        this.valueTwo = valueTwo;
        return this;
    }
}
