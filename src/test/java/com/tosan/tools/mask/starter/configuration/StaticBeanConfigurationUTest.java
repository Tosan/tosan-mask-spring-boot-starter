package com.tosan.tools.mask.starter.configuration;

import com.tosan.tools.mask.starter.replace.JsonReplaceHelperDecider;
import com.tosan.tools.mask.starter.replace.StaticJsonReplaceHelperDecider;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author mina khoshnevisan
 * @since 7/11/2022
 */
public class StaticBeanConfigurationUTest {

    private StaticBeanConfiguration staticBeanConfiguration = new StaticBeanConfiguration();

    @Test
    public void testSetApplicationContext_validReplaceHelperBean_injectReplaceHelperIntoStaticReplaceHelperDeciderCorrectly() {
        ApplicationContext context = mock(ApplicationContext.class);
        JsonReplaceHelperDecider jsonReplaceHelperDecider = mock(JsonReplaceHelperDecider.class);
        when(context.getBean(eq(JsonReplaceHelperDecider.class))).thenReturn(jsonReplaceHelperDecider);
        staticBeanConfiguration.setApplicationContext(context);
        String test = "test";
        StaticJsonReplaceHelperDecider.replace(test);
        verify(jsonReplaceHelperDecider, times(1)).replace(eq(test));
    }
}