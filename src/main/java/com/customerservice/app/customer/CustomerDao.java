package com.customerservice.app.customer;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
@Transactional
public interface CustomerDao extends CrudRepository<Customer, String> {
    @Query("SELECT c FROM Customer AS c WHERE c.deleted=NULL AND c.id=?1")
    public Customer findById(Long id);
    @Override 
    @Query("SELECT c FROM Customer AS c WHERE c.deleted=NULL")
    public Iterable<Customer> findAll();
}