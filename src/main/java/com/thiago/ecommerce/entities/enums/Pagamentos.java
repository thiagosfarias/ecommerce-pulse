package com.thiago.ecommerce.entities.enums;

public enum Pagamentos {
    CARTAO(1),
    BOLETO(2);

    private Integer code;

    Pagamentos(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Pagamentos valueOf(int code) throws IllegalAccessException {
        for(Pagamentos value : Pagamentos.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalAccessException("Invalid Status Code");
    }
}
