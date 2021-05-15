package com.thiago.controledeestoque.entities.enums;

public enum Status {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELLED(5);

    private Integer code;

    private Status(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Status valueOf(int code) throws IllegalAccessException {
        for(Status value : Status.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalAccessException("Invalid Status Code");
    }
}
