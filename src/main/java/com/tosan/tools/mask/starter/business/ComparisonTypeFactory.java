package com.tosan.tools.mask.starter.business;

import com.tosan.tools.mask.starter.business.enumeration.ComparisonType;
import com.tosan.tools.mask.starter.config.SecureParameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mina khoshnevisan
 * @since 9/18/2022
 */
public class ComparisonTypeFactory {
    protected Map<ComparisonType, ComparisonFunction> comparisonFunctionMap = new HashMap<>();

    public ComparisonTypeFactory(List<ComparisonFunction> comparisonFunctionList) {
        for (ComparisonFunction comparisonFunction : comparisonFunctionList) {
            comparisonFunctionMap.put(comparisonFunction.getComparisonType(), comparisonFunction);
        }
    }

    public boolean compare(String fieldName, SecureParameter entry) {
        if (fieldName == null || fieldName.isEmpty()) {
            return false;
        }
        ComparisonFunction comparisonFunction = comparisonFunctionMap.get(entry.getComparisonType());
        return comparisonFunction.compare(fieldName, entry.getParameterName());
    }

    public interface ComparisonFunction {
        boolean compare(String fieldName, String parameterName);

        ComparisonType getComparisonType();
    }

    public static class EqualsIgnoreCaseComparisonFunction implements ComparisonFunction {
        @Override
        public boolean compare(String fieldName, String parameterName) {
            return fieldName.equalsIgnoreCase(parameterName);
        }

        @Override
        public ComparisonType getComparisonType() {
            return ComparisonType.EQUALS_IGNORE_CASE;
        }
    }

    public static class EqualsComparisonFunction implements ComparisonFunction {
        @Override
        public boolean compare(String fieldName, String parameterName) {
            return fieldName.equals(parameterName);
        }

        @Override
        public ComparisonType getComparisonType() {
            return ComparisonType.EQUALS;
        }
    }

    public static class LikeComparisonFunction implements ComparisonFunction {
        @Override
        public boolean compare(String fieldName, String parameterName) {
            if (parameterName == null || parameterName.length() == 0) {
                return false;
            }
            return fieldName.toLowerCase().contains(parameterName.toLowerCase());
        }

        @Override
        public ComparisonType getComparisonType() {
            return ComparisonType.LIKE;
        }
    }

    public static class LeftLikeComparisonFunction implements ComparisonFunction {
        @Override
        public boolean compare(String fieldName, String parameterName) {
            if (parameterName == null || parameterName.length() == 0) {
                return false;
            }
            return fieldName.toLowerCase().startsWith(parameterName.toLowerCase());
        }

        @Override
        public ComparisonType getComparisonType() {
            return ComparisonType.LEFT_LIKE;
        }
    }

    public static class RightLikeComparisonFunction implements ComparisonFunction {
        @Override
        public boolean compare(String fieldName, String parameterName) {
            if (parameterName == null || parameterName.length() == 0) {
                return false;
            }
            return fieldName.toLowerCase().endsWith(parameterName.toLowerCase());
        }

        @Override
        public ComparisonType getComparisonType() {
            return ComparisonType.RIGHT_LIKE;
        }
    }
}