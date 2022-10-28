package com.example.demospring.restapi.service;

import com.example.demospring.restapi.api.v1.contract.OrderRequest;
import com.example.demospring.restapi.api.v1.contract.OrderResponse;
import com.example.demospring.restapi.domain.exception.EntityNotFoundException;
import com.example.demospring.restapi.domain.model.Order;
import com.example.demospring.restapi.domain.repository.OrderRepository;
import com.example.demospring.restapi.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public OrderResponse save(OrderRequest request) {
        Order order = new Order();
        request.getItems().forEach(orderItemRequest -> {
            var product = productRepository.findById(orderItemRequest.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            String.format("Product with id %d, not found", orderItemRequest.getProductId())));
            order.addOrderItem(product, orderItemRequest.getQuantity());
        });
        return new OrderResponse(orderRepository.save(order));
    }

    public List<OrderResponse> listAll() {
        List<Order> orders = orderRepository.findAll();
        orders.forEach(order -> {
            System.out.println(order.getItems().size());
        });
        return orders.stream().map(OrderResponse::new).toList();
    }
}
