package com.tosan.tools.mask.starter.replace;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.tosan.tools.mask.starter.business.ComparisonTypeFactory;
import com.tosan.tools.mask.starter.business.ValueMaskFactory;
import com.tosan.tools.mask.starter.business.enumeration.MaskType;
import com.tosan.tools.mask.starter.config.SecureParameter;
import com.tosan.tools.mask.starter.exception.JsonConvertException;

import java.util.Map;

/**
 * @author M.khoshnevisan
 * @since 6/23/2021
 */
public class JacksonReplaceHelper extends ReplaceHelper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JacksonReplaceHelper(ValueMaskFactory valueMaskFactory, ComparisonTypeFactory comparisonTypeFactory) {
        super(valueMaskFactory, comparisonTypeFactory);
    }

    @Override
    public String replace(String json, Map<String, SecureParameter> securedParameterNames) {
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new JsonConvertException("invalidJson", e);
        }
        if (isPrimitiveType(jsonNode)) {
            throw new JsonConvertException("primitive json type");
        }
        JsonNode result = process(jsonNode, "root", securedParameterNames);
        return result.toString();
    }

    private boolean isPrimitiveType(JsonNode jsonNode) {
        if (jsonNode instanceof ValueNode) {
            return true;
        } else {
            return false;
        }
    }

    private JsonNode process(JsonNode currentNode, String nodeName, Map<String, SecureParameter> securedParameterNames) {
        if (currentNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) currentNode;
            ArrayNode newArrayNode = objectMapper.createArrayNode();
            for (JsonNode jsonNode : arrayNode) {
                JsonNode processed = process(jsonNode, nodeName, securedParameterNames);
                newArrayNode.add(processed);
            }
            return newArrayNode;
        } else if (currentNode.isObject()) {
            currentNode.fields().forEachRemaining(entry -> entry.setValue(process(entry.getValue(), entry.getKey(), securedParameterNames)));
            return currentNode;
        } else {
            if (currentNode instanceof ValueNode) {
                if (currentNode instanceof NullNode) {
                    return currentNode;
                }
                String replace = replace(nodeName, currentNode.asText(), securedParameterNames);
                return new TextNode(replace);
            } else {
                return currentNode;
            }
        }
    }

    public String replace(String fieldName, String fieldValue, Map<String, SecureParameter> securedParameterNames) {
        MaskType maskType = checkAndGetMaskType(fieldName, securedParameterNames);
        String value = fieldValue;
        if (maskType != null && value != null) {
            value = maskValue(fieldValue, maskType);
        }
        return value;
    }
}