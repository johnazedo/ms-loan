package br.ufrn.imd.microservices.msloan.payroll.controller;

import br.ufrn.imd.microservices.msloan.core.util.Endpoint;
import br.ufrn.imd.microservices.msloan.payroll.dto.PayrollOutDto;
import br.ufrn.imd.microservices.msloan.payroll.dto.PayrollPostDto;
import br.ufrn.imd.microservices.msloan.payroll.dto.PayrollPutDto;
import br.ufrn.imd.microservices.msloan.payroll.service.PayrollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoint.PAYROLLS)
public class PayrollController {

    private final PayrollService payrollService;

    @PostMapping
    public ResponseEntity<PayrollOutDto> save(@RequestBody PayrollPostDto payroll) {
        return ResponseEntity.ok(payrollService.save(payroll));
    }

    @PutMapping
    public ResponseEntity<PayrollOutDto> update(@RequestBody PayrollPutDto payrollDto) {
        return ResponseEntity.ok(payrollService.update(payrollDto));
    }

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }
}
