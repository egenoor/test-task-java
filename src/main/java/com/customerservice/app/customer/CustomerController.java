package com.customerservice.app.customer;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@RestController    
public class CustomerController{
    @Autowired
	private CustomerDao customerDao;

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	String add(String firstName,
				String lastName,
				Date dateOfBirth,
				String username,
				String password){
		Customer customer=new Customer();
		customer.firstName=firstName;
		customer.lastName=lastName;
		customer.dateOfBirth=dateOfBirth;
		customer.username=username;
		customer.password=password;
		customerDao.save(customer);
		return "Added "+username;
	}

	@RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
	public Iterable<Customer> getAllCustomers() {
		return customerDao.findAll();
	}

	@RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
	public Customer getCustomer(Long id){
		return customerDao.findById(id);
	}

	@RequestMapping(value = "/editCustomer", method = RequestMethod.POST)
	public String editCustomer(Long id,
								String firstName,
								String lastName,
								Date dateOfBirth,
								String username,
								String password){
		Customer customer=getCustomer(id);
		customer.firstName=firstName;
		customer.lastName=lastName;
		customer.dateOfBirth=dateOfBirth;
		customer.username=username;
		customer.password=password;
		customerDao.save(customer);
		return "Edited "+ username;
	}

	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
	public String deleteCustomer(Long id){
		Customer customer=getCustomer(id);
		customer.deleted="deleted";
		customer.username=null;
		customerDao.save(customer);
		return "Deleted customer with id: " + id;
	}
}
    