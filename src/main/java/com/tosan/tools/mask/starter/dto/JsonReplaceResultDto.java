package com.tosan.tools.mask.starter.dto;

/**
 * @author mina khoshnevisan
 * @since 7/20/2022
 */
public class JsonReplaceResultDto {
    private boolean isJson;
    private String replacedJson;

    public JsonReplaceResultDto(boolean isJson) {
        this.isJson = isJson;
    }

    public JsonReplaceResultDto(boolean isJson, String replacedJson) {
        this.isJson = isJson;
        this.replacedJson = replacedJson;
    }

    public boolean isJson() {
        return isJson;
    }

    public String getReplacedJson() {
        return replacedJson;
    }
}