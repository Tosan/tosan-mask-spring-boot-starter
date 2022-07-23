package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author M.khoshnevisan
 * @since 7/3/2021
 */
public class LeftValueMaskerUTest {

    private LeftValueMasker leftValueMask = new LeftValueMasker();

    @Test
    public void testGetType_returnLeft() {
        MaskType type = leftValueMask.getType();
        Assertions.assertEquals(type, MaskType.LEFT);
    }

    @Test
    public void testLeftMask_nullParameter_returnNull() {
        String maskedValue = leftValueMask.mask(null);
        Assertions.assertNull(maskedValue);
    }

    @Test
    public void testLeftMask_emptyParameter_returnEmptyString() {
        String maskedValue = leftValueMask.mask("");
        Assertions.assertEquals(maskedValue, "");
    }

    @Test
    public void testLeftMask_parameterWithOneCharacter_returnSameString() {
        String plainValue = "t";
        String maskedValue = leftValueMask.mask(plainValue);
        Assertions.assertEquals(maskedValue, plainValue);
    }

    @Test
    public void testLeftMask_normalParameters_maskString() {
        String plainValue = "testValue";
        String maskedValue = leftValueMask.mask(plainValue);
        Assertions.assertEquals(maskedValue, "*SEMI_ENCRYPTED:Value");
    }
}