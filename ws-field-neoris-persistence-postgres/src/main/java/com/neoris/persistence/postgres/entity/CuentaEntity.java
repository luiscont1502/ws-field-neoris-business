package com.neoris.persistence.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cuenta", schema = "public", catalog = "neoris")
public class CuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cuenta")
    private int idCuenta;
    @Basic
    @Column(name = "id_persona")
    private Integer idPersona;
    @Basic
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    @Basic
    @Column(name = "tipo_cuenta")
    private String tipoCuenta;
    @Basic
    @Column(name = "saldo_inicial")
    private Double saldoInicial;
    @Basic
    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;
    @Basic
    @Column(name = "fecha_modificacion")
    private Timestamp fechaModificacion;
    @Basic
    @Column(name = "estado")
    private String estado;

}
