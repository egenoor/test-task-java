package com.customerservice.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@SpringBootApplication
public class CustomerServiceApplication {
	@Autowired
	private CustomerDao customerDao;
	@RequestMapping("/")
	String home(){
		return "hello";
	}

	@RequestMapping("/addCustomer")
	String add(String firstName,
				String lastName,
				String dateOfBirth,
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

	@RequestMapping("/getAllCustomers")
	public Iterable<Customer> getAllCustomers() {
		return customerDao.findAll();
	}

	@RequestMapping("/getCustomer")
	public Customer getCustomer(String username){
		return customerDao.findByUsername(username);
	}

	@RequestMapping("/editCustomer")
	public String editCustomer(String firstName,
								String lastName,
								String dateOfBirth,
								String username,
								String password){
		Customer customer=getCustomer(username);
		customer.firstName=firstName;
		customer.lastName=lastName;
		customer.dateOfBirth=dateOfBirth;
		customer.username=username;
		customer.password=password;
		customerDao.save(customer);
		return "Edited "+username;
	}

	@RequestMapping("/deleteCustomer")
	public String deleteCustomer(String username){
		Customer customer=getCustomer(username);
		customer.deleted=true;
		return "Deleted "+username;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
