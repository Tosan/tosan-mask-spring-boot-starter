package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author M.khoshnevisan
 * @since 7/3/2021
 */
public class RightValueMaskerUTest {

    private RightValueMasker rightValueMask = new RightValueMasker();

    @Test
    public void testGetType_returnRight() {
        MaskType type = rightValueMask.getType();
        Assertions.assertEquals(type, MaskType.RIGHT);
    }

    @Test
    public void testRightMask_nullParameter_returnNull() {
        String maskedValue = rightValueMask.mask(null);
        Assertions.assertNull(maskedValue);
    }

    @Test
    public void testRightMask_emptyParameter_returnEmptyString() {
        String maskedValue = rightValueMask.mask("");
        Assertions.assertEquals(maskedValue, "");
    }

    @Test
    public void testRightMask_parameterWithOneCharacter_returnSameParameter() {
        final String parameterPlainValue = "y";
        String maskedValue = rightValueMask.mask(parameterPlainValue);
        Assertions.assertEquals(maskedValue, parameterPlainValue);
    }

    @Test
    public void testRightMask_normalParameter_returnCorrectMaskedParameter() {
        final String parameterPlainValue = "testValue";
        String maskedValue = rightValueMask.mask(parameterPlainValue);
        Assertions.assertEquals(maskedValue, "*SEMI_ENCRYPTED:test");
    }
}