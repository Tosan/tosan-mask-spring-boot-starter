package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;

/**
 * @author Mostafa Abdollahi
 * @since 11/19/2023
 */
public class MiddleValueMasker implements ValueMasker {

    @Override
    public MaskType getType() {
        return MaskType.MIDDLE;
    }

    @Override
    public String mask(String originalValue) {
        if (originalValue == null || originalValue.isBlank()) {
            return originalValue;
        } else if (originalValue.length() <= 3) {
            return ENCRYPTED;
        } else {
            int plainLength = originalValue.length() / 3;
            return originalValue.substring(0, plainLength) + "*".repeat(originalValue.length() - plainLength * 2) +
                    originalValue.substring(originalValue.length() - plainLength);
        }
    }
}
