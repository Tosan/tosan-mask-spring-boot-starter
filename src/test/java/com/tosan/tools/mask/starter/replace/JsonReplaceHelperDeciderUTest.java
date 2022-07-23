package com.tosan.tools.mask.starter.replace;

import com.tosan.tools.mask.starter.config.SecureParametersConfig;
import com.tosan.tools.mask.starter.dto.JsonReplaceResultDto;
import com.tosan.tools.mask.starter.exception.JsonConvertException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author M.khoshnevisan
 * @since 6/26/2021
 */
public class JsonReplaceHelperDeciderUTest {

    private JsonReplaceHelperDecider jsonReplaceHelperDecider;
    private JacksonReplaceHelper jacksonReplaceHelper;
    private RegexReplaceHelper regexReplaceHelper;
    private SecureParametersConfig secureParametersConfig;

    @BeforeEach
    public void setup() {
        jacksonReplaceHelper = mock(JacksonReplaceHelper.class);
        regexReplaceHelper = mock(RegexReplaceHelper.class);
        secureParametersConfig = mock(SecureParametersConfig.class);
        jsonReplaceHelperDecider = new JsonReplaceHelperDecider(jacksonReplaceHelper, regexReplaceHelper, secureParametersConfig);
    }

    @Test
    public void testReplace_validJson_replaceWithJacksonReplacer() {
        String testJson = "{test:success}";
        when(jacksonReplaceHelper.replace(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(testJson);
        String result = jsonReplaceHelperDecider.replace("testJson");
        assertEquals(result, testJson);
        verify(jacksonReplaceHelper, times(1)).replace(ArgumentMatchers.anyString(), ArgumentMatchers.any());
        verify(regexReplaceHelper, times(0)).replace(ArgumentMatchers.anyString(), ArgumentMatchers.any());
    }

    @Test
    public void testReplace_invalidJson_replaceWithRegexReplacer() {
        String testJson = "{test:success}";
        when(jacksonReplaceHelper.replace(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenThrow(JsonConvertException.class);
        when(regexReplaceHelper.replace(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(testJson);
        String result = jsonReplaceHelperDecider.replace("testJson");
        assertEquals(result, testJson);
        verify(jacksonReplaceHelper, times(1)).replace(ArgumentMatchers.anyString(), ArgumentMatchers.any());
        verify(regexReplaceHelper, times(1)).replace(ArgumentMatchers.anyString(), ArgumentMatchers.any());
    }

    @Test
    public void testReplaceByJacksonHelper_normalCallWithNoException_callJacksonReplaceHelper() {
        String json = "test";
        jsonReplaceHelperDecider.replaceByJacksonHelper(json);
        verify(jacksonReplaceHelper, times(1)).replace(eq(json), any());
    }

    @Test
    public void testReplaceByJacksonHelper_jsonConvertExceptionHappen_returnOriginalString() {
        String json = "test";
        when(jacksonReplaceHelper.replace(eq(json), any())).thenThrow(new JsonConvertException("test"));
        String result = jsonReplaceHelperDecider.replaceByJacksonHelper(json);
        assertEquals(json, result);
    }

    @Test
    public void testCheckJsonAndReplace_validJson_returnTrueIsJson() {
        String json = "test";
        String maskedJson = "ENCRYPTED";
        when(jacksonReplaceHelper.replace(eq(json), any())).thenReturn(maskedJson);
        JsonReplaceResultDto jsonReplaceResultDto = jsonReplaceHelperDecider.checkJsonAndReplace(json);
        assertTrue(jsonReplaceResultDto.isJson());
        assertEquals(maskedJson, jsonReplaceResultDto.getReplacedJson());
    }

    @Test
    public void testCheckJsonAndReplace_invalidJson_returnFalseIsJson() {
        String json = "test";
        when(jacksonReplaceHelper.replace(eq(json), any())).thenThrow(new JsonConvertException("test"));
        JsonReplaceResultDto jsonReplaceResultDto = jsonReplaceHelperDecider.checkJsonAndReplace(json);
        assertFalse(jsonReplaceResultDto.isJson());
        assertNull(jsonReplaceResultDto.getReplacedJson());
    }

    @Test
    public void testReplaceByRegexHelper_normalCall_callRegexHelper() {
        String json = "test";
        jsonReplaceHelperDecider.replaceByRegexHelper(json);
        verify(regexReplaceHelper, times(1)).replace(eq(json), any());
    }

    @Test
    public void testReplace_normalCall_callReplaceHelper() {
        String fieldName = "test field name";
        String fieldValue = "testFieldName";
        jsonReplaceHelperDecider.replace(fieldName, fieldValue);
        verify(jacksonReplaceHelper, times(1)).replace(eq(fieldName), eq(fieldValue), any());
    }
}