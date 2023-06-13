package br.ufrn.imd.microservices.msloan.simulation;

import br.ufrn.imd.microservices.msloan.util.Endpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping(Endpoint.SIMULATION)
public class SimulationController {

    @GetMapping
    public ResponseEntity<SimulationDTO> simulation(@RequestParam String userId,
                                                    @RequestParam BigDecimal totalValue,
                                                    @RequestParam int installments ) {
        return ResponseEntity.ok();
    }
}
