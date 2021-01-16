package com.hibernate.mapping.demo.controller;


import com.hibernate.mapping.demo.model.Cpu;
import com.hibernate.mapping.demo.model.Customer;
import com.hibernate.mapping.demo.model.GraphicCard;
import com.hibernate.mapping.demo.model.Pc;
import com.hibernate.mapping.demo.service.CpuService;
import com.hibernate.mapping.demo.service.CustomerService;
import com.hibernate.mapping.demo.service.GraphicCardService;
import com.hibernate.mapping.demo.service.PcService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/graphiccard")
public class GraphicCardController {

    PcService pcService;
    CustomerService customerService;
    CpuService cpuService;
    GraphicCardService graphicCardService;

    GraphicCardController(GraphicCardService graphicCardService) {
        this.graphicCardService = graphicCardService;
    }

    @PostMapping
    public GraphicCard createGraphicCard(@RequestBody GraphicCard graphicCard) {
        graphicCardService.createGraphicCard(graphicCard);
        return graphicCard;
    }

    @GetMapping("/all")
    public List<GraphicCard> getAllGraphicCards() {
        return graphicCardService.getAllGraphicCard();
    }

    @GetMapping("/byid/{id}")
    public GraphicCard getGraphicCardById(@PathVariable int id) {
        return graphicCardService.getGraphicCardById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteGraphicCardById(@PathVariable int id) {
        graphicCardService.deleteGraphicCardById(id);
        return "Deleted: " + id;
    }

    @PostMapping("addgc/{pcid}/{customerid}")
    public GraphicCard getCpuAndCustomerAndGraphicCard(@PathVariable int pcid, @PathVariable int customerid, @RequestBody GraphicCard graphicCard) {
        Pc pc = pcService.getPcById(pcid);
        Customer customer = customerService.getCustomerById(customerid);
        graphicCard.setPc(pc);
        graphicCard.setCustomers((List<Customer>) customer);
        return graphicCardService.createGraphicCard(graphicCard);

    }
}
