package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Hadi Zahedian
 * @since 11/13/2023
 */
public class MobileValueMaskerUTest {

    private final MobileValueMasker mobileValueMasker = new MobileValueMasker();

    @Test
    public void testGetType_returnMobile() {
        assertEquals(MaskType.MOBILE, mobileValueMasker.getType());
    }

    @Test
    public void testMask_givenNullParameter_thenReturnNull() {
        assertNull(mobileValueMasker.mask(null));
    }

    @Test
    public void testMask_givenEmptyParameter_thenReturnEmptyString() {
        assertEquals("", mobileValueMasker.mask(""));
    }

    @Test
    public void testMask_givenParameterWith09_thenReturnCorrectMaskedMobile() {
        assertEquals("0912***6789", mobileValueMasker.mask("09123456789"));
    }

    @Test
    public void testMask_givenParameterWith9_thenReturnCorrectMaskedMobile() {
        assertEquals("912***6789", mobileValueMasker.mask("9123456789"));
    }

    @Test
    public void testMask_givenParameterWithPlus98_thenReturnCorrectMaskedMobile() {
        assertEquals("+98912***6789", mobileValueMasker.mask("+989123456789"));
    }

    @Test
    public void testMask_givenParameterWithPlus980_thenReturnCorrectMaskedMobile() {
        assertEquals("+980912***6789", mobileValueMasker.mask("+9809123456789"));
    }

    @Test
    public void testMask_givenParameterWith0098_thenReturnCorrectMaskedMobile() {
        assertEquals("0098912***6789", mobileValueMasker.mask("00989123456789"));
    }

    @Test
    public void testMask_givenParameterWith00980_thenReturnCorrectMaskedMobile() {
        assertEquals("00980912***6789", mobileValueMasker.mask("009809123456789"));
    }

    @Test
    public void testMask_givenParameterWithLowerThan7Characters_thenReturnEncrypted() {
        assertEquals(ValueMasker.ENCRYPTED, mobileValueMasker.mask("091285"));
    }
}
