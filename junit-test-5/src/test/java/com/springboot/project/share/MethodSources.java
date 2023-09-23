package com.springboot.project.share;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class MethodSources {

    private static Stream<Arguments> getValidArguments() {
        return Stream.of(
                Arguments.of(3, 10, 5, 5),
                Arguments.of(4, 6, 3, 3)
        );
    }

}
