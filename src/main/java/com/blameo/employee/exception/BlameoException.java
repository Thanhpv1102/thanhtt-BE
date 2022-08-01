package com.blameo.employee.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.PrintStream;
import java.io.PrintWriter;

public class BlameoException extends Exception {

    private int code;
    private String message;

    public BlameoException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public BlameoException(String message) {
        this.message = message;
        this.code = 500;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonIgnore
    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    @JsonIgnore
    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return super.initCause(cause);
    }


    @JsonIgnore
    @Override
    public String toString() {
        return super.toString();
    }

    @JsonIgnore
    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    @JsonIgnore
    @Override
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
    }

    @JsonIgnore
    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
    }

    @JsonIgnore
    @Override
    public synchronized Throwable fillInStackTrace() {
        return super.fillInStackTrace();
    }

    @JsonIgnore
    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }

    @JsonIgnore
    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
        super.setStackTrace(stackTrace);
    }
}
