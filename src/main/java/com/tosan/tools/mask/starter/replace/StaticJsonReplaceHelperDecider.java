package com.tosan.tools.mask.starter.replace;

/**
 * @author mina khoshnevisan
 * @since 7/6/2022
 */
public class StaticJsonReplaceHelperDecider {

    private static JsonReplaceHelperDecider jsonReplaceHelperDecider;

    public static void init(JsonReplaceHelperDecider replaceHelper) {
        jsonReplaceHelperDecider = replaceHelper;
    }

    /**
     * @param json the input json
     * @return masked json
     */
    public static String replace(String json) {
        return jsonReplaceHelperDecider.replace(json);
    }
}