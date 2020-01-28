package de.viadee.camunda.connected.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.viadee.camunda.connected.calculator.model.CalculateModel;
import de.viadee.camunda.connected.calculator.service.CalculatorService;

@RestController
@RequestMapping("/sync")
public class SyncCalculatorController {

    @Autowired
    private CalculatorService service;

    @PostMapping("/calc")
    public ResponseEntity<Number> calculate(@RequestBody final CalculateModel model) {
        return ResponseEntity.ok(this.service.calculate(model.getNum1(), model.getNum2(), model.getOperator()));
    }

}
