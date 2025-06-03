package com.excercise.orders_api.controller;

import com.excercise.orders_api.dtos.PayloadDTO;
import com.excercise.orders_api.dtos.ResponseOrderDTO;
import com.excercise.orders_api.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/processOrder")
    public ResponseEntity<ResponseOrderDTO> processOrder(@RequestBody @Valid PayloadDTO payloadData) {
        ResponseOrderDTO response = orderService.manageOrder(payloadData);
        return ResponseEntity.ok(response);
    }

}
