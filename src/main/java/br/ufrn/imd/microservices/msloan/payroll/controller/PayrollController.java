package br.ufrn.imd.microservices.msloan.payroll.controller;

import br.ufrn.imd.microservices.msloan.payroll.dto.PayrollDTO;
import br.ufrn.imd.microservices.msloan.payroll.model.Payroll;
import br.ufrn.imd.microservices.msloan.util.Endpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoint.PAYROLL)
public class PayrollController {

    @PostMapping
    public ResponseEntity<Payroll> save(@RequestBody PayrollDTO payroll) {
        return ResponseEntity.ok(new Payroll());
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Payroll payroll) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
