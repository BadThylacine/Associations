package com.hibernate.mapping.demo.controller;

import com.hibernate.mapping.demo.model.Cpu;
import com.hibernate.mapping.demo.model.Customer;
import com.hibernate.mapping.demo.model.Pc;
import com.hibernate.mapping.demo.model.Pc;
import com.hibernate.mapping.demo.service.CpuService;
import com.hibernate.mapping.demo.service.CustomerService;
import com.hibernate.mapping.demo.service.PcService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cpu")
public class CpuController {

    CpuService cpuService;
    PcService pcService;
    CustomerService customerService;

    CpuController(CpuService cpuService) {
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

    @PostMapping("addcpu/{pcid}/{customerid}")
    public Cpu getPcAndCustomer(@PathVariable int pcid, @PathVariable int customerid, @RequestBody Cpu cpu) {
        Pc pc = pcService.getPcById(pcid);
        Customer customer = customerService.getCustomerById(customerid);
        cpu.setPc(pc);
        cpu.setCustomer((List<Customer>) customer);
        return cpuService.createCpu(cpu);

    }
}