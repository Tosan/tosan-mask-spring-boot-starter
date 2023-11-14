package com.tosan.tools.mask.starter.configuration;

import com.tosan.tools.mask.starter.business.*;
import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import com.tosan.tools.mask.starter.config.SecureParameter;
import com.tosan.tools.mask.starter.config.SecureParametersConfig;
import com.tosan.tools.mask.starter.replace.JacksonReplaceHelper;
import com.tosan.tools.mask.starter.replace.JsonReplaceHelperDecider;
import com.tosan.tools.mask.starter.replace.RegexReplaceHelper;
import org.springframework.boot.autoconfigure.AutoConfiguration;
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
@AutoConfiguration
public class MaskBeanConfiguration {

    public static final Set<SecureParameter> SECURED_PARAMETERS = new HashSet<SecureParameter>() {
        {
            add(new SecureParameter("password", MaskType.COMPLETE));
            add(new SecureParameter("pan", MaskType.PAN));
            add(new SecureParameter("pin", MaskType.COMPLETE));
            add(new SecureParameter("cvv2", MaskType.SEMI));
            add(new SecureParameter("expDate", MaskType.SEMI));
            add(new SecureParameter("username", MaskType.SEMI));
            add(new SecureParameter("mobile", MaskType.MOBILE));
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
    public JacksonReplaceHelper jacksonReplaceHelper(ValueMaskFactory valueMaskFactory, ComparisonTypeFactory comparisonTypeFactory) {
        return new JacksonReplaceHelper(valueMaskFactory, comparisonTypeFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public RegexReplaceHelper regexReplaceHelper(ValueMaskFactory valueMaskFactory, ComparisonTypeFactory comparisonTypeFactory) {
        return new RegexReplaceHelper(valueMaskFactory, comparisonTypeFactory);
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

    @Bean
    @ConditionalOnMissingBean
    public MobileValueMasker mobileValueMasker() {
        return new MobileValueMasker();
    }

    @Bean
    public ComparisonTypeFactory comparisonTypeFactory(List<ComparisonTypeFactory.ComparisonFunction> comparisonFunctionList) {
        return new ComparisonTypeFactory(comparisonFunctionList);
    }

    @Bean
    public ComparisonTypeFactory.EqualsIgnoreCaseComparisonFunction equalIgnoreCaseComparisonFunction() {
        return new ComparisonTypeFactory.EqualsIgnoreCaseComparisonFunction();
    }

    @Bean
    public ComparisonTypeFactory.EqualsComparisonFunction equalComparisonFunction() {
        return new ComparisonTypeFactory.EqualsComparisonFunction();
    }

    @Bean
    public ComparisonTypeFactory.LikeComparisonFunction likeComparisonFunction() {
        return new ComparisonTypeFactory.LikeComparisonFunction();
    }

    @Bean
    public ComparisonTypeFactory.LeftLikeComparisonFunction leftLikeComparisonFunction() {
        return new ComparisonTypeFactory.LeftLikeComparisonFunction();
    }

    @Bean
    public ComparisonTypeFactory.RightLikeComparisonFunction rightLikeComparisonFunction() {
        return new ComparisonTypeFactory.RightLikeComparisonFunction();
    }
}