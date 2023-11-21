package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author M.khoshnevisan
 * @since 7/3/2021
 */
public class PanValueMaskerUTest {
    private final PanValueMasker panValueMask = new PanValueMasker();

    @Test
    public void testGetType_returnPan() {
        MaskType type = panValueMask.getType();
        assertEquals(type, MaskType.PAN);
    }

    @Test
    public void testPantMask_nullParameter_returnNull() {
        String maskedValue = panValueMask.mask(null);
        assertNull(maskedValue);
    }

    @Test
    public void testPanMask_emptyParameter_returnEmptyString() {
        String maskedValue = panValueMask.mask("");
        assertEquals(maskedValue, "");
    }

    @Test
    public void testPanMask_parameterLessThanTwoCharacters_returnCorrectMaskedString() {
        String maskedValue = panValueMask.mask("5");
        assertEquals(maskedValue, "*ENCRYPTED");
    }

    @Test
    public void testPanMask_with16CharacterParameter_returnCorrectMaskedString() {
        String maskedValue = panValueMask.mask("5876546533454650");
        assertEquals(maskedValue, "587654******4650");
    }

    @Test
    public void testPanMask_with19CharacterParameter_returnCorrectMaskedString() {
        String maskedValue = panValueMask.mask("5876546533454650876");
        assertEquals(maskedValue, "587654*********0876");
    }

    @Test
    public void testPanMask_withLessThan16CharacterParameter_returnCorrectMaskedString() {
        String maskedValue = panValueMask.mask("587654653345465");
        assertEquals(maskedValue, "58765*****45465");
    }
}