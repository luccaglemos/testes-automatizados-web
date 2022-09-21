package br.com.bancoada.bancoada.service;

import br.com.bancoada.bancoada.entity.ContaCorrente;
import br.com.bancoada.bancoada.repository.ContaCorrenteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ContaCorrenteService {

    private final ContaCorrenteRepository repository;

    public ContaCorrenteService(ContaCorrenteRepository repository, ServicoDeEnviarEmail servicoDeEnviarEmail) {
        this.repository = repository;
    }


    public BigDecimal sacar(int contaCorrenteId, BigDecimal valor) {
        ContaCorrente conta = repository.findById(contaCorrenteId)
                .orElseThrow();

        if (valor != null && conta.getSaldo().compareTo(valor) >= 0) {
            efetuarSaque(conta, valor);
            return conta.getSaldo();
        }

        throw new RuntimeException("Erro ao sacar");
    }

    public BigDecimal transferir(int contaOrigemId, int contaDestinoId, BigDecimal valor) {
        //retornar o novo saldo
        return BigDecimal.ZERO;
    }

    public BigDecimal consultarSaldo(int idConta) {
        return BigDecimal.ZERO;
    }

    private void efetuarSaque(ContaCorrente conta, BigDecimal valor) {
        conta.setSaldo(conta.getSaldo().subtract(valor));
        repository.save(conta);
    }
}
