package com.neoris.persistence.postgres.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String code;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    public ResponseModel(String code){
    this.code=!code.isEmpty()?code:null;
    this.message=null;
    }

}
