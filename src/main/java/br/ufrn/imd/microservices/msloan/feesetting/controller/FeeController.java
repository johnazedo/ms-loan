package br.ufrn.imd.microservices.msloan.feesetting.controller;

import br.ufrn.imd.microservices.msloan.feesetting.dto.FeeDTO;
import br.ufrn.imd.microservices.msloan.feesetting.model.Fee;
import br.ufrn.imd.microservices.msloan.util.Endpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoint.FEE)
public class FeeController {

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Fee fee) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Fee> save(@RequestBody FeeDTO fee) {
        return ResponseEntity.ok(new Fee());
    }
}
