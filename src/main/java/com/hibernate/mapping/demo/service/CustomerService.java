package com.hibernate.mapping.demo.service;


import com.hibernate.mapping.demo.model.Customer;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerService {

    SessionFactory sessionFactory;

    CustomerService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Customer createCustomer(Customer customer) {
        sessionFactory.getCurrentSession().persist(customer);
        return customer;
    }


    @Transactional
    public List<Customer> getAllCustomer() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Customer", Customer.class)
                .getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Customer getCustomerById(int id) {
        return (Customer) sessionFactory.getCurrentSession()
                .createQuery("FROM Customer d WHERE d.id = :id", Customer.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    public void deleteCustomerById(int id) {
        Customer customer = getCustomerById(id);
        sessionFactory.getCurrentSession()
                .remove(customer);
        customer.setName("Eddy"); // detached object
        sessionFactory.getCurrentSession().delete(customer);
    }

    @Transactional
    public void updateCustomer(Customer customerNew, int id) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(customerNew);
    }


}