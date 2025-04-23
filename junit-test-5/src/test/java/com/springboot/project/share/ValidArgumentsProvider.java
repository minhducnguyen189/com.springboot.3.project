package com.springboot.project.share;

import java.security.SecureRandom;
import java.util.Random;

public class ValidArgumentsProvider {

    private int numberOfSection;
    private int sectionLength;
    private int numOfCharacter;
    private int numOfDigit;

    public ValidArgumentsProvider build() {
        Random random = new SecureRandom();
        this.numberOfSection = random.nextInt(3, 7);
        this.sectionLength = random.nextInt(6, 11);
        this.numOfCharacter = sectionLength / 2;
        this.numOfDigit = sectionLength - numOfCharacter;
        return this;
    }

    public int getNumberOfSection() {
        return numberOfSection;
    }

    public int getSectionLength() {
        return sectionLength;
    }

    public int getNumOfCharacter() {
        return numOfCharacter;
    }

    public int getNumOfDigit() {
        return numOfDigit;
    }
}
