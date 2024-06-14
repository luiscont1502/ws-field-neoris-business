package com.neoris.persistence.postgres.service.impl;

import com.neoris.persistence.postgres.entity.MovimientoEntity;
import com.neoris.persistence.postgres.repository.MovimientoRepository;
import com.neoris.persistence.postgres.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;
    @Override
    public List<MovimientoEntity> findAllMovimientos(Pageable pageable) {
        return movimientoRepository.findAllMovimientos(pageable);
    }

    @Override
    public List<MovimientoEntity> findAllMovimientoRango(Pageable pageable, Timestamp fechaInicio, Timestamp fechaFin,Integer idCuenta) {
        return movimientoRepository.findAllMovimientoRango(pageable,fechaInicio,fechaFin,idCuenta);
    }

    @Override
    public MovimientoEntity findMovimientoById(Integer idMovimiento) {
        return movimientoRepository.findMovimientoById(idMovimiento);
    }

    @Override
    public MovimientoEntity saveOrUpdate(MovimientoEntity movimiento) {
        return movimientoRepository.save(movimiento);
    }

    @Override
    public MovimientoEntity findPenultimateActiveRecord(Integer idCuenta) {
        return movimientoRepository.findPenultimateActiveRecord(idCuenta).orElse(null);
    }
}
