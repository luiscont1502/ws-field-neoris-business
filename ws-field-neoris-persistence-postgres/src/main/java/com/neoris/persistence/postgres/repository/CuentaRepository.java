package com.neoris.persistence.postgres.repository;

import com.neoris.persistence.postgres.entity.CuentaEntity;
import com.neoris.persistence.postgres.entity.MovimientoEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity,Integer> {
    @Query("select c from CuentaEntity c where c.estado ='1'")
    List<CuentaEntity> findAllCuentas(Pageable pageable);
    @Query("select c from CuentaEntity c where c.estado ='1' and c.fechaCreacion  between :fechaInicio and :fechaFin")
    List<CuentaEntity> findAllCuentasRango(Pageable pageable, Timestamp fechaInicio,Timestamp fechaFin);

    @Query("select c from CuentaEntity c where c.idCuenta=:idCuenta and c.estado='1'")
    CuentaEntity findCuentaById(@Param("idCuenta") Integer idCuenta);
}
