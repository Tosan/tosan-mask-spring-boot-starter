package com.tosan.tools.mask.integration.business;

import com.tosan.tools.mask.starter.business.enumeration.ComparisonType;
import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import com.tosan.tools.mask.starter.config.SecureParameter;
import com.tosan.tools.mask.starter.config.SecureParametersConfig;
import com.tosan.tools.mask.starter.configuration.MaskBeanConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashSet;
import java.util.Set;

/**
 * @author mina khoshnevisan
 * @since 7/6/2022
 */
@Configuration
public class AppConfiguration {

    @Bean
    @Primary
    public SecureParametersConfig secureParametersConfig() {
        Set<SecureParameter> securedParameters = new HashSet<SecureParameter>() {
            {
                add(new SecureParameter("pan", UserMaskType.PAN));
                add(new SecureParameter("testField", UserMaskType.TEST_MASK_TYPE));
            }
        };
        return new SecureParametersConfig(securedParameters);
    }

    @Bean
    public SecureParametersConfig securedParametersWithDefault() {
        Set<SecureParameter> securedParameters = MaskBeanConfiguration.SECURED_PARAMETERS;
        securedParameters.add(new SecureParameter("testField", UserMaskType.TEST_MASK_TYPE));
        return new SecureParametersConfig(securedParameters);
    }

    @Bean
    public SecureParametersConfig securedParametersWithComparisonType() {
        Set<SecureParameter> securedParameters = new HashSet<>();
        securedParameters.add(new SecureParameter("pan", MaskType.PAN, ComparisonType.RIGHT_LIKE));
        return new SecureParametersConfig(securedParameters);
    }
}