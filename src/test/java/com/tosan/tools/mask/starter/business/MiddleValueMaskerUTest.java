package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Mostafa Abdollahi
 * @since 11/19/2023
 */
public class MiddleValueMaskerUTest {
    private final MiddleValueMasker middleValueMasker = new MiddleValueMasker();

    @Test
    public void testGetType_returnMiddle() {
        assertEquals(MaskType.MIDDLE, middleValueMasker.getType());
    }

    @Test
    public void testMask_givenNullParameter_thenReturnNull() {
        assertNull(middleValueMasker.mask(null));
    }

    @Test
    public void testMask_givenEmptyParameter_thenReturnEmptyString() {
        assertEquals("", middleValueMasker.mask(""));
        assertEquals(" ", middleValueMasker.mask(" "));
    }

    @Test
    public void testMask_givenLessThan5Parameters_thenReturnEncrypted() {
        assertEquals(ValueMasker.ENCRYPTED, middleValueMasker.mask("0"));
        assertEquals(ValueMasker.ENCRYPTED, middleValueMasker.mask("ab"));
        assertEquals(ValueMasker.ENCRYPTED, middleValueMasker.mask("ab0"));
        assertEquals("a**1", middleValueMasker.mask("ab01"));
    }

    @Test
    public void testMask_given10Characters_thenReturnCorrectMasked() {
        String originalValue = "0012345678";
        assertEquals("001****678", middleValueMasker.mask(originalValue));
        assertEquals("0012345678", originalValue);
    }

    @Test
    public void testMask_givenMobileCharacters_thenReturnCorrectMasked() {
        assertEquals("091*****789", middleValueMasker.mask("09123456789"));
    }

    @Test
    public void testMask_given70Characters_thenReturnCorrectMasked() {
        assertEquals("abcdrfghijklmnopqrstvwx*************************mnopqrstvwxyz0123456789",
                middleValueMasker.mask("abcdrfghijklmnopqrstvwxyz0123456789-abcdrfghijklmnopqrstvwxyz0123456789"));
    }
}
