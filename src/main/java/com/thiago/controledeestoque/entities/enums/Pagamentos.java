package com.thiago.controledeestoque.entities.enums;

public enum Pagamentos {
    CREDIT_CARD(1),
    DEBIT_CARD(2),
    BOLETO(3);

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
