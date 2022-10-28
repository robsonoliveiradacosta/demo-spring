package com.example.demospring.restapi.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customer_order", schema = "public")
public class Order {

    @Id
    @SequenceGenerator(name = "orderSeqGen", sequenceName = "customer_order_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "orderSeqGen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> items = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public BigDecimal total() {
        return items.stream()
                .map(item -> item.total())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public void addOrderItem(Product product, Short quantity) {
        items.add(new OrderItem(this, product, quantity));
    }
}
