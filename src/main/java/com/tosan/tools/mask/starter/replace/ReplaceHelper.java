package com.tosan.tools.mask.starter.replace;

import com.tosan.tools.mask.starter.business.ComparisonTypeFactory;
import com.tosan.tools.mask.starter.business.ValueMasker;
import com.tosan.tools.mask.starter.business.ValueMaskFactory;
import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import com.tosan.tools.mask.starter.config.SecureParameter;

import java.util.Map;

/**
 * @author M.khoshnevisan
 * @since 6/23/2021
 */
public abstract class ReplaceHelper {

    protected ValueMaskFactory valueMaskFactory;
    protected ComparisonTypeFactory comparisonTypeFactory;

    protected ReplaceHelper(ValueMaskFactory valueMaskFactory, ComparisonTypeFactory comparisonTypeFactory) {
        this.valueMaskFactory = valueMaskFactory;
        this.comparisonTypeFactory = comparisonTypeFactory;
    }

    public abstract String replace(String json, Map<String, SecureParameter> securedParameterNames);

    protected MaskType checkAndGetMaskType(String fieldName, Map<String, SecureParameter> securedParameterNames) {
        if (securedParameterNames != null && !securedParameterNames.isEmpty()) {
            for (Map.Entry<String, SecureParameter> entry : securedParameterNames.entrySet()) {
                if (comparisonTypeFactory.compare(fieldName, entry.getValue())) {
                    return entry.getValue().getMaskType();
                }
            }
        }
        return null;
    }

    protected String maskValue(String value, MaskType maskType) {
        ValueMasker valueMasker = valueMaskFactory.getValueMask(maskType);
        return valueMasker.mask(value);
    }
}