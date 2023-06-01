package br.ufrn.imd.microservices.msloan.recurrence;

import br.ufrn.imd.microservices.msloan.payroll.model.Payroll;
import br.ufrn.imd.microservices.msloan.util.Endpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(Endpoint.RECURRENCE)
public class RecurrenceController {

    @PostMapping
    public ResponseEntity<Payroll> save(@RequestBody UUID id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
