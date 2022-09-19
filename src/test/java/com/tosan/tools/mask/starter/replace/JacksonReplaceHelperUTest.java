package com.tosan.tools.mask.starter.replace;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tosan.tools.mask.starter.business.ComparisonTypeFactory;
import com.tosan.tools.mask.starter.business.ValueMasker;
import com.tosan.tools.mask.starter.business.ValueMaskFactory;
import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import com.tosan.tools.mask.starter.config.SecureParameter;
import com.tosan.tools.mask.starter.exception.JsonConvertException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author M.khoshnevisan
 * @since 6/23/2021
 */
public class JacksonReplaceHelperUTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    private JacksonReplaceHelper replacerHelper;
    private Map<String, SecureParameter> securedParameterMap;
    private ValueMaskFactory valueMaskFactory;
    private ComparisonTypeFactory comparisonTypeFactory;
    private static final String MASKED_VALUE = "MASKED_VALUE";

    @BeforeEach
    public void setup() {
        System.out.println("setup before JacksonReplaceHelperUTest");
        valueMaskFactory = Mockito.mock(ValueMaskFactory.class);
        comparisonTypeFactory = mock(ComparisonTypeFactory.class);
        replacerHelper = new JacksonReplaceHelper(valueMaskFactory, comparisonTypeFactory);
        securedParameterMap = new HashMap<>();
        securedParameterMap.put("pan", new SecureParameter("pan", MaskType.PAN));
        securedParameterMap.put("password", new SecureParameter("password", MaskType.COMPLETE));
        ValueMasker valueMasker = Mockito.mock(ValueMasker.class);
        Mockito.when(valueMasker.mask(ArgumentMatchers.anyString())).thenReturn(MASKED_VALUE);
        Mockito.when(valueMaskFactory.getValueMask(ArgumentMatchers.any())).thenReturn(valueMasker);
        when(comparisonTypeFactory.compare(eq("password"), any())).thenReturn(true);
    }

    @Test
    public void testReplace_noParameterToReplace_noChangeToInput() throws JsonProcessingException {
        String input = "{" +
                "\"id\" : 8767," +
                "\"name\":\"mina\"," +
                "\"person\":{" +
                "\"name\":\"mina\"," +
                "\"array\":[" +
                "{\"family\":\"kh\"}" +
                "]" +
                "}" +
                "}";
        String replaced = replacerHelper.replace(input, securedParameterMap);
        verifyResult(input, replaced);
    }

    @Test
    public void testReplace_passwordParameter_maskPassword() throws JsonProcessingException {
        String input = "{" +
                "\"id\" : 8767," +
                "\"password\":\"8574945\"," +
                "\"person\":{" +
                "\"name\":\"mina\"," +
                "\"array\":[" +
                "{\"family\":\"kh\", \"password\":\"kjfksdf4\"}," +
                "{\"password\":\"983485\"}" +
                "]" +
                "}" +
                "}";
        String expected = "{" +
                "\"id\" : 8767," +
                "\"password\":\"MASKED_VALUE\"," +
                "\"person\":{" +
                "\"name\":\"mina\"," +
                "\"array\":[" +
                "{\"family\":\"kh\", \"password\":\"MASKED_VALUE\"}," +
                "{\"password\":\"MASKED_VALUE\"}" +
                "]" +
                "}" +
                "}";
        String replaced = replacerHelper.replace(input, securedParameterMap);
        verifyResult(expected, replaced);
    }

    @Test
    public void testReplace_panParameter_maskPan() throws JsonProcessingException {
        String input = "{" +
                "\"id\" : 8767," +
                "\"name\":\"8574945\"," +
                "\"person\":{" +
                "\"pan\":\"48930000498883\"," +
                "\"array\":[" +
                "{\"pan\":\"48930000498883\", \"password\":\"kjfksdf4\"}," +
                "{\"password\":\"983485\"}" +
                "]" +
                "}" +
                "}";
        String expected = "{" +
                "\"id\" : 8767," +
                "\"name\":\"8574945\"," +
                "\"person\":{" +
                "\"pan\":\"MASKED_VALUE\"," +
                "\"array\":[" +
                "{\"pan\":\"MASKED_VALUE\", \"password\":\"MASKED_VALUE\"}," +
                "{\"password\":\"MASKED_VALUE\"}" +
                "]" +
                "}" +
                "}";
        when(comparisonTypeFactory.compare(eq("pan"), any())).thenReturn(true);
        String replaced = replacerHelper.replace(input, securedParameterMap);
        verifyResult(expected, replaced);
    }

    @Test
    public void testReplace_listOfPan_maskAllElementsInList() throws JsonProcessingException {
        String input = "{" +
                "\"id\" : 8767," +
                "\"name\":\"8574945\"," +
                "\"person\":{" +
                "\"panList\":[\"4893000049888387\",\"5093000049888345\",\"5087000049888390\"]" +
                "}" +
                "}";
        String expected = "{" +
                "\"id\" : 8767," +
                "\"name\":\"8574945\"," +
                "\"person\":{" +
                "\"panList\":[\"MASKED_VALUE\",\"MASKED_VALUE\",\"MASKED_VALUE\"]" +
                "}" +
                "}";

        securedParameterMap.put("panList", new SecureParameter("panList", MaskType.PAN));
        when(comparisonTypeFactory.compare(eq("panList"), any())).thenReturn(true);
        String replaced = replacerHelper.replace(input, securedParameterMap);
        verifyResult(expected, replaced);
    }

    @Test
    public void testReplace_nullNode_replaceWithNoException() throws JsonProcessingException {
        String input = "{" +
                "\"id\" : 8767," +
                "\"name\":null," +
                "\"person\":{" +
                "\"panList\":[\"4893000049888387\",\"5093000049888345\",\"5087000049888390\"]" +
                "}" +
                "}";
        String replaced = replacerHelper.replace(input, securedParameterMap);
        verifyResult(input, replaced);
    }

    @Test
    public void testReplace_emptyObjectNode_replaceWithNoException() throws JsonProcessingException {
        String input = "{" +
                "\"id\" : 8767," +
                "\"name\":null," +
                "\"person\":{" +
                "}" +
                "}";
        System.out.println("replacerHelper************* = " + replacerHelper);
        String replaced = replacerHelper.replace(input, securedParameterMap);
        verifyResult(input, replaced);
    }

    @Test
    public void testReplace_emptyArray_replaceWithNoException() throws JsonProcessingException {
        String input = "{" +
                "\"id\" : 8767," +
                "\"name\":null," +
                "\"person\":[" +
                "]" +
                "}";
        String replaced = replacerHelper.replace(input, securedParameterMap);
        verifyResult(input, replaced);
    }

    @Test
    public void testReplace_nullElementInArray_replaceWithNoException() throws JsonProcessingException {
        String input = "{" +
                "\"id\" : 8767," +
                "\"name\":null," +
                "\"person\":[null, null" +
                "]" +
                "}";
        String replaced = replacerHelper.replace(input, securedParameterMap);
        verifyResult(input, replaced);
    }

    @Test()
    public void testReplace_invalidJson_throwInvalidJsonException() {
        String input = "{" +
                "\"id\" : 8767," +
                "\"name\":null," +
                "\"person\":[null, null" +
                "}";
        assertThrows(JsonConvertException.class, () -> replacerHelper.replace(input, securedParameterMap));
    }

    public void verifyResult(String input, String replaced) throws JsonProcessingException {
        JsonNode actual = objectMapper.readTree(replaced);
        JsonNode expected = objectMapper.readTree(input);
        assertEquals(actual, expected);
    }
}