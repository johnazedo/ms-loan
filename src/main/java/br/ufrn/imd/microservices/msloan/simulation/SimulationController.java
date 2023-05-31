package br.ufrn.imd.microservices.msloan.simulation;

import br.ufrn.imd.microservices.msloan.util.Endpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoint.SIMULATION)
public class SimulationController {

    @GetMapping
    public ResponseEntity<Simulation> simulation(){
       return ResponseEntity.ok(new Simulation());
    }
}
