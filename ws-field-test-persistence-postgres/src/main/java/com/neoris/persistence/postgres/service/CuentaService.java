package com.neoris.persistence.postgres.service;

import com.neoris.persistence.postgres.entity.CuentaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface CuentaService {
    List<CuentaEntity> findAllCuentas(Pageable pageable);
    List<CuentaEntity> findAllCuentasRango(Pageable pageable, Timestamp fechaInicio,Timestamp fechaFin);

    CuentaEntity findCuentaById(Integer idCuenta);

    CuentaEntity saveOrUpdate(CuentaEntity cuenta);
}
