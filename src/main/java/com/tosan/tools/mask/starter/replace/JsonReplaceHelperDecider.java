package com.tosan.tools.mask.starter.replace;

import com.tosan.tools.mask.starter.config.SecureParametersConfig;
import com.tosan.tools.mask.starter.dto.JsonReplaceResultDto;
import com.tosan.tools.mask.starter.exception.JsonConvertException;

/**
 * @author M.khoshnevisan
 * @since 6/23/2021
 */
public class JsonReplaceHelperDecider {

    private final JacksonReplaceHelper jacksonReplaceHelper;
    private final RegexReplaceHelper regexReplaceHelper;
    private final SecureParametersConfig secureParametersConfig;

    public JsonReplaceHelperDecider(JacksonReplaceHelper jacksonReplaceHelper, RegexReplaceHelper regexReplaceHelper,
                                    SecureParametersConfig secureParametersConfig) {
        this.jacksonReplaceHelper = jacksonReplaceHelper;
        this.regexReplaceHelper = regexReplaceHelper;
        this.secureParametersConfig = secureParametersConfig;
    }

    /**
     * use jacksonReplaceHelper. if any exception happen in parsing json regexReplaceHelper will be used.
     *
     * @param json the input json
     * @return masked json
     */
    public String replace(String json) {
        try {
            return jacksonReplaceHelper.replace(json, secureParametersConfig.getSecuredParametersMap());
        } catch (JsonConvertException e) {
            return regexReplaceHelper.replace(json, secureParametersConfig.getSecuredParametersMap());
        }
    }

    /**
     * works with jacksonReplaceHelper
     * returns original value if String is not json
     *
     * @param json the input json
     * @return masked json
     */
    public String replaceByJacksonHelper(String json) {
        try {
            return jacksonReplaceHelper.replace(json, secureParametersConfig.getSecuredParametersMap());
        } catch (JsonConvertException e) {
            return json;
        }
    }

    /**
     * works with jacksonReplaceHelper
     * show if input data was json and return replaced data as well
     *
     * @param json input json
     * @return JsonReplaceResultDto
     */
    public JsonReplaceResultDto checkJsonAndReplace(String json) {
        try {
            String replacedJson = jacksonReplaceHelper.replace(json, secureParametersConfig.getSecuredParametersMap());
            return new JsonReplaceResultDto(true, replacedJson);
        } catch (JsonConvertException e) {
            return new JsonReplaceResultDto(false);
        }
    }

    /**
     * mask with regexReplaceHelper
     *
     * @param json input json
     * @return replaced json
     */
    public String replaceByRegexHelper(String json) {
        return regexReplaceHelper.replace(json, secureParametersConfig.getSecuredParametersMap());
    }

    /**
     * mask field value base filed name
     *
     * @param fieldName input field name
     * @param fieldValue input field value
     * @return masked value
     */
    public String replace(String fieldName, String fieldValue) {
        return jacksonReplaceHelper.replace(fieldName, fieldValue, secureParametersConfig.getSecuredParametersMap());
    }
}