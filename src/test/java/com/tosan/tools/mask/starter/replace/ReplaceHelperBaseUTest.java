package com.tosan.tools.mask.starter.replace;

import com.tosan.tools.mask.starter.business.ValueMasker;
import com.tosan.tools.mask.starter.business.ValueMaskFactory;
import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import com.tosan.tools.mask.starter.config.SecureParameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author M.khoshnevisan
 * @since 6/26/2021
 */
public class ReplaceHelperBaseUTest {

    private ReplaceHelper replaceHelper;
    private ValueMaskFactory valueMaskFactory;

    @BeforeEach
    public void setup() {
        valueMaskFactory = Mockito.mock(ValueMaskFactory.class);
        replaceHelper = new ReplaceHelper(valueMaskFactory) {
            @Override
            public String replace(String json, Map<String, SecureParameter> securedParameterNames) {
                return null;
            }
        };
    }

    @Test
    public void findMaskTypeTest_fieldExistInPassedMap_findDesiredMaskTypeInPassedMap() {
        Map<String, SecureParameter> securedParameterNames = new HashMap<>();
        securedParameterNames.put("password", new SecureParameter("password", MaskType.LEFT));
        MaskType maskType = replaceHelper.checkAndGetMaskType("password", securedParameterNames);
        assertEquals(maskType, MaskType.LEFT);
    }

    @Test
    public void findMaskTypeTest_fieldNotExistInBothMaps_returnNull() {
        Map<String, SecureParameter> securedParameterNames = new HashMap<>();
        securedParameterNames.put("password", new SecureParameter("password", MaskType.LEFT));
        MaskType maskType = replaceHelper.checkAndGetMaskType("testfield", securedParameterNames);
        assertNull(maskType);
    }

    @Test
    public void testMaskValue_callValueMaskFactoryAndSelectedValueMask() {
        ValueMasker valueMasker = Mockito.mock(ValueMasker.class);
        Mockito.when(valueMaskFactory.getValueMask(ArgumentMatchers.any())).thenReturn(valueMasker);
        replaceHelper.maskValue("someField", MaskType.COMPLETE);
        Mockito.verify(valueMaskFactory, Mockito.times(1)).getValueMask(ArgumentMatchers.eq(MaskType.COMPLETE));
        Mockito.verify(valueMasker, Mockito.times(1)).mask(ArgumentMatchers.eq("someField"));
    }
}