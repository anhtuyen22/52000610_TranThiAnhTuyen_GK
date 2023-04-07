package com.trantuyen.springecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "status", nullable = false, length = 50)
    private String status;
    @Basic
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Basic
    @Column(name = "company_name", nullable = true, length = 50)
    private String companyName;
    @Basic
    @Column(name = "country", nullable = true, length = 50)
    private String country;
    @Basic
    @Column(name = "address", nullable = true, length = 50)
    private String address;
    @Basic
    @Column(name = "phone", nullable = true, length = 50)
    private String phone;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    private BigDecimal price;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customerByCustomerId;
    @OneToMany(mappedBy = "orderByOrderId")
    private Set<OrderDetail> orderDetailsById = new HashSet<>();

}
