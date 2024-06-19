package com.neoris.core;

import com.neoris.persistence.postgres.entity.CuentaEntity;
import com.neoris.persistence.postgres.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class CuentaCore {
    @Autowired
    private CuentaService cuentaService;

    public CuentaEntity saveOrUpdate(CuentaEntity entity){
        CuentaEntity cuenta;
        if(entity.getIdCuenta()!=0){
            cuenta=cuentaService.findCuentaById(entity.getIdCuenta());
            cuenta.setSaldoInicial(entity.getSaldoInicial());
            cuenta.setTipoCuenta(entity.getTipoCuenta());
            cuenta.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
        }else{
            cuenta=entity;
            cuenta.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            cuenta.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            cuenta.setEstado("1");
        }
        return cuentaService.saveOrUpdate(cuenta);
    }

}
