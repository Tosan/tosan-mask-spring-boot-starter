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
    public String mask(String mobile) {
        String maskedMobile = "";
        if (mobile != null && !mobile.isEmpty()) {
            int length = mobile.length();
            return SEMI_ENCRYPTED + mobile.substring(0, length - 7) + "***" + mobile.substring(length - 4);
        }
        return maskedMobile;
    }
}
