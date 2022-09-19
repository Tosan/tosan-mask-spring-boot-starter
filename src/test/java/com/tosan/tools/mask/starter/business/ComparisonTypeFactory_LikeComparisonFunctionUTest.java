package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.ComparisonType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mina khoshnevisan
 * @since 9/19/2022
 */
public class ComparisonTypeFactory_LikeComparisonFunctionUTest {
    private ComparisonTypeFactory.ComparisonFunction function = new ComparisonTypeFactory.
            LikeComparisonFunction();
    String fieldName = "testFieldName";

    private static Stream<Arguments> parameterNames() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of("", false),
                Arguments.of("test", true),
                Arguments.of("fieldName", true),
                Arguments.of("NAME", true),
                Arguments.of("anotherField", false),
                Arguments.of("testFieldName2", false),
                Arguments.of("testFieldName", true),
                Arguments.of("testfieldname", true),
                Arguments.of("TESTFIELDNAME", true),
                Arguments.of("TEsTFIElDnAME", true));
    }

    @ParameterizedTest
    @MethodSource("parameterNames")
    public void testCompare_differentParameters_returnCorrectResult(String parameterName, boolean result) {
        boolean compare = function.compare(fieldName, parameterName);
        assertEquals(result, compare);
    }

    @Test
    public void testGetComparisonType_returnEqualsIgnoreCaseType() {
        assertEquals(ComparisonType.LIKE, function.getComparisonType());
    }
}