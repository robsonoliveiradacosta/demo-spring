package com.example.demospring.restapi.api.v1.resource;

import com.example.demospring.restapi.api.v1.contract.ProductRequest;
import com.example.demospring.restapi.api.v1.contract.ProductResponse;
import com.example.demospring.restapi.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductResource {

    private final ProductService service;

    public ProductResource(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody @Valid ProductRequest request) {
        ProductResponse response = service.save(request);
        return ResponseEntity.created(null).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody @Valid ProductRequest request) {
        ProductResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }
}
