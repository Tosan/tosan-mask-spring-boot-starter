package com.tosan.tools.mask.starter.config;

import com.tosan.tools.mask.starter.business.enumeration.ComparisonType;
import com.tosan.tools.mask.starter.business.enumeration.MaskType;

import java.util.Objects;

/**
 * @author mina khoshnevisan
 * @since 7/13/2022
 */
public class SecureParameter {

    private final String parameterName;
    private final MaskType maskType;
    private ComparisonType comparisonType = ComparisonType.EQUALS_IGNORE_CASE;

    public SecureParameter(String parameterName, MaskType maskType) {
        this.parameterName = parameterName;
        this.maskType = maskType;
    }

    public SecureParameter(String parameterName, MaskType maskType, ComparisonType comparisonType) {
        this.parameterName = parameterName;
        this.maskType = maskType;
        this.comparisonType = comparisonType;
    }

    public String getParameterName() {
        return parameterName;
    }

    public MaskType getMaskType() {
        return maskType;
    }

    public ComparisonType getComparisonType() {
        if (comparisonType == null) {
            this.comparisonType = ComparisonType.EQUALS_IGNORE_CASE;
        }
        return comparisonType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecureParameter that = (SecureParameter) o;
        return parameterName.equals(that.parameterName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameterName);
    }
}