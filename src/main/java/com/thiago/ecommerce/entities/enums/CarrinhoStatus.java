package com.thiago.ecommerce.entities.enums;

public enum CarrinhoStatus {
    NEW(1),
    WAITING_PAYMENT(2),
    PROCESSING(3),
    PAID(4),
    CANCELLED(5);

    private Integer code;

    private CarrinhoStatus(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CarrinhoStatus valueOf(Integer code) throws IllegalAccessException {
        for(CarrinhoStatus value : CarrinhoStatus.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalAccessException("Invalid Status Code");
    }
}
