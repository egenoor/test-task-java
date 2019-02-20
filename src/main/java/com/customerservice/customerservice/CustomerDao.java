package com.customerservice.customerservice;
import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
@Transactional
public interface CustomerDao extends CrudRepository<Customer, String> {
    public Customer findByUsername(String username);
    @Override 
    @Query("SELECT c FROM Customer AS c WHERE c.deleted=false")
    public Iterable<Customer> findAll();
}