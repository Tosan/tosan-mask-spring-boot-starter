package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author M.khoshnevisan
 * @since 7/3/2021
 */
public class CompleteValueMaskerUTest {

    private CompleteValueMasker completeValueMask = new CompleteValueMasker();

    @Test
    public void testGetType_returnComplete() {
        MaskType type = completeValueMask.getType();
        Assertions.assertEquals(type, MaskType.COMPLETE);
    }

    @Test
    public void testCompleteMask_nullParameter_returnNull() {
        String maskedValue = completeValueMask.mask(null);
        Assertions.assertNull(maskedValue);
    }

    @Test
    public void testCompleteMask_emptyParameter_returnEmptyString() {
        String maskedValue = completeValueMask.mask("");
        Assertions.assertEquals(maskedValue, "");
    }

    @Test
    public void testCompleteMask_normalParameter_maskString() {
        String maskedValue = completeValueMask.mask("test");
        Assertions.assertEquals(maskedValue, "*ENCRYPTED");
    }
}