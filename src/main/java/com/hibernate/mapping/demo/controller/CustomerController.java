package com.hibernate.mapping.demo.controller;

import com.hibernate.mapping.demo.model.Cpu;
import com.hibernate.mapping.demo.model.Customer;
import com.hibernate.mapping.demo.model.GraphicCard;
import com.hibernate.mapping.demo.model.Pc;
import com.hibernate.mapping.demo.service.CpuService;

import com.hibernate.mapping.demo.service.GraphicCardService;
import com.hibernate.mapping.demo.service.PcService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    CpuService cpuService;
    PcService pcService;
    GraphicCardService graphicCardService;

    CustomerController(CpuService cpuService) {
        this.cpuService = cpuService;
    }

    @PostMapping
    public Cpu createCpu(@RequestBody Cpu cpu) {
        cpuService.createCpu(cpu);
        return cpu;
    }

    @GetMapping("/all")
    public List<Cpu> getAllCpus() {
        return cpuService.getAllCpu();
    }

    @GetMapping("/byid/{id}")
    public Cpu getCpuById(@PathVariable int id) {
        return cpuService.getCpuById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCpuById(@PathVariable int id) {
        cpuService.deleteCpuById(id);
        return "Deleted: " + id;
    }

    @PostMapping("addcust/{pcid}/{cpuid}/{graphiccardid}")
    public Cpu getPcAndCustomer(@PathVariable int pcid, @PathVariable int cpuid, @PathVariable int graphiccardid, @RequestBody Customer customer) {
        Pc pc = pcService.getPcById(pcid);
        Cpu cpu = cpuService.getCpuById(cpuid);
        GraphicCard graphicCard = graphicCardService.getGraphicCardById(graphiccardid);
        customer.setPc(pc);
        customer.setCpu((List<Cpu>) cpu);
        customer.setGraphicCards((List<GraphicCard>) graphicCard);
        return cpuService.createCpu(cpu);

    }
}