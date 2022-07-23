package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;

/**
 * @author M.khoshnevisan
 * @since 4/21/2021
 */
public interface ValueMasker {
    String ENCRYPTED = "*ENCRYPTED";
    String SEMI_ENCRYPTED = "*SEMI_ENCRYPTED:";

    MaskType getType();

    String mask(String parameterPlainValue);
}