package com.example.demospring.restapi.api.v1.contract;

import com.example.demospring.restapi.domain.model.Order;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderResponse {

    private Long id;
    private BigDecimal total;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    protected OrderResponse() {
    }

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.total = order.total();
        this.createdAt = order.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
