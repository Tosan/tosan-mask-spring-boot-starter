package com.tosan.tools.mask.starter.config;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mina khoshnevisan
 * @since 7/11/2022
 */
public class SecureParametersConfigUTest {

    @Test
    public void testSecureParametersConfig_callConstructor_initParametersCorrectly() {
        Set<SecureParameter> securedParameters = new HashSet<>();
        SecureParameter secureParameter = new SecureParameter("test", MaskType.COMPLETE);
        securedParameters.add(secureParameter);
        SecureParametersConfig secureParametersConfig = new SecureParametersConfig(securedParameters);
        Map<String, SecureParameter> result = secureParametersConfig.getSecuredParametersMap();
        assertEquals(secureParameter, result.get("test"));
    }

    @Test
    public void testSecureParametersConfig_addParametersCorrectly() {
        Set<SecureParameter> securedParameters = new HashSet<>();
        SecureParameter secureParameter = new SecureParameter("test", MaskType.COMPLETE);
        securedParameters.add(secureParameter);
        SecureParametersConfig secureParametersConfig = new SecureParametersConfig(securedParameters);
        SecureParameter secureParameter2 = new SecureParameter("param", MaskType.SEMI);
        secureParametersConfig.addSecureParam(secureParameter2);

        Map<String, SecureParameter> result = secureParametersConfig.getSecuredParametersMap();
        assertEquals(secureParameter, result.get("test"));
        assertEquals(secureParameter2, result.get("param"));
    }
}