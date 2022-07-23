package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author M.khoshnevisan
 * @since 7/3/2021
 */
public class SemiValueMaskerUTest {

    private SemiValueMasker semiValueMask = new SemiValueMasker();

    @Test
    public void testGetType_returnSemi() {
        MaskType type = semiValueMask.getType();
        Assertions.assertEquals(type, MaskType.SEMI);
    }

    @Test
    public void testSemiMask_nullParameter_returnNull() {
        String maskedValue = semiValueMask.mask(null);
        Assertions.assertNull(maskedValue);
    }

    @Test
    public void testSemiMask_emptyParameter_returnEmptyString() {
        String maskedValue = semiValueMask.mask("");
        Assertions.assertEquals(maskedValue, "");
    }

    @Test
    public void testSemiMask_parameterLessThan2Characters_returnMasked() {
        String maskedValue = semiValueMask.mask("i");
        Assertions.assertEquals(maskedValue, "*ENCRYPTED");
    }

    @Test
    public void testSemiMask_parameterWithMoreThan10Characters_returnMaskedParameter() {
        String maskedValue = semiValueMask.mask("54953894jjfdnb");
        Assertions.assertEquals(maskedValue, "*SEMI_ENCRYPTED:54953894j");
    }

    @Test
    public void testSemiMask_parameterWithLessThan10Characters_returnMaskedParameter() {
        String maskedValue = semiValueMask.mask("85767jg");
        Assertions.assertEquals(maskedValue, "*SEMI_ENCRYPTED:857");
    }
}