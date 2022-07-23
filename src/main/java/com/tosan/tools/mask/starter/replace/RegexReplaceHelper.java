package com.tosan.tools.mask.starter.replace;

import com.tosan.tools.mask.starter.business.ValueMaskFactory;
import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import com.tosan.tools.mask.starter.config.SecureParameter;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author M.khoshnevisan
 * @since 6/23/2021
 */
public class RegexReplaceHelper extends ReplaceHelper {

    public RegexReplaceHelper(ValueMaskFactory valueMaskFactory) {
        super(valueMaskFactory);
    }

    @Override
    public String replace(String input, Map<String, SecureParameter> securedParameterNames) {
        Pattern pattern = Pattern.compile("\"([^\"]+)\"([ ]*[:][ ]*)\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String tag = matcher.group(1);
            MaskType maskType = checkAndGetMaskType(tag, securedParameterNames);
            if (maskType != null) {
                String originalTag = "\"" + tag + "\"" + matcher.group(2) + "\"" + matcher.group(3) + "\"";
                String toBeReplacedTag;
                String value = matcher.group(3);
                if (value == null) {
                    toBeReplacedTag = "\"" + tag + "\"" + matcher.group(2) + "null";
                } else {
                    String maskedValue = maskValue(value, maskType);
                    toBeReplacedTag = "\"" + tag + "\"" + matcher.group(2) + "\"" + maskedValue + "\"";
                }
                input = input.replace(originalTag, toBeReplacedTag);
            }
        }
        return input;
    }
}