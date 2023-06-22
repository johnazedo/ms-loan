package br.ufrn.imd.microservices.msloan.payroll.dto;

import java.math.BigDecimal;

public record CheckDto(Integer id,
                       BigDecimal valor,
                       BigDecimal limite,
                       BigDecimal juros,
                       String valorExtenso,
                       String dataAtual,
                       String nome,
                       String numero,
                       String serie,
                       String dataEntrada,
                       Integer idConta) {
}
