package com.tosan.tools.mask.starter.configuration;

import com.tosan.tools.mask.starter.config.SecureParameter;
import com.tosan.tools.mask.starter.config.SecureParametersConfig;
import com.tosan.tools.mask.starter.business.*;
import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import com.tosan.tools.mask.starter.replace.JacksonReplaceHelper;
import com.tosan.tools.mask.starter.replace.JsonReplaceHelperDecider;
import com.tosan.tools.mask.starter.replace.RegexReplaceHelper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * @author mina khoshnevisan
 * @since 7/11/2022
 */
public class MaskBeanConfigurationUTest {

    private final MaskBeanConfiguration maskBeanConfiguration = new MaskBeanConfiguration();

    @Test
    public void testSecureParametersConfig_defaultConfig_returnCorrectConfig() {
        SecureParametersConfig secureParametersConfig = maskBeanConfiguration.secureParametersConfig();
        Map<String, SecureParameter> securedParameters = secureParametersConfig.getSecuredParametersMap();
        assertNotNull(securedParameters);
        assertEquals(6, securedParameters.size());
        assertEquals(MaskType.COMPLETE, securedParameters.get("password").getMaskType());
        assertEquals(MaskType.PAN, securedParameters.get("pan").getMaskType());
        assertEquals(MaskType.COMPLETE, securedParameters.get("pin").getMaskType());
        assertEquals(MaskType.SEMI, securedParameters.get("cvv2").getMaskType());
        assertEquals(MaskType.SEMI, securedParameters.get("expDate").getMaskType());
        assertEquals(MaskType.SEMI, securedParameters.get("username").getMaskType());
    }

    @Test
    public void testReplaceHelperDecider_defaultConfig_createCorrectConfig() {
        JsonReplaceHelperDecider jsonReplaceHelperDecider = maskBeanConfiguration.jsonReplaceHelperDecider(
                mock(JacksonReplaceHelper.class), mock(RegexReplaceHelper.class), mock(SecureParametersConfig.class));
        assertNotNull(jsonReplaceHelperDecider);
    }

    @Test
    public void testJacksonReplaceHelper_defaultConfig_createCorrectConfig() {
        JacksonReplaceHelper jacksonReplaceHelper = maskBeanConfiguration.jacksonReplaceHelper(mock(ValueMaskFactory.class));
        assertNotNull(jacksonReplaceHelper);
    }

    @Test
    public void testRegexReplaceHelper_defaultConfig_createCorrectConfig() {
        RegexReplaceHelper regexReplaceHelper = maskBeanConfiguration.regexReplaceHelper(mock(ValueMaskFactory.class));
        assertNotNull(regexReplaceHelper);
    }

    @Test
    public void testValueMaskFactory_emptyList_createCorrectConfig() {
        ValueMaskFactory valueMaskFactory = maskBeanConfiguration.valueMaskFactory(new ArrayList<>());
        assertNotNull(valueMaskFactory);
    }

    @Test
    public void testCompleteValueMask_defaultConfig_createCorrectConfig() {
        CompleteValueMasker valueMask = maskBeanConfiguration.completeValueMask();
        assertNotNull(valueMask);
    }

    @Test
    public void testLeftValueMask_defaultConfig_createCorrectConfig() {
        LeftValueMasker valueMask = maskBeanConfiguration.leftValueMask();
        assertNotNull(valueMask);
    }

    @Test
    public void testPanValueMask_defaultConfig_createCorrectConfig() {
        PanValueMasker valueMask = maskBeanConfiguration.panValueMask();
        assertNotNull(valueMask);
    }

    @Test
    public void testRightValueMask_defaultConfig_createCorrectConfig() {
        RightValueMasker valueMask = maskBeanConfiguration.rightValueMask();
        assertNotNull(valueMask);
    }

    @Test
    public void testSemiValueMask_defaultConfig_createCorrectConfig() {
        SemiValueMasker valueMask = maskBeanConfiguration.semiValueMask();
        assertNotNull(valueMask);
    }
}