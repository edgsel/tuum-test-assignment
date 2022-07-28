package com.edgsel.tuumtestassignment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StatusController {

    @RequestMapping("/api/v1/status")
    public ResponseEntity<Map<String, Boolean>> getApiStatus() {
        Map<String, Boolean> response = new HashMap<>();

        response.put("ok", true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
