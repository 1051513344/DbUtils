package com.laoxu.demo.exception;


import com.laoxu.demo.dataobject.entity.AbstractCode;

/**
 * 系统运行时异常父类.
 * @author plz
 */
public class BizRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_CODE = -1;

    private Integer code = DEFAULT_CODE;
    private String message;

    public BizRuntimeException() {
    }

    public BizRuntimeException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BizRuntimeException(String message) {
        super(message);
        this.message = message;
    }

    public BizRuntimeException(Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
    }

    public BizRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BizRuntimeException(AbstractCode abstractCode) {
        this.code = abstractCode.getCode();
        this.message = abstractCode.getDesc();
    }

    public Integer getCode() {
        return code;
    }

    protected void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    protected void setMessage(String message) {
        this.message = message;
    }

    public boolean isDefault() {
        return code == DEFAULT_CODE;
    }

}
