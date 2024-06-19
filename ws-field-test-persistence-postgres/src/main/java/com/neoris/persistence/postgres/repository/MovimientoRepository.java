package com.neoris.persistence.postgres.repository;

import com.neoris.persistence.postgres.entity.MovimientoEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity,Integer> {
    @Query("select m from MovimientoEntity m where m.estado='1'")
    List<MovimientoEntity> findAllMovimientos(Pageable pageable);
    @Query("select m from MovimientoEntity m where m.estado='1' and m.idMovimiento=:idMovimiento" )
    MovimientoEntity findMovimientoById(@Param("idMovimiento") Integer idMovimiento);
    @Query("select m from MovimientoEntity m where m.estado='1' and m.idCuenta=:idCuenta and m.fechaCreacion between :fechaInicio and :fechaFin")
    List<MovimientoEntity> findAllMovimientoRango(Pageable pageable, Timestamp fechaInicio, Timestamp fechaFin,Integer idCuenta);

    @Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY id_movimiento DESC) as rn FROM movimiento WHERE estado = '1' AND id_cuenta = :idCuenta) sub WHERE sub.rn = 2", nativeQuery = true)
    Optional<MovimientoEntity> findPenultimateActiveRecord(@Param("idCuenta") Integer idCuenta);

}
