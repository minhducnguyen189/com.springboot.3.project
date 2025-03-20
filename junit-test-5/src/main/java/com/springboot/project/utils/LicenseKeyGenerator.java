package com.springboot.project.utils;

import java.security.SecureRandom;
import java.util.Random;

public class LicenseKeyGenerator {

  private final int numberOfSections;
  private final int sectionLength;
  private final int numberOfCharacter;
  private final int numberOfDigit;

  public LicenseKeyGenerator(
      int numberOfSections, int sectionLength, int numberOfCharacter, int numberOfDigit) {
    this.numberOfSections = numberOfSections;
    this.sectionLength = sectionLength;
    this.numberOfCharacter = numberOfCharacter;
    this.numberOfDigit = numberOfDigit;
  }

  public String generate() {
    if (numberOfSections < 2
        || numberOfSections > 6
        || sectionLength < 6
        || sectionLength > 10
        || numberOfDigit < 3
        || numberOfDigit > 5
        || numberOfCharacter < 3
        || numberOfCharacter > 5) {
      throw new RuntimeException("Inputs are not correct!");
    }
    StringBuilder builder = new StringBuilder();
    for (int section = 0; section < numberOfSections; section++) {
      Random random = new SecureRandom();
      for (int i = 0; i < numberOfCharacter; i++) {
        char character = (char) (random.nextInt(26) + 'A');
        builder.append(character);
      }

      for (int i = 0; i < numberOfDigit; i++) {
        int digit = random.nextInt(10);
        builder.append(digit);
      }
      if (section < numberOfSections - 1) {
        builder.append("-");
      }
    }
    return builder.toString();
  }
}
