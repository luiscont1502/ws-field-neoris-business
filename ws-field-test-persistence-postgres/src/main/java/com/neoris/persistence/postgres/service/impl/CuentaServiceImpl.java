package com.neoris.persistence.postgres.service.impl;

import com.neoris.persistence.postgres.entity.CuentaEntity;
import com.neoris.persistence.postgres.repository.CuentaRepository;
import com.neoris.persistence.postgres.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<CuentaEntity> findAllCuentas(Pageable pageable) {
        return cuentaRepository.findAllCuentas(pageable);
    }

    @Override
    public List<CuentaEntity> findAllCuentasRango(Pageable pageable, Timestamp fechaInicio, Timestamp fechaFin) {
        return cuentaRepository.findAllCuentasRango(pageable, fechaInicio,fechaFin);
    }
    @Override
    public CuentaEntity findCuentaById(Integer idCuenta) {
        return cuentaRepository.findCuentaById(idCuenta);
    }

    @Override
    public CuentaEntity saveOrUpdate(CuentaEntity cuenta) {
        return cuentaRepository.save(cuenta);
    }
}
