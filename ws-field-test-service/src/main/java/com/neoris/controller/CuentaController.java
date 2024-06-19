package com.neoris.controller;

import com.neoris.core.CuentaCore;
import com.neoris.enums.EnumResponse;
import com.neoris.model.ResponseData;
import com.neoris.model.SearchDto;
import com.neoris.persistence.postgres.entity.CuentaEntity;
import com.neoris.persistence.postgres.entity.MovimientoEntity;
import com.neoris.persistence.postgres.service.CuentaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private CuentaCore cuentaCore;

    @PostMapping("/allCuentas")
    public ResponseEntity<ResponseData<List<CuentaEntity>>> findAllCuenta(@RequestBody SearchDto searchDto){
        ResponseData<List<CuentaEntity>> response=new ResponseData<>(EnumResponse.VACIO.code());
        try{
            List<CuentaEntity> cuenta= cuentaService.findAllCuentas(PageRequest.of(searchDto.getPage(),searchDto.getSize()));
            response.setData(cuenta);
        }catch(Exception e){
            log.info("Error findAllCuenta(): ",e);
            response.setMessage("Error : "+e.getMessage());
            response.setCode(EnumResponse.ERROR.code());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/allCuentasRango")
    public ResponseEntity<ResponseData<List<CuentaEntity>>> findAllCuentaRango(@RequestBody SearchDto searchDto) throws ParseException {
        ResponseData<List<CuentaEntity>> response=new ResponseData<>(EnumResponse.VACIO.code());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaIncio = dateFormat.parse(searchDto.getStartDate());
        Date fechafin = dateFormat.parse(searchDto.getEndDate());
        try{
            List<CuentaEntity> cuenta= cuentaService.findAllCuentasRango(PageRequest.of(searchDto.getPage(),searchDto.getSize()),new Timestamp(fechaIncio.getTime()),new Timestamp(fechafin.getTime()));
            response.setData(cuenta);
        }catch(Exception e){
            log.info("Error findAllCuenta(): ",e);
            response.setMessage("Error : "+e.getMessage());
            response.setCode(EnumResponse.ERROR.code());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<ResponseData<CuentaEntity>> saveCuenta(@RequestBody CuentaEntity entity){
        ResponseData<CuentaEntity> response=new ResponseData<>(EnumResponse.VACIO.code());
        try{
            CuentaEntity movimiento= cuentaCore.saveOrUpdate(entity);
            response.setData(movimiento);
        }catch(Exception e){
            log.info("Error saveCuenta(): ",e);
            response.setMessage("Error : "+e.getMessage());
            response.setCode(EnumResponse.ERROR.code());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseData<CuentaEntity>> updateCuenta(@RequestBody CuentaEntity entity){
        ResponseData<CuentaEntity> response=new ResponseData<>(EnumResponse.VACIO.code());
        try{

            CuentaEntity movimiento= cuentaCore.saveOrUpdate(entity);
            response.setData(movimiento);
        }catch(Exception e){
            log.info("Error updateCuenta(): ",e);
            response.setMessage("Error : "+e.getMessage());
            response.setCode(EnumResponse.ERROR.code());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<ResponseData<CuentaEntity>> findCuentaById(@PathVariable("id") Integer id){
        ResponseData<CuentaEntity> response=new ResponseData<>(EnumResponse.VACIO.code());
        try{
            CuentaEntity cuenta= cuentaService.findCuentaById(id);
            response.setData(cuenta);
        }catch(Exception e){
            log.info("Error findCuentaById(): ",e);
            response.setMessage("Error : "+e.getMessage());
            response.setCode(EnumResponse.ERROR.code());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseData<CuentaEntity>> deleteCuentafindById(@PathVariable("id") Integer id){
        ResponseData<CuentaEntity> response=new ResponseData<>(EnumResponse.VACIO.code());
        try{
            CuentaEntity cuenta= cuentaService.findCuentaById(id);
            cuenta.setEstado("0");
            CuentaEntity deletePersona=cuentaService.saveOrUpdate(cuenta);
            response.setData(deletePersona);
        }catch(Exception e){
            log.info("Error deleteCuentafindById(): ",e);
            response.setMessage("Error : "+e.getMessage());
            response.setCode(EnumResponse.ERROR.code());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
