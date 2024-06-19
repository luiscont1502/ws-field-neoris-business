package com.neoris.enums;

public enum EnumResponse {
    OK("0","OK"),
    ERROR("1","Error"),
    VACIO("","");
    EnumResponse(String code, String state){
        this.code = code;
        this.state = state;
    }
    private String code;
    private String state;
    public String code(){
        return code;
    }
    public String state(){
        return state;
    }

}
