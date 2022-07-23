package com.tosan.tools.mask.starter.configuration;

import com.tosan.tools.mask.starter.business.*;
import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import com.tosan.tools.mask.starter.config.SecureParameter;
import com.tosan.tools.mask.starter.config.SecureParametersConfig;
import com.tosan.tools.mask.starter.replace.JacksonReplaceHelper;
import com.tosan.tools.mask.starter.replace.JsonReplaceHelperDecider;
import com.tosan.tools.mask.starter.replace.RegexReplaceHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author mina khoshnevisan
 * @since 6/20/2022
 */
@Configuration
public class MaskBeanConfiguration {

    public static final Set<SecureParameter> SECURED_PARAMETERS = new HashSet<SecureParameter>() {
        {
            add(new SecureParameter("password", MaskType.COMPLETE));
            add(new SecureParameter("pan", MaskType.PAN));
            add(new SecureParameter("pin", MaskType.COMPLETE));
            add(new SecureParameter("cvv2", MaskType.SEMI));
            add(new SecureParameter("expDate", MaskType.SEMI));
            add(new SecureParameter("username", MaskType.SEMI));
        }
    };

    @Bean
    @ConditionalOnMissingBean
    public SecureParametersConfig secureParametersConfig() {
        return new SecureParametersConfig(SECURED_PARAMETERS);
    }

    @Bean
    @ConditionalOnMissingBean
    public JsonReplaceHelperDecider jsonReplaceHelperDecider(JacksonReplaceHelper jacksonReplaceHelper,
                                                             RegexReplaceHelper regexReplaceHelper,
                                                             SecureParametersConfig secureParametersConfig) {
        return new JsonReplaceHelperDecider(jacksonReplaceHelper, regexReplaceHelper, secureParametersConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    public JacksonReplaceHelper jacksonReplaceHelper(ValueMaskFactory valueMaskFactory) {
        return new JacksonReplaceHelper(valueMaskFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public RegexReplaceHelper regexReplaceHelper(ValueMaskFactory valueMaskFactory) {
        return new RegexReplaceHelper(valueMaskFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public ValueMaskFactory valueMaskFactory(List<ValueMasker> valueMaskerList) {
        return new ValueMaskFactory(valueMaskerList);
    }

    @Bean
    @ConditionalOnMissingBean
    public CompleteValueMasker completeValueMask() {
        return new CompleteValueMasker();
    }

    @Bean
    @ConditionalOnMissingBean
    public LeftValueMasker leftValueMask() {
        return new LeftValueMasker();
    }

    @Bean
    @ConditionalOnMissingBean
    public PanValueMasker panValueMask() {
        return new PanValueMasker();
    }

    @Bean
    @ConditionalOnMissingBean
    public RightValueMasker rightValueMask() {
        return new RightValueMasker();
    }

    @Bean
    @ConditionalOnMissingBean
    public SemiValueMasker semiValueMask() {
        return new SemiValueMasker();
    }
}