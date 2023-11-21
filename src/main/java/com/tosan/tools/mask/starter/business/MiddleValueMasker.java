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
        return MaskUtil.middleMask(originalValue);
    }
}
