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
        if (mobile != null && !mobile.isEmpty()) {
            return mobile.length() < 7 ? SEMI_ENCRYPTED + mobile : SEMI_ENCRYPTED + mobile.substring(0,
                    mobile.length() - 7) + "***" + mobile.substring(mobile.length() - 4);
        }
        return "";
    }
}
