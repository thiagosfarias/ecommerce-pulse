package com.thiago.controledeestoque.entities.enums;

public enum CarrinhoStatus {
    WAITING_PAYMENT(1),
    PROCESSING(2),
    PAID(3),
    CANCELLED(4);

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
