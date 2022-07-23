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
    public String mask(String panPlainValue) {
        String encryptedPan = "";
        if (panPlainValue != null && !panPlainValue.isEmpty()) {
            if (panPlainValue.length() == 16) {
                encryptedPan = SEMI_ENCRYPTED.concat(panPlainValue.substring(0, 6)).concat("******").concat(panPlainValue.substring(12, 16));
            } else if (panPlainValue.length() == 19) {
                encryptedPan = SEMI_ENCRYPTED.concat(panPlainValue.substring(0, 6)).concat("*********").concat(panPlainValue.substring(15, 19));
            } else {
                if (panPlainValue.length() >= 2) {
                    encryptedPan = SEMI_ENCRYPTED.concat(panPlainValue.substring(0, panPlainValue.length() / 2));
                } else {
                    encryptedPan = ENCRYPTED;
                }
            }
        }
        return encryptedPan;
    }
}