package com.example.demospring.restapi.api.v1.contract;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductRequest {

    @NotBlank(message = "name required")
    private String name;

    @NotNull(message = "price required")
    @DecimalMin(value = "0.01", message = "invalid price")
    private BigDecimal price;

    protected ProductRequest() {
    }

    public ProductRequest(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
