package br.ufrn.imd.microservices.msloan.core.client;

import br.ufrn.imd.microservices.msloan.payroll.dto.AccountDto;
import br.ufrn.imd.microservices.msloan.payroll.dto.BalanceDto;
import br.ufrn.imd.microservices.msloan.payroll.dto.CheckDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "currentAccountClient", url = "${client.current-account-url}")
public interface CurrentAccountClient {

    @GetMapping(value = "/cheque/all/{accountId}")
    List<CheckDto> allChecksByAccount(@PathVariable Integer accountId);

    @GetMapping(value = "/saldo/{accountId}")
    BalanceDto balanceByAccount(@PathVariable Integer accountId);

    @PutMapping("/{accountId}")
    AccountDto updateBalance(@PathVariable Integer accountId, @RequestBody AccountDto accountDto);
}
