package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.service.exption.DuplicationExption;
import com.codegym.service.exption.NotFoundException;


public interface CustomerService {
    Iterable<Customer> findAll();
    Customer findById(Long id) throws NotFoundException;
    Customer save(Customer customer) throws DuplicationExption;
    void remove(Long id);
//    Page<Customer> findAll(Pageable pageable);
//    Page<Customer> findAllByFirstNameContaining(String name  , Pageable pageable);

}
