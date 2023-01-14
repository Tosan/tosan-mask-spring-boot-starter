package com.tosan.tools.mask.starter.exception;

/**
 * @author mina khoshnevisan
 * @since 7/5/2022
 */
public class JsonConvertException  extends RuntimeException{

    public JsonConvertException(String message) {
        super(message);
    }

    public JsonConvertException(String message, Throwable cause) {
        super(message, cause);
    }
}