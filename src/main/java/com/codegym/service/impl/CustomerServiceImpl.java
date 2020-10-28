package com.codegym.service.impl;

import com.codegym.model.Customer;
import com.codegym.repository.CustomerRepository;
import com.codegym.service.CustomerService;
import com.codegym.service.exption.DuplicationExption;
import com.codegym.service.exption.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) throws NotFoundException {
//        return customerRepository.findOne(id);
        Customer target = customerRepository.findOne(id);
        if(target == null) {
            throw new NotFoundException();
        }
        return target;
    }

//    @Override
//    public void save(Customer customer) {
//        customerRepository.save(customer);
//    }
@Override
public Customer save(Customer customer) throws DuplicationExption {
    try {
        return customerRepository.save(customer);
    } catch (DataIntegrityViolationException e) {
        throw new DuplicationExption();
    }
}
    @Override
    public void remove(Long id) {
        customerRepository.delete(id);
    }
//    @Override
//    public Page<Customer> findAll(Pageable pageable) {
//        return customerRepository.findAll(pageable);
    }
//    @Override
//    public Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable) {
//        return customerRepository.findAllByFirstNameContaining(firstname, pageable);
//    }
