package br.ufrn.imd.microservices.msloan.requirementdetail.controller;

import br.ufrn.imd.microservices.msloan.payroll.PayrollStatus;
import br.ufrn.imd.microservices.msloan.requirementdetail.dto.RequirementDetailDTO;
import br.ufrn.imd.microservices.msloan.util.Endpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(Endpoint.REQUIREMENT_DETAIL)
public class RequirementDetailController {

    @GetMapping("/{id}")
    public ResponseEntity<RequirementDetailDTO> save(@PathVariable UUID id) {
        return ResponseEntity.ok(new RequirementDetailDTO(PayrollStatus.DISAPPROVED, false));
    }
}