package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;

/**
 * @author M.khoshnevisan
 * @since 4/21/2021
 */
public class CompleteValueMasker implements ValueMasker {

    @Override
    public MaskType getType() {
        return MaskType.COMPLETE;
    }

    @Override
    public String mask(String parameterPlainValue) {
        String completeEncryptedValue = parameterPlainValue;
        if (parameterPlainValue != null && !parameterPlainValue.isEmpty()) {
            completeEncryptedValue = ENCRYPTED;
        }
        return completeEncryptedValue;
    }
}