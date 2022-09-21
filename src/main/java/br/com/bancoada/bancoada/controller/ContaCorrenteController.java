package br.com.bancoada.bancoada.controller;

import br.com.bancoada.bancoada.service.ContaCorrenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/conta-corrente")
@RequiredArgsConstructor
public class ContaCorrenteController {

    private final ContaCorrenteService service;

    @GetMapping("/saldo")
    public BigDecimal consultarSaldo(@RequestParam("id") int idConta) {
        BigDecimal saldo = service.consultarSaldo(idConta);
        return saldo;
    }

    @PostMapping("/{id_conta}/sacar/{valor}")
    public ResponseEntity<BigDecimal> sacar(@PathVariable("id_conta") int idconta, @PathVariable BigDecimal valor) {
        BigDecimal novoSaldo = service.sacar(idconta, valor);
        return ResponseEntity.ok(novoSaldo);
    }

    @PostMapping("/{id_conta_origem}/transferir/{id_conta_destino}/{valor}")
    public ResponseEntity<BigDecimal> transferir(@PathVariable("id_conta_origem") int idContaOrigem,
                                           @PathVariable("id_conta_destino") int idContaDestino,
                                           @PathVariable BigDecimal valor) {
        BigDecimal novoSaldoOrigem = service.transferir(idContaOrigem, idContaDestino, valor);
        return ResponseEntity.ok(novoSaldoOrigem);
    }
}
