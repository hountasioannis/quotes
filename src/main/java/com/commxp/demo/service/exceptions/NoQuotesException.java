package com.commxp.demo.service.exceptions;

public class NoQuotesException extends Exception {
    private static final long serialVersionUID = 1L;

    public NoQuotesException(Class<?> entityClass, Long id) {
        super("there are no quotes");
    }
}
