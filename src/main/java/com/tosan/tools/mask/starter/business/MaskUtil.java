package com.tosan.tools.mask.starter.business;

/**
 * @author MosiDev
 * @since 21/11/2023
 */
public class MaskUtil {

    public static String middleMask(String text) {
        if (text == null || text.isBlank()) {
            return text;
        } else if (text.length() <= 3) {
            return ValueMasker.ENCRYPTED;
        } else {
            int plainLength = text.length() / 3;
            return text.substring(0, plainLength) + "*".repeat(text.length() - plainLength * 2) +
                    text.substring(text.length() - plainLength);
        }
    }

    public static String mobileMask(String mobile) {
        if (mobile != null && !mobile.isBlank()) {
            return mobile.length() < 7 ? ValueMasker.ENCRYPTED :
                    mobile.substring(0, mobile.length() - 7) + "***" +
                            mobile.substring(mobile.length() - 4);
        }
        return mobile;
    }
}
