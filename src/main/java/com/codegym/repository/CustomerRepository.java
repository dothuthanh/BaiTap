package com.codegym.repository;

import com.codegym.model.Customer;

import com.codegym.model.Province;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Iterable<Customer> findAllByProvince(Province province);


}
