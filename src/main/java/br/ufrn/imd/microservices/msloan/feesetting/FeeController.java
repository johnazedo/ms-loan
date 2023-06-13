package br.ufrn.imd.microservices.msloan.feesetting;

import br.ufrn.imd.microservices.msloan.util.Endpoint;
import jakarta.validation.Valid;
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

    private final FeeService service;

    @PostMapping
    public ResponseEntity<FeeDTO> save(@RequestBody @Valid FeeDTO fee) {
        return ResponseEntity.ok(service.save(fee));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody FeeDTO fee) {
        service.update(fee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public FeeController(FeeService feeService) {
        this.service = feeService;
    }
}
