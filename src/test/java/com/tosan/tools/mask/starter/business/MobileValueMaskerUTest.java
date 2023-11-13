package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testMask_givenNullParameter_thenReturnEmptyString() {
        assertEquals("", mobileValueMasker.mask(null));
    }

    @Test
    public void testMask_givenEmptyParameter_thenReturnEmptyString() {
        assertEquals("", mobileValueMasker.mask(""));
    }

    @Test
    public void testMask_givenParameterWith09_thenReturnCorrectMaskedMobile() {
        assertEquals("*SEMI_ENCRYPTED:0911***4506", mobileValueMasker.mask("09118534506"));
    }

    @Test
    public void testMask_givenParameterWith9_thenReturnCorrectMaskedMobile() {
        assertEquals("*SEMI_ENCRYPTED:911***4506", mobileValueMasker.mask("9118534506"));
    }

    @Test
    public void testMask_givenParameterWithPlus98_thenReturnCorrectMaskedMobile() {
        assertEquals("*SEMI_ENCRYPTED:+98911***4506", mobileValueMasker.mask("+989118534506"));
    }

    @Test
    public void testMask_givenParameterWithPlus980_thenReturnCorrectMaskedMobile() {
        assertEquals("*SEMI_ENCRYPTED:+980911***4506", mobileValueMasker.mask("+9809118534506"));
    }

    @Test
    public void testMask_givenParameterWith0098_thenReturnCorrectMaskedMobile() {
        assertEquals("*SEMI_ENCRYPTED:0098911***4506", mobileValueMasker.mask("00989118534506"));
    }

    @Test
    public void testMask_givenParameterWith00980_thenReturnCorrectMaskedMobile() {
        assertEquals("*SEMI_ENCRYPTED:00980911***4506", mobileValueMasker.mask("009809118534506"));
    }
}
