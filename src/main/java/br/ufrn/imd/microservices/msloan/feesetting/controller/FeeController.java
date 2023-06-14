package br.ufrn.imd.microservices.msloan.feesetting.controller;

import br.ufrn.imd.microservices.msloan.core.util.Endpoint;
import br.ufrn.imd.microservices.msloan.feesetting.service.FeeService;
import br.ufrn.imd.microservices.msloan.feesetting.dto.FeeDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoint.FEES)
public class FeeController {

    private final FeeService service;

    @PostMapping
    public ResponseEntity<FeeDto> save(@RequestBody @Valid FeeDto fee) {
        return ResponseEntity.ok(service.save(fee));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody FeeDto fee) {
        service.update(fee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public FeeController(FeeService feeService) {
        this.service = feeService;
    }
}
