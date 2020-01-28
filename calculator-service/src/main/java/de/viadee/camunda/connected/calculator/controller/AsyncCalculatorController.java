package de.viadee.camunda.connected.calculator.controller;

import org.apache.camel.Produce;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.viadee.camunda.connected.calculator.model.CallbackCalculateModel;
import de.viadee.camunda.connected.calculator.route.CalculateRouteProxy;

@RestController
@RequestMapping("/async")
public class AsyncCalculatorController {

    @Produce(uri = "seda:asyncCalc")
    private CalculateRouteProxy proxy;

    @PostMapping("/calc")
    public ResponseEntity<String> calculate(@RequestBody final CallbackCalculateModel model) {
        this.proxy.startCalc(model);
        return ResponseEntity.ok("Calculation started");
    }
}
