package br.ufrn.imd.microservices.msloan.simulation.controller;

import br.ufrn.imd.microservices.msloan.simulation.dto.SimulationDTO;
import br.ufrn.imd.microservices.msloan.util.Endpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping(Endpoint.SIMULATION)
public class SimulationController {

    @GetMapping
    public ResponseEntity<SimulationDTO> simulation() {
        return ResponseEntity.ok(new SimulationDTO(UUID.randomUUID(), BigDecimal.ONE, Integer.MAX_VALUE));
    }
}
