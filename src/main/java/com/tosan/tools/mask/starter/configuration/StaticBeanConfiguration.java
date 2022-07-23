package com.tosan.tools.mask.starter.configuration;

import com.tosan.tools.mask.starter.replace.JsonReplaceHelperDecider;
import com.tosan.tools.mask.starter.replace.StaticJsonReplaceHelperDecider;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author M.khoshnevisan
 * @since 10/31/2021
 */
@Configuration
public class StaticBeanConfiguration implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        StaticJsonReplaceHelperDecider.init(context.getBean(JsonReplaceHelperDecider.class));
    }
}