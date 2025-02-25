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
        return MaskUtil.mobileMask(originalValue);
    }
}
