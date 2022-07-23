package com.tosan.tools.mask.starter.replace;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

/**
 * @author mina khoshnevisan
 * @since 7/11/2022
 */
public class StaticJsonReplaceHelperDeciderUTest {

    @Test
    public void testReplace_initDeciderCorrectly_callDeciderReplaceMethod() {
        JsonReplaceHelperDecider replaceHelper = mock(JsonReplaceHelperDecider.class);
        StaticJsonReplaceHelperDecider.init(replaceHelper);
        String testJson = "test";
        StaticJsonReplaceHelperDecider.replace(testJson);
        verify(replaceHelper, times(1)).replace(testJson);
    }
}