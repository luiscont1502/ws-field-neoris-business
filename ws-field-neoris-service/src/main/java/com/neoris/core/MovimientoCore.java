package com.neoris.core;

import com.neoris.exception.NeorisException;
import com.neoris.persistence.postgres.entity.CuentaEntity;
import com.neoris.persistence.postgres.entity.MovimientoEntity;
import com.neoris.persistence.postgres.service.CuentaService;
import com.neoris.persistence.postgres.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Component
public class MovimientoCore {
    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private MovimientoService movimientoService;

    public MovimientoCore(CuentaService cuentaService, MovimientoService movimientoService) {
        this.cuentaService = cuentaService;
        this.movimientoService = movimientoService;
    }
    public MovimientoCore() {

    }

    public MovimientoEntity saveOrUpdate(MovimientoEntity movimiento) throws NeorisException {
        MovimientoEntity movimientoResp=new MovimientoEntity();
        if(!Objects.isNull(movimiento) && movimiento.getIdCuenta()!=0){
            CuentaEntity cuenta=cuentaService.findCuentaById(movimiento.getIdCuenta());
            switch (movimiento.getTipoMovimiento()){
                case "Retiro":
                    cuenta.setSaldoInicial(cuenta.getSaldoInicial()-movimiento.getValor());
                    if(cuenta.getSaldoInicial()<=0){
                        throw new NeorisException("Saldo no Disponible");
                    }
                    break;
                case "Abono":
                    cuenta.setSaldoInicial(cuenta.getSaldoInicial()+movimiento.getValor());
                    break;
            }
            cuentaService.saveOrUpdate(cuenta);
            movimiento.setEstado("1");
            movimiento.setFecha(new Timestamp(System.currentTimeMillis()));
            movimiento.setSaldo(cuenta.getSaldoInicial());
            movimiento.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            movimiento.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            movimientoResp=movimientoService.saveOrUpdate(movimiento);
        }
    return movimientoResp;
    }
    public MovimientoEntity updateMovimiento(MovimientoEntity movimiento) throws NeorisException {
        MovimientoEntity movimientoResp=new MovimientoEntity();
        CuentaEntity cuentaResp;
        MovimientoEntity movimientoAnterior;
        if(movimiento.getIdMovimiento()!=0){
            movimientoResp=movimientoService.findMovimientoById(movimiento.getIdMovimiento());
            cuentaResp=cuentaService.findCuentaById(movimiento.getIdCuenta());
            movimientoResp.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            cuentaResp.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            movimientoResp.setValor(movimiento.getValor());
            Double valorResp=0d;
            switch (movimiento.getTipoMovimiento()){
                case "Retiro":
                    movimientoAnterior=movimientoService.findPenultimateActiveRecord(cuentaResp.getIdCuenta());
                    valorResp=movimientoAnterior.getSaldo()-movimiento.getValor();
                    movimientoResp.setSaldo(valorResp);
                    movimientoResp.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    cuentaResp.setSaldoInicial(valorResp);
                    if(cuentaResp.getSaldoInicial()<=0){
                        throw new NeorisException("Saldo no Disponible");
                    }
                    break;
                case "Abono":
                    movimientoAnterior=movimientoService.findPenultimateActiveRecord(cuentaResp.getIdCuenta());
                    valorResp=movimientoAnterior.getSaldo()+movimiento.getValor();
                    movimientoResp.setSaldo(valorResp);
                    cuentaResp.setSaldoInicial(valorResp);
                    movimientoResp.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    break;
            }

            cuentaResp.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            cuentaService.saveOrUpdate(cuentaResp);
        }
        return  movimientoService.saveOrUpdate(movimientoResp);
    }
    public MovimientoEntity deleteMovimiento(Integer idMovimiento) throws NeorisException {
        MovimientoEntity movimientoResp;
        MovimientoEntity movimientoAnterior;
        CuentaEntity cuentaResp;
        movimientoResp=movimientoService.findMovimientoById(idMovimiento);
        if(!Objects.isNull(movimientoResp)){
            movimientoResp.setEstado("0");
            cuentaResp=cuentaService.findCuentaById(movimientoResp.getIdCuenta());
            cuentaResp.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            movimientoAnterior=movimientoService.findPenultimateActiveRecord(cuentaResp.getIdCuenta());
            cuentaResp.setSaldoInicial(movimientoAnterior.getSaldo());
            cuentaService.saveOrUpdate(cuentaResp);
        }else{
            throw new NeorisException("No existen Registros");
        }

        return movimientoAnterior;

    }
}
