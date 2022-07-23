package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;

/**
 * @author M.khoshnevisan
 * @since 4/21/2021
 */
public class SemiValueMasker implements ValueMasker {

    @Override
    public MaskType getType() {
        return MaskType.SEMI;
    }

    @Override
    public String mask(String parameterPlainValue) {
        String semiEncryptedValue = parameterPlainValue;
        if (parameterPlainValue != null && !parameterPlainValue.isEmpty()) {
            if (parameterPlainValue.length() > 2) {
                semiEncryptedValue = SEMI_ENCRYPTED.concat(parameterPlainValue.length() > 10 ?
                        parameterPlainValue.substring(0, parameterPlainValue.length() - 5) :
                        parameterPlainValue.substring(0, parameterPlainValue.length() / 2));
            } else {
                semiEncryptedValue = ENCRYPTED;
            }
        }
        return semiEncryptedValue;
    }
}