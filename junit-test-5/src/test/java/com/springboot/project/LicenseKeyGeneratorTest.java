package com.springboot.project;

import com.springboot.project.utils.LicenseKeyGenerator;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

public class LicenseKeyGeneratorTest {

    @Execution(ExecutionMode.CONCURRENT)
    @RepeatedTest(15)
    void should_return_valid_license_key() {
        LicenseKeyGenerator generator = new LicenseKeyGenerator(6, 11, 5, 5);
        System.out.println(generator.generate());
    }

}
