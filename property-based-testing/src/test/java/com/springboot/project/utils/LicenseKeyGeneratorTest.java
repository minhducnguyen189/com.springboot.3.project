package com.springboot.project.utils;

import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;

class LicenseKeyGeneratorTest {

    private static final String SECTION_PATTERN = "[A-Z]{3,5}[0-9]{3,5}";

    @Property
    void should_return_valid_license_key_random_valid_arguments(
            @ForAll("validArguments") Tuple.Tuple3<Integer, Integer, Integer> validArguments) {
        int numberOfSection = validArguments.get1();
        int numOfCharacter = validArguments.get2();
        int numOfDigit = validArguments.get3();
        int sectionLength = numOfCharacter + numOfDigit;
        System.out.println(
                "numberOfSection: "
                        + numberOfSection
                        + " "
                        + "sectionLength: "
                        + sectionLength
                        + " "
                        + "numOfCharacter: "
                        + numOfCharacter
                        + " "
                        + "numOfDsigit: "
                        + numOfDigit
                        + " ");
        String sectionPattern = this.generateSectionPattern(numberOfSection);
        LicenseKeyGenerator generator =
                new LicenseKeyGenerator(numberOfSection, sectionLength, numOfCharacter, numOfDigit);
        String actualResult = generator.generate();
        System.out.println(actualResult);
        Assertions.assertTrue(actualResult.matches(sectionPattern));
    }

    @Property
    void should_return_invalid_license_key_random_invalid_arguments(
            @ForAll("inValidArguments") Tuple.Tuple3<Integer, Integer, Integer> validArguments) {
        int numberOfSection = validArguments.get1();
        int numOfCharacter = validArguments.get2();
        int numOfDigit = validArguments.get3();
        int sectionLength = numOfCharacter + numOfDigit;
        System.out.println(
                "numberOfSection: "
                        + numberOfSection
                        + " "
                        + "sectionLength: "
                        + sectionLength
                        + " "
                        + "numOfCharacter: "
                        + numOfCharacter
                        + " "
                        + "numOfDsigit: "
                        + numOfDigit
                        + " ");
        LicenseKeyGenerator generator =
                new LicenseKeyGenerator(numberOfSection, sectionLength, numOfCharacter, numOfDigit);
        Assertions.assertThrows(RuntimeException.class, generator::generate);
    }

    @Provide
    Arbitrary<Tuple.Tuple3<Integer, Integer, Integer>> validArguments() {
        Arbitrary<Integer> numberOfSection = Arbitraries.integers().between(3, 6);
        Arbitrary<Integer> numOfCharacter = Arbitraries.integers().between(3, 5);
        Arbitrary<Integer> numOfDigit = Arbitraries.integers().between(3, 5);
        return Combinators.combine(numberOfSection, numOfCharacter, numOfDigit).as(Tuple::of);
    }

    @Provide
    Arbitrary<Tuple.Tuple3<Integer, Integer, Integer>> inValidArguments() {
        Arbitrary<Integer> invalidNumberOfSection =
                Arbitraries.oneOf(
                        Arbitraries.integers().between(Integer.MIN_VALUE, 2),
                        Arbitraries.integers().between(7, Integer.MAX_VALUE));

        Arbitrary<Integer> invalidNumberOfCharacter =
                Arbitraries.oneOf(
                        Arbitraries.integers().between(Integer.MIN_VALUE, 2),
                        Arbitraries.integers().between(7, Integer.MAX_VALUE));

        Arbitrary<Integer> invalidNumberOfDigit =
                Arbitraries.oneOf(
                        Arbitraries.integers().between(Integer.MIN_VALUE, 2),
                        Arbitraries.integers().between(7, Integer.MAX_VALUE));

        return Combinators.combine(
                        invalidNumberOfSection, invalidNumberOfCharacter, invalidNumberOfDigit)
                .as(Tuple::of);
    }

    private String generateSectionPattern(int numberOfSection) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numberOfSection; i++) {
            builder.append(SECTION_PATTERN);
            if (i < numberOfSection - 1) {
                builder.append("-");
            }
        }
        return builder.toString();
    }
}
