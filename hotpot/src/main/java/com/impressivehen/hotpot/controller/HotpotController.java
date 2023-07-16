package com.impressivehen.hotpot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/hotpot")
@RestController
public class HotpotController {

    public static final Logger logger = LoggerFactory.getLogger(HotpotController.class);

    @GetMapping("/menu")
    public ResponseEntity<Map<String, Double>> get() {
        Map<String, Double> menu = new HashMap<>();
        menu.put("Spicy Beef", 14.5);
        menu.put("Curry Milk", 13.0);
        menu.put("Stinky Tofu", 21.5);

        logger.info("Retrieved menu info: " + menu);

        return new ResponseEntity<>(menu, HttpStatus.ACCEPTED);
    }
}
