package com.excercise.orders_api.controller;

import com.excercise.orders_api.dtos.PayloadDTO;
import com.excercise.orders_api.dtos.ResponseOrderDTO;
import com.excercise.orders_api.service.ServiceOrder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private ServiceOrder serviceOrder;

    @PostMapping("/processOrder")
    public ResponseEntity<ResponseOrderDTO> processOrder(@RequestBody @Valid PayloadDTO payloadData) {
        ResponseOrderDTO response = serviceOrder.manageOrder(payloadData);
        return ResponseEntity.ok(response);
    }


}
