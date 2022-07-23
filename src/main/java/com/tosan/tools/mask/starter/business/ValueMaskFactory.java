package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import com.tosan.tools.mask.starter.exception.MaskTypeNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author M.khoshnevisan
 * @since 4/21/2021
 */
public class ValueMaskFactory {

    private final Map<MaskType, ValueMasker> valueMaskCache = new HashMap<>();

    public ValueMaskFactory(List<ValueMasker> valueMaskerList) {
        for (ValueMasker valueMasker : valueMaskerList) {
            valueMaskCache.put(valueMasker.getType(), valueMasker);
        }
    }

    public ValueMasker getValueMask(MaskType maskType) {
        ValueMasker valueMasker = valueMaskCache.get(maskType);
        if (valueMasker == null) {
            throw new MaskTypeNotFoundException("no value mask defined for maskType: " + maskType);
        }
        return valueMasker;
    }
}