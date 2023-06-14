package br.ufrn.imd.microservices.msloan.core.client;

import br.ufrn.imd.microservices.msloan.payroll.dto.PayrollOutDto;
import br.ufrn.imd.microservices.msloan.payroll.model.CheckDto;
import br.ufrn.imd.microservices.msloan.payroll.model.Payroll;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "currentAccount", url = "${client.current-account-url}")
public interface CurrentAccountClient {

    @GetMapping(value = "/cheque/all/{accountId}")
    List<CheckDto> allChecksByAccount(@PathVariable Integer accountId);

    @PostMapping(value = "/recorrencia")
    Payroll postRecurrence(@RequestBody PayrollOutDto payroll);
}
