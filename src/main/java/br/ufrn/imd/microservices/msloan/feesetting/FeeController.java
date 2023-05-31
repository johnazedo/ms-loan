package br.ufrn.imd.microservices.msloan.feesetting;

import br.ufrn.imd.microservices.msloan.util.Endpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoint.FEE)
public class FeeController {

    @PutMapping
    public ResponseEntity<Fee> update(){
        return ResponseEntity.ok(new Fee());
    }
}
