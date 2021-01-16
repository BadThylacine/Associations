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
public class Cpu {

    @Version
    int version;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String cores;
    String frequency;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pc_id")
    Pc pc;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cpu_author",
            joinColumns = { @JoinColumn(name = "cpu_id") },
            inverseJoinColumns = { @JoinColumn(name = "customer_id") })


    //@JoinColumn(name = "customer_id")
    List<Customer> customer;
}
