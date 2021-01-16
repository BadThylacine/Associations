package com.hibernate.mapping.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphicCard {



    @Version
    int version;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String memory;
    String frequency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pc_id")
    Pc pc;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    List<Customer> customers;
}
