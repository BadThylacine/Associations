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
@RequestMapping("/pc")
public class PcController {

    PcService pcService;
    CustomerService customerService;
    CpuService cpuService;
    GraphicCardService graphicCardService;

    PcController(PcService pcService) {
        this.pcService = pcService;
    }

    @PostMapping
    public Pc postPc(@RequestBody Pc pc) {
        pcService.createPc(pc);
        return pc;
    }

    @GetMapping("/all")
    public List<Pc> getAllPcs() {
        return pcService.getAllPc();
    }

    @GetMapping("/byid/{id}")
    public Pc getPcById(@PathVariable int id) {
        return pcService.getPcById(id);
    }

    @DeleteMapping("/{id}")
    public String deletePcById(@PathVariable int id) {
        pcService.deletePcById(id);
        return "Deleted: " + id;
    }

    @PostMapping("addpc/{cpuid}/{customerid}/{graphiccardid}")
    public Pc getCpuAndCustomerAndGraphicCard(@PathVariable int cpuid, @PathVariable int customerid, @PathVariable int graphiccardid, @RequestBody Pc pc) {
        Cpu cpu = cpuService.getCpuById(cpuid);
        Customer customer = customerService.getCustomerById(customerid);
        GraphicCard graphicCard = graphicCardService.getGraphicCardById(graphiccardid);
        pc.setCpu(cpu);
        pc.setCustomer(customer);
        pc.setGraphicCardList((List<GraphicCard>) graphicCard);
        return pcService.createPc(pc);

    }
}