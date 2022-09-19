package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.ComparisonType;
import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import com.tosan.tools.mask.starter.config.SecureParameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

/**
 * @author mina khoshnevisan
 * @since 9/19/2022
 */
public class ComparisonTypeFactoryUTest {

    private ComparisonTypeFactory comparisonTypeFactory;

    @BeforeEach
    public void setup() {
        comparisonTypeFactory = new ComparisonTypeFactory(new ArrayList<>());
    }

    @Test
    public void testComparisonMapInitialization_initCorrectly() {
        List<ComparisonTypeFactory.ComparisonFunction> functionList = new ArrayList<>();
        ComparisonTypeFactory.EqualsComparisonFunction equalsComparisonFunction = new ComparisonTypeFactory.EqualsComparisonFunction();
        functionList.add(equalsComparisonFunction);
        ComparisonTypeFactory.EqualsIgnoreCaseComparisonFunction equalsIgnoreCaseComparisonFunction = new ComparisonTypeFactory.EqualsIgnoreCaseComparisonFunction();
        functionList.add(equalsIgnoreCaseComparisonFunction);
        ComparisonTypeFactory.LikeComparisonFunction likeComparisonFunction = new ComparisonTypeFactory.LikeComparisonFunction();
        functionList.add(likeComparisonFunction);
        ComparisonTypeFactory.LeftLikeComparisonFunction leftLikeComparisonFunction = new ComparisonTypeFactory.LeftLikeComparisonFunction();
        functionList.add(leftLikeComparisonFunction);
        ComparisonTypeFactory.RightLikeComparisonFunction rightLikeComparisonFunction = new ComparisonTypeFactory.RightLikeComparisonFunction();
        functionList.add(rightLikeComparisonFunction);
        comparisonTypeFactory = new ComparisonTypeFactory(functionList);
        Map<ComparisonType, ComparisonTypeFactory.ComparisonFunction> comparisonFunctionMap = comparisonTypeFactory.comparisonFunctionMap;
        assertEquals(5, comparisonFunctionMap.size());
        assertEquals(equalsComparisonFunction, comparisonFunctionMap.get(ComparisonType.EQUALS));
        assertEquals(equalsIgnoreCaseComparisonFunction, comparisonFunctionMap.get(ComparisonType.EQUALS_IGNORE_CASE));
        assertEquals(likeComparisonFunction, comparisonFunctionMap.get(ComparisonType.LIKE));
        assertEquals(leftLikeComparisonFunction, comparisonFunctionMap.get(ComparisonType.LEFT_LIKE));
        assertEquals(rightLikeComparisonFunction, comparisonFunctionMap.get(ComparisonType.RIGHT_LIKE));
    }

    @Test
    public void testCompare_nullFieldName_returnFalse() {
        boolean compare = comparisonTypeFactory.compare(null, null);
        assertFalse(compare);
    }

    @Test
    public void testCompare_emptyFieldName_returnFalse() {
        boolean compare = comparisonTypeFactory.compare("", null);
        assertFalse(compare);
    }

    @Test
    public void testCompare_nonEmptyFieldName_compareWithRelatedComparisonType() {
        List<ComparisonTypeFactory.ComparisonFunction> functionList = new ArrayList<>();
        ComparisonTypeFactory.ComparisonFunction function = mock(ComparisonTypeFactory.ComparisonFunction.class);
        when(function.getComparisonType()).thenReturn(ComparisonType.LIKE);
        functionList.add(function);
        comparisonTypeFactory = new ComparisonTypeFactory(functionList);
        String fieldName = "testFieldName";
        String parameterName = "parameterName";
        comparisonTypeFactory.compare(fieldName, new SecureParameter(parameterName,
                MaskType.COMPLETE, ComparisonType.LIKE));
        verify(function, times(1)).compare(eq(fieldName), eq(parameterName));
    }
}