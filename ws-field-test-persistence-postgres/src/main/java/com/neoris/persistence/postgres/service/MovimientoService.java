package com.neoris.persistence.postgres.service;

import com.neoris.persistence.postgres.entity.CuentaEntity;
import com.neoris.persistence.postgres.entity.MovimientoEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.util.List;

public interface MovimientoService {
    List<MovimientoEntity> findAllMovimientos(Pageable pageable);
    List<MovimientoEntity> findAllMovimientoRango(Pageable pageable, Timestamp fechaInicio, Timestamp fechaFin,Integer idCuenta);

    MovimientoEntity findMovimientoById(Integer idMovimiento);
    MovimientoEntity saveOrUpdate(MovimientoEntity movimiento);
    MovimientoEntity findPenultimateActiveRecord(Integer idCuenta);
}
