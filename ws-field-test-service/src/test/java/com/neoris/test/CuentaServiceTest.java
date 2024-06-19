package com.neoris.test;
import com.neoris.persistence.postgres.entity.CuentaEntity;
import com.neoris.persistence.postgres.repository.CuentaRepository;
import com.neoris.persistence.postgres.service.impl.CuentaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CuentaServiceTest {

    @InjectMocks
    private CuentaServiceImpl cuentaService;

    @Mock
    private CuentaRepository cuentaRepository;

    @Test
    public void testSaveCuenta() {
        CuentaEntity cuenta = new CuentaEntity();
        cuenta.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
        cuenta.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
        cuenta.setIdPersona(1);
        cuenta.setNumeroCuenta("561853232");
        cuenta.setSaldoInicial(21.00);
        cuenta.setTipoCuenta("Corriente");
        cuenta.setEstado("1");

        when(cuentaRepository.save(any(CuentaEntity.class))).thenReturn(cuenta);

        CuentaEntity cuentaResp = cuentaService.saveOrUpdate(cuenta);

        assertEquals("561853232", cuentaResp.getNumeroCuenta());
        verify(cuentaRepository, times(1)).save(cuenta);
    }
}
