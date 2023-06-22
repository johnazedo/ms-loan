package br.ufrn.imd.microservices.msloan.payroll.dto;

public record AccountDto(Integer id,

                         Integer idUser,

                         String nome,

                         String primeiroNome,

                         String agencia,

                         String numero,

                         Integer digito,

                         String numeroComDigito,

                         String cpf,

                         String profissao,

                         boolean elegivelCredito,

                         double saldo,

                         String ChavePix) {
}
