package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;

/**
 * @author M.khoshnevisan
 * @since 4/21/2021
 */
public class PanValueMasker implements ValueMasker {

    @Override
    public MaskType getType() {
        return MaskType.PAN;
    }

    @Override
    public String mask(String originalValue) {
        if (originalValue != null && !originalValue.isBlank()) {
            if (originalValue.length() == 16) {
                return originalValue.substring(0, 6).concat("******").concat(originalValue.substring(12, 16));
            } else if (originalValue.length() == 19) {
                return originalValue.substring(0, 6).concat("*********").concat(originalValue.substring(15, 19));
            } else {
                return MaskUtil.middleMask(originalValue);
            }
        }
        return originalValue;
    }
}