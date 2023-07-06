package br.ufrn.imd.microservices.msloan.payroll.controller;

import br.ufrn.imd.microservices.msloan.core.util.Endpoint;
import br.ufrn.imd.microservices.msloan.payroll.dto.PayrollOutDto;
import br.ufrn.imd.microservices.msloan.payroll.dto.PayrollPostDto;
import br.ufrn.imd.microservices.msloan.payroll.dto.PayrollPutDto;
import br.ufrn.imd.microservices.msloan.payroll.service.PayrollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoint.PAYROLLS)
public class PayrollController {

    private final PayrollService payrollService;

    @GetMapping
    public ResponseEntity<List<PayrollOutDto>> findAll() {
        return ResponseEntity.ok(payrollService.findAll());
    }

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
