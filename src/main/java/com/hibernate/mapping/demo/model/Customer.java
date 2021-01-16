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
public class Customer {

    @Version
    int version;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String surname;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pc_id")
    Pc pc;

    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "Cpu_id")
    @JoinTable(name = "cpu_author",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "cpu_id") })
    List<Cpu> cpu;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "GraphicCard_id")
    List<GraphicCard> graphicCards;


}
