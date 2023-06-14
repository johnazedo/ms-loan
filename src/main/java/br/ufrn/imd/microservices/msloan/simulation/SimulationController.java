package br.ufrn.imd.microservices.msloan.simulation;

import br.ufrn.imd.microservices.msloan.core.util.Endpoint;
import br.ufrn.imd.microservices.msloan.payroll.service.PayrollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoint.SIMULATION)
public class SimulationController {

    private final PayrollService payrollService;

    @PostMapping
    public ResponseEntity<PayrollLoanSimulatedDto> simulation(@RequestBody PayrollLoanSimulationDto simulation) {
        return ResponseEntity.ok(payrollService.simulatePayrollLoan(simulation));
    }

    public SimulationController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }
}
