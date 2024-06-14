package com.neoris.persistence.postgres.model;

import lombok.Getter;
import lombok.Setter;


public class ResponseData<T> extends ResponseModel {
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private T data;

    public ResponseData(String code){
        super(code);
    }


}
