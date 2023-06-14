package br.ufrn.imd.microservices.msloan.requirementdetail.controller;

import br.ufrn.imd.microservices.msloan.core.util.Endpoint;
import br.ufrn.imd.microservices.msloan.payroll.service.PayrollService;
import br.ufrn.imd.microservices.msloan.requirementdetail.dto.RequirementDetailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Endpoint.REQUIREMENTS_DETAILS)
public class RequirementDetailController {

    private final PayrollService payrollService;

    @GetMapping("/{accountId}")
    public ResponseEntity<List<RequirementDetailDto>> save(@PathVariable Integer accountId) {
        return ResponseEntity.ok(payrollService.findPayrolls(accountId));
    }

    public RequirementDetailController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }
}