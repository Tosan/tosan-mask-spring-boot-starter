package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;

/**
 * @author M.khoshnevisan
 * @since 4/21/2021
 */
public class LeftValueMasker implements ValueMasker {

    @Override
    public MaskType getType() {
        return MaskType.LEFT;
    }

    @Override
    public String mask(String parameterPlainValue) {
        String leftEncryptedValue = parameterPlainValue;
        if (parameterPlainValue != null && parameterPlainValue.length() >= 2) {
            leftEncryptedValue = SEMI_ENCRYPTED.concat(parameterPlainValue.substring(parameterPlainValue.length() / 2));
        }
        return leftEncryptedValue;
    }
}