package com.example.demospring.restapi.service;

import com.example.demospring.restapi.api.v1.contract.ProductRequest;
import com.example.demospring.restapi.api.v1.contract.ProductResponse;
import com.example.demospring.restapi.domain.exception.EntityNotFoundException;
import com.example.demospring.restapi.domain.model.Product;
import com.example.demospring.restapi.domain.repository.ProductRepository;
import com.example.demospring.restapi.mapper.ProductMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProductResponse save(ProductRequest request) {
        Product product = mapper.map(request);
        repository.save(product);
        return mapper.map(product);
    }

    public List<ProductResponse> listAll() {
        List<Product> products = repository.findAll();
        return products.stream().map(mapper::map).toList();
    }

    public ProductResponse findById(Long id) {
        Product product = find(id);
        return mapper.map(product);
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product product = find(id);
        mapper.update(request, product);
        repository.save(product);
        return mapper.map(product);
    }

    public void remove(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Product with id %d, not found", id));
        }
    }

    private Product find(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Product with id %d, not found", id)));
    }
}
