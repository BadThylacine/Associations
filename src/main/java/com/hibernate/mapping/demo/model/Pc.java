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
public class Pc {

    @Version
    int version;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String motherboard;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    Customer customer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cpu_id")
    Cpu cpu;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "GraphicCard_id")
    List<GraphicCard> graphicCardList;

    int ram;
    int ssd_rom;
    int hdd_rom;


}
