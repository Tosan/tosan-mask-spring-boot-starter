package com.tosan.tools.mask.starter.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author mina khoshnevisan
 * @since 7/3/2022
 */
public class SecureParametersConfig {
    private final Map<String, SecureParameter> securedParametersMap;

    public SecureParametersConfig() {
        this.securedParametersMap = new HashMap<>();
    }

    public SecureParametersConfig(Set<SecureParameter> securedParameters) {
        securedParametersMap = securedParameters.stream().collect(Collectors.toMap(
                SecureParameter::getParameterName, e -> e));
    }

    public void addSecureParam(SecureParameter secureParameter) {
        this.securedParametersMap.put(secureParameter.getParameterName(), secureParameter);
    }

    public Map<String, SecureParameter> getSecuredParametersMap() {
        return securedParametersMap;
    }
}