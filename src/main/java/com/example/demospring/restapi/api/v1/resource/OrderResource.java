package com.example.demospring.restapi.api.v1.resource;

import com.example.demospring.restapi.api.v1.contract.OrderRequest;
import com.example.demospring.restapi.api.v1.contract.OrderResponse;
import com.example.demospring.restapi.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderResource {

    private final OrderService service;

    public OrderResource(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.created(null).body(service.save(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

}
