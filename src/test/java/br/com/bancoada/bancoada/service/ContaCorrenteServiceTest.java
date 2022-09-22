package br.com.bancoada.bancoada.service;

import br.com.bancoada.bancoada.entity.ContaCorrente;
import br.com.bancoada.bancoada.repository.ContaCorrenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContaCorrenteServiceTest {

    @Mock
    ContaCorrenteRepository repository;

    @InjectMocks
    private ContaCorrenteService service;

    int idConta = 1;
    ContaCorrente contaCorrente;

    @BeforeEach
    void setup() {
        contaCorrente = new ContaCorrente();
        contaCorrente.setId(idConta);
        contaCorrente.setSaldo(new BigDecimal(100));
    }

    @Test
    void testaSeRetornaNovoSaldoCorretamente() {
        when(repository.findById(idConta)).thenReturn(Optional.of(contaCorrente));

        BigDecimal novoSaldo = service.sacar(idConta, new BigDecimal(50));
        assertEquals(new BigDecimal(50), novoSaldo);
    }

    @Test
    void testaConsultaSaldo() {
        when(repository.findById(idConta)).thenReturn(Optional.of(contaCorrente));

        BigDecimal saldo = service.consultarSaldo(idConta);

        assertEquals(new BigDecimal(100), saldo);
    }

    @Test
    void testaConsultarSaldoContaInexistente() {
        when(repository.findById(idConta)).thenReturn(Optional.empty());
        IllegalStateException exceptionRetornada = assertThrows(IllegalStateException.class,
                () -> service.consultarSaldo(idConta));
        assertEquals("Conta inexistente", exceptionRetornada.getMessage());
    }

}
