package com.example.adminservice.entity;

import com.example.adminservice.entity.enums.OrderStatus;
import com.example.adminservice.entity.enums.PayType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(nullable = false)
    private Human courier;

    @OneToMany
    private List<OrderProduct> products;

    @ManyToOne
    private Human operator;

    @Enumerated(EnumType.STRING)
    private PayType payType;

    @Builder.Default
    @Column(scale = 2)
    private BigDecimal deliveryPrice = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Filial filial;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Human client;
}
