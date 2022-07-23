package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import com.tosan.tools.mask.starter.exception.MaskTypeNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * @author M.khoshnevisan
 * @since 7/3/2021
 */
public class ValueMaskerFactoryUTest {

    @Test
    public void testGetValueMask_ListInjected_selectCorrectValueMask() {

        List<ValueMasker> valueMaskerList = new ArrayList<>();
        ValueMasker mockValueMasker = Mockito.mock(ValueMasker.class);
        MaskType left = MaskType.LEFT;
        Mockito.when(mockValueMasker.getType()).thenReturn(left);
        valueMaskerList.add(mockValueMasker);
        ValueMaskFactory valueMaskFactory = new ValueMaskFactory(valueMaskerList);
        ValueMasker leftValueMasker = valueMaskFactory.getValueMask(left);
        Assertions.assertEquals(leftValueMasker, mockValueMasker);
    }

    @Test
    public void testGetValueMask_noValueMaskForMaskType_throwsServiceException() {
        List<ValueMasker> valueMaskerList = new ArrayList<>();
        ValueMasker mockValueMasker = Mockito.mock(ValueMasker.class);
        MaskType left = MaskType.LEFT;
        Mockito.when(mockValueMasker.getType()).thenReturn(left);
        valueMaskerList.add(mockValueMasker);
        ValueMaskFactory valueMaskFactory = new ValueMaskFactory(valueMaskerList);
        Assertions.assertThrows(MaskTypeNotFoundException.class, () -> valueMaskFactory.getValueMask(MaskType.COMPLETE));
    }
}