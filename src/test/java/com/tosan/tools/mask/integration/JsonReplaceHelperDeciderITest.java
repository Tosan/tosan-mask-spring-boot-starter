package com.tosan.tools.mask.integration;

import com.tosan.tools.mask.starter.replace.JsonReplaceHelperDecider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author mina khoshnevisan
 * @since 7/6/2022
 */
@SpringBootTest(classes = {TestMaskApplication.class})
public class JsonReplaceHelperDeciderITest {

    @Autowired
    private JsonReplaceHelperDecider jsonReplaceHelperDecider;

    @Test
    public void testReplacing() {
        String replace = jsonReplaceHelperDecider.replace("{\"pan\":\"5022291075648374\", \"password\":\"1234\"}");
        System.out.println(replace);
    }

    @Test
    public void testAddingMaskType() {
        String replace = jsonReplaceHelperDecider.replace("{\"testField\":\"5454fskdfsk\"}");
        System.out.println(replace);
    }
}