package com.example.demospring.restapi.api.v1.contract;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderRequest {

    @NotEmpty
    @Valid
    private List<OrderItemRequest> items;

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public static class OrderItemRequest {

        @NotNull
        private Long productId;

        @Min(1)
        private Short quantity;

        public Long getProductId() {
            return productId;
        }

        public Short getQuantity() {
            return quantity;
        }
    }
}
