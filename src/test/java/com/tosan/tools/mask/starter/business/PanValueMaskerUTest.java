package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author M.khoshnevisan
 * @since 7/3/2021
 */
public class PanValueMaskerUTest {

    private PanValueMasker panValueMask = new PanValueMasker();

    @Test
    public void testGetType_returnPan() {
        MaskType type = panValueMask.getType();
        Assertions.assertEquals(type, MaskType.PAN);
    }

    @Test
    public void testPantMask_nullParameter_returnEmptyString() {
        String maskedValue = panValueMask.mask(null);
        Assertions.assertEquals(maskedValue, "");
    }

    @Test
    public void testPanMask_emptyParameter_returnEmptyString() {
        String maskedValue = panValueMask.mask("");
        Assertions.assertEquals(maskedValue, "");
    }

    @Test
    public void testPanMask_parameterLessThanTwoCharacters_returnCorrectMaskedString() {
        String maskedValue = panValueMask.mask("5");
        Assertions.assertEquals(maskedValue, "*ENCRYPTED");
    }

    @Test
    public void testPanMask_with16CharacterParameter_returnCorrectMaskedString() {
        String maskedValue = panValueMask.mask("5876546533454650");
        Assertions.assertEquals(maskedValue, "*SEMI_ENCRYPTED:587654******4650");
    }

    @Test
    public void testPanMask_with19CharacterParameter_returnCorrectMaskedString() {
        String maskedValue = panValueMask.mask("5876546533454650876");
        Assertions.assertEquals(maskedValue, "*SEMI_ENCRYPTED:587654*********0876");
    }

    @Test
    public void testPanMask_withLessThan16CharacterParameter_returnCorrectMaskedString() {
        String maskedValue = panValueMask.mask("587654653345465");
        Assertions.assertEquals(maskedValue, "*SEMI_ENCRYPTED:5876546");
    }
}