package com.neoris.persistence.postgres.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Objects;
@Data
@Entity
@Table(name = "movimiento", schema = "public", catalog = "neoris")
public class MovimientoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_movimiento")
    private int idMovimiento;
    @Basic
    @Column(name = "fecha")
    private Timestamp fecha;
    @Basic
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;
    @Basic
    @Column(name = "valor")
    private Double valor;
    @Basic
    @Column(name = "saldo")
    private Double saldo;
    @Basic
    @Column(name = "estado")
    private String estado;
    @Basic
    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;
    @Basic
    @Column(name = "fecha_modificacion")
    private Timestamp fechaModificacion;
    @Basic
    @Column(name = "id_cuenta")
    private Integer idCuenta;
    @ManyToOne
    @JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta",insertable = false,updatable = false)
    private CuentaEntity cuentaByIdCuenta;

}
