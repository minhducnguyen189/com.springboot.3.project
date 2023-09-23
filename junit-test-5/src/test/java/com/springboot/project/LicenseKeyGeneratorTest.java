package com.springboot.project;

import com.springboot.project.share.ValidArgumentsProvider;
import com.springboot.project.utils.LicenseKeyGenerator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;


class LicenseKeyGeneratorTest {

    private static final String SECTION_PATTERN = "[A-Z]{3,5}[0-9]{3,5}";

    @Test
    @DisplayName("should return valid licenseKey with 3 sections and length is 10 with 5 character and 5 digit")
    void should_return_valid_license_key_with_3_sections() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            builder.append(SECTION_PATTERN);
            if (i < 2) {
                builder.append("-");
            }
        }
        LicenseKeyGenerator generator = new LicenseKeyGenerator(3, 10, 5, 5);
        Assertions.assertTrue(generator.generate().matches(builder.toString()));
    }

    @ParameterizedTest(name = "[{index}] sections: {0}")
    @ValueSource(ints = {3, 4, 6})
    @DisplayName("should return valid licenseKey with 3, 4 and 6 sections")
    void should_return_valid_license_key_with_parameterized_and_valueSource(int numberOfSection) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numberOfSection; i++) {
            builder.append(SECTION_PATTERN);
            if (i < numberOfSection - 1) {
                builder.append("-");
            }
        }
        LicenseKeyGenerator generator = new LicenseKeyGenerator(numberOfSection, 10, 5, 5);
        Assertions.assertTrue(generator.generate().matches(builder.toString()));
    }

    @ParameterizedTest(name = "[{index}] sections: {0} - sectionLength: {1} - numOfCharacter: {2} - numOfDigit: {3}")
    @CsvSource({"3,10,5,5", "4,6,3,3"})
    @DisplayName("should return valid licenseKey with input csv source")
    void should_return_valid_license_key_with_parameterized_csv_source(int numberOfSection, int sectionLength, int numOfCharacter, int numOfDigit) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numberOfSection; i++) {
            builder.append(SECTION_PATTERN);
            if (i < numberOfSection - 1) {
                builder.append("-");
            }
        }
        LicenseKeyGenerator generator = new LicenseKeyGenerator(numberOfSection, sectionLength, numOfCharacter, numOfDigit);
        Assertions.assertTrue(generator.generate().matches(builder.toString()));
    }


    @ParameterizedTest(name = "[{index}] sections: {0} - sectionLength: {1} - numOfCharacter: {2} - numOfDigit: {3}")
    @CsvSource({"3,10,5,5", "4,6,3,3"})
    @DisplayName("should return valid licenseKey with input csv source and argument accessor")
    void should_return_valid_license_key_with_parameterized_csv_source_with_argument_accessor(ArgumentsAccessor argumentsAccessor) {
        int numberOfSection = argumentsAccessor.getInteger(0);
        int sectionLength = argumentsAccessor.getInteger(1);
        int numOfCharacter = argumentsAccessor.getInteger(2);
        int numOfDigit = argumentsAccessor.getInteger(3);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numberOfSection; i++) {
            builder.append(SECTION_PATTERN);
            if (i < numberOfSection - 1) {
                builder.append("-");
            }
        }
        LicenseKeyGenerator generator = new LicenseKeyGenerator(numberOfSection, sectionLength, numOfCharacter, numOfDigit);
        Assertions.assertTrue(generator.generate().matches(builder.toString()));
    }

    @ParameterizedTest(name = "[{index}] sections: {0} - sectionLength: {1} - numOfCharacter: {2} - numOfDigit: {3}")
    @CsvFileSource(resources = {"/data.csv"}, numLinesToSkip = 1)
    @DisplayName("should return valid licenseKey with input csv file source")
    void should_return_valid_license_key_with_parameterized_csv_file_source(int numberOfSection, int sectionLength, int numOfCharacter, int numOfDigit) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numberOfSection; i++) {
            builder.append(SECTION_PATTERN);
            if (i < numberOfSection - 1) {
                builder.append("-");
            }
        }
        LicenseKeyGenerator generator = new LicenseKeyGenerator(numberOfSection, sectionLength, numOfCharacter, numOfDigit);
        Assertions.assertTrue(generator.generate().matches(builder.toString()));
    }

    @ParameterizedTest(name = "[{index}] sections: {0} - sectionLength: {1} - numOfCharacter: {2} - numOfDigit: {3}")
    @MethodSource("provideValidArguments")
    @DisplayName("should return valid licenseKey with input using method source")
    void should_return_valid_license_key_with_parameterized_method_source(int numberOfSection, int sectionLength, int numOfCharacter, int numOfDigit) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numberOfSection; i++) {
            builder.append(SECTION_PATTERN);
            if (i < numberOfSection - 1) {
                builder.append("-");
            }
        }
        LicenseKeyGenerator generator = new LicenseKeyGenerator(numberOfSection, sectionLength, numOfCharacter, numOfDigit);
        Assertions.assertTrue(generator.generate().matches(builder.toString()));
    }

    private static Stream<Arguments> provideValidArguments() {
        return Stream.of(
                Arguments.of(3, 10, 5, 5),
                Arguments.of(4, 6, 3, 3)
        );
    }

    @ParameterizedTest(name = "[{index}] sections: {0} - sectionLength: {1} - numOfCharacter: {2} - numOfDigit: {3}")
    @MethodSource
    @DisplayName("should return valid licenseKey with input using method source contain same test method name")
    void should_return_valid_license_key_with_parameterized_method_source_same_test_method_name(int numberOfSection, int sectionLength, int numOfCharacter, int numOfDigit) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numberOfSection; i++) {
            builder.append(SECTION_PATTERN);
            if (i < numberOfSection - 1) {
                builder.append("-");
            }
        }
        LicenseKeyGenerator generator = new LicenseKeyGenerator(numberOfSection, sectionLength, numOfCharacter, numOfDigit);
        Assertions.assertTrue(generator.generate().matches(builder.toString()));
    }

    private static Stream<Arguments> should_return_valid_license_key_with_parameterized_method_source_same_test_method_name() {
        return Stream.of(
                Arguments.of(3, 10, 5, 5),
                Arguments.of(4, 6, 3, 3)
        );
    }

    @ParameterizedTest(name = "[{index}] sections: {0} - sectionLength: {1} - numOfCharacter: {2} - numOfDigit: {3}")
    @MethodSource("com.springboot.project.share.MethodSources#getValidArguments")
    @DisplayName("should return valid licenseKey with input using method source with sharing methods")
    void should_return_valid_license_key_with_parameterized_method_source_with_sharing_methods(int numberOfSection, int sectionLength, int numOfCharacter, int numOfDigit) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numberOfSection; i++) {
            builder.append(SECTION_PATTERN);
            if (i < numberOfSection - 1) {
                builder.append("-");
            }
        }
        LicenseKeyGenerator generator = new LicenseKeyGenerator(numberOfSection, sectionLength, numOfCharacter, numOfDigit);
        Assertions.assertTrue(generator.generate().matches(builder.toString()));
    }

    @Execution(ExecutionMode.CONCURRENT)
    @RepeatedTest(30)
    @DisplayName("should return valid licenseKey with input using method source and random arguments")
    void should_return_valid_license_key_with_repeated_test_and_random_arguments(RepetitionInfo repetitionInfo) {
        ValidArgumentsProvider validArgumentsProvider = new ValidArgumentsProvider().build();
        int numberOfSection = validArgumentsProvider.getNumberOfSection();
        int sectionLength = validArgumentsProvider.getSectionLength();
        int numOfCharacter = validArgumentsProvider.getNumOfCharacter();
        int numOfDigit = validArgumentsProvider.getNumOfDigit();
        System.out.println(
                "Repetition #" + repetitionInfo.getCurrentRepetition() + " " +
                "numberOfSection: " + numberOfSection + " " +
                "sectionLength: " + sectionLength + " " +
                "numOfCharacter: " + numOfCharacter + " " +
                "numOfDigit: " + numOfDigit + " "
        );
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numberOfSection; i++) {
            builder.append(SECTION_PATTERN);
            if (i < numberOfSection - 1) {
                builder.append("-");
            }
        }
        LicenseKeyGenerator generator = new LicenseKeyGenerator(numberOfSection, sectionLength, numOfCharacter, numOfDigit);
        Assertions.assertTrue(generator.generate().matches(builder.toString()));
    }

}
