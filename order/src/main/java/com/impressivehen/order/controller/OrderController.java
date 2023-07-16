package com.impressivehen.order.controller;

import com.impressivehen.order.client.HotpotClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/order")
@RestController
public class OrderController {

    public static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private HotpotClient hotpotClient;

    @GetMapping("/price/{name}")
    public ResponseEntity<Double> get(@PathVariable("name") String name) {
        Map<String, Double> menu = hotpotClient.getMenu();

        if (menu.containsKey(name)) {
            logger.info("Price retrieved for: " + name);
            return new ResponseEntity<>(menu.get(name), HttpStatus.ACCEPTED);
        }

        logger.warn("Price not found for: " + name);
        return ResponseEntity.badRequest().build();
    }
}
