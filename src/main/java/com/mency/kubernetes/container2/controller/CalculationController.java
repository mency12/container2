package com.mency.kubernetes.container2.controller;

import com.mency.kubernetes.container2.model.CalculationRequest;
import com.mency.kubernetes.container2.model.CalculationResponse;
import com.mency.kubernetes.container2.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculationController {

    private final CalculationService calculationService;

    @Autowired
    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/calculate-sum")
    public ResponseEntity<CalculationResponse> calculateSum(@RequestBody CalculationRequest request) {
        CalculationResponse response = calculationService.calculateSum(
                request.getFilePath(),
                request.getProduct()
        );

        if (response.getError() != null) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }
}