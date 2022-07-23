package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;

/**
 * @author M.khoshnevisan
 * @since 4/21/2021
 */
public class RightValueMasker implements ValueMasker {

    @Override
    public MaskType getType() {
        return MaskType.RIGHT;
    }

    @Override
    public String mask(String parameterPlainValue) {
        String rightEncryptedValue = parameterPlainValue;
        if (parameterPlainValue != null && parameterPlainValue.length() >= 2) {
            rightEncryptedValue = SEMI_ENCRYPTED.concat(parameterPlainValue.substring(0, parameterPlainValue.length() / 2));
        }
        return rightEncryptedValue;
    }
}