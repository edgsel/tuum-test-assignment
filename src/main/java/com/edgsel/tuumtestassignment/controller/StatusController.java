package com.edgsel.tuumtestassignment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
public class StatusController {

    @RequestMapping(value = "/status", method = GET)
    public ResponseEntity<Map<String, Boolean>> getApiStatus() {
        Map<String, Boolean> response = new HashMap<>();

        response.put("ok", true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
