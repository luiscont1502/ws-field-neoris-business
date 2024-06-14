package com.neoris.controller;

import com.neoris.core.MovimientoCore;
import com.neoris.enums.EnumResponse;
import com.neoris.model.ResponseData;
import com.neoris.model.SearchDto;
import com.neoris.persistence.postgres.entity.MovimientoEntity;
import com.neoris.persistence.postgres.service.MovimientoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
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
@RequestMapping("/movimientos")
public class MovimientoController {

     @Autowired
    private MovimientoService movimientoService;
     @Autowired
     private MovimientoCore movimientoCore;
    @PostMapping("/allMovimientos")
    public ResponseEntity<ResponseData<List<MovimientoEntity>>> findAllMovimientp(@RequestBody SearchDto searchDto){
        ResponseData<List<MovimientoEntity>> response=new ResponseData<>(EnumResponse.VACIO.code());
        try{
            List<MovimientoEntity> movimiento= movimientoService.findAllMovimientos(PageRequest.of(searchDto.getPage(),searchDto.getSize()));
            response.setData(movimiento);
        }catch(Exception e){
            log.info("Error findAllMovimientp(): ",e);
            response.setMessage("Error : "+e.getMessage());
            response.setCode(EnumResponse.ERROR.code());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/allMovimientosDetalle")
    public ResponseEntity<ResponseData<List<MovimientoEntity>>> findAllMovimientoDetalle(@RequestBody SearchDto searchDto) throws ParseException {
        ResponseData<List<MovimientoEntity>> response=new ResponseData<>(EnumResponse.VACIO.code());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaIncio = dateFormat.parse(searchDto.getStartDate());
        Date fechafin = dateFormat.parse(searchDto.getEndDate());
        try{
            List<MovimientoEntity> movimiento= movimientoService.findAllMovimientoRango(PageRequest.of(searchDto.getPage(),searchDto.getSize()),new Timestamp(fechaIncio.getTime()),new Timestamp(fechafin.getTime()),searchDto.getCode());
            response.setData(movimiento);
        }catch(Exception e){
            log.info("Error findAllMovimientp(): ",e);
            response.setMessage("Error : "+e.getMessage());
            response.setCode(EnumResponse.ERROR.code());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<ResponseData<MovimientoEntity>> saveMovimiento(@RequestBody MovimientoEntity entity){
        ResponseData<MovimientoEntity> response=new ResponseData<>(EnumResponse.VACIO.code());
        try{
            MovimientoEntity movimiento= movimientoCore.saveOrUpdate(entity);
            response.setData(movimiento);
        }catch(Exception e){
            log.info("Error SavePersona(): ",e);
            response.setMessage(e.getMessage());
            response.setCode(EnumResponse.ERROR.code());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseData<MovimientoEntity>> updateMovimiento(@RequestBody MovimientoEntity entity){
        ResponseData<MovimientoEntity> response=new ResponseData<>(EnumResponse.VACIO.code());
        try{
            MovimientoEntity movimeinto= movimientoCore.updateMovimiento(entity);
            response.setData(movimeinto);
        }catch(Exception e){
            log.info("Error SavePersona(): ",e);
            response.setMessage("Error : "+e.getMessage());
            response.setCode(EnumResponse.ERROR.code());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<ResponseData<MovimientoEntity>> findMovimientoById(@PathVariable("id") Integer id){
        ResponseData<MovimientoEntity> response=new ResponseData<>(EnumResponse.VACIO.code());
        try{
            MovimientoEntity persona= movimientoService.findMovimientoById(id);
            response.setData(persona);
        }catch(Exception e){
            log.info("Error findMovimientoById(): ",e);
            response.setMessage("Error : "+e.getMessage());
            response.setCode(EnumResponse.ERROR.code());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseData<MovimientoEntity>> deleteMovimientofindById(@PathVariable("id") Integer id){
        ResponseData<MovimientoEntity> response=new ResponseData<>(EnumResponse.VACIO.code());
        try{
            MovimientoEntity deletePersona=movimientoCore.deleteMovimiento(id);
            response.setData(deletePersona);
        }catch(Exception e){
            log.info("Error deleteMovimientofindById(): ",e);
            response.setMessage("Error : "+e.getMessage());
            response.setCode(EnumResponse.ERROR.code());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
