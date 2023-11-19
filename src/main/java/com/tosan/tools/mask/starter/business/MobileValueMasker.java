package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;

/**
 * @author Hadi Zahedian
 * @since 11/13/2023
 */
public class MobileValueMasker implements ValueMasker {

    @Override
    public MaskType getType() {
        return MaskType.MOBILE;
    }

    @Override
    public String mask(String originalValue) {
        if (originalValue != null && !originalValue.isBlank()) {
            return originalValue.length() < 7 ? ENCRYPTED :
                    originalValue.substring(0, originalValue.length() - 7) + "***" +
                            originalValue.substring(originalValue.length() - 4);
        }
        return originalValue;
    }
}
