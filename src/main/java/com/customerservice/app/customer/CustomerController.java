package com.customerservice.app.customer;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;


@RestController    
public class CustomerController{
    @Autowired
	private CustomerDao customerDao;

	@RequestMapping(value = "/api/addCustomer", method = RequestMethod.POST)
	public void add(@RequestBody Map<String, String> payload){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		LocalDate localDate = LocalDate.parse(payload.get("dateOfBirth"), formatter);
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		Customer customer=new Customer();
		customer.firstName=payload.get("firstName");
		customer.lastName=payload.get("lastName");
		customer.dateOfBirth=date;
		customer.username=payload.get("username");
		customer.password=payload.get("password");
		customerDao.save(customer);
		System.out.println("Added "+payload.get("username"));
	}

	@RequestMapping(value = "/api/getAllCustomers", method = RequestMethod.GET)
	public Iterable<Customer> getAllCustomers() {
		return customerDao.findAll();
	}

	@RequestMapping(value = "/api/getCustomer", method = RequestMethod.GET)
	public Customer getCustomer(Long id){
		return customerDao.findById(id);
	}

	@RequestMapping(value = "/api/editCustomer", method = RequestMethod.POST)
	public void editCustomer(@RequestBody Map<String, String> payload){

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		LocalDate localDate = LocalDate.parse(payload.get("dateOfBirth"), formatter);
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		Customer customer=getCustomer(Long.parseLong(payload.get("id")));
		customer.firstName=payload.get("firstName");
		customer.lastName=payload.get("lastName");
		customer.dateOfBirth=date;
		customer.username=payload.get("username");
		customerDao.save(customer);
		System.out.println("Edited "+ payload.get("id"));
	}

	@RequestMapping(value = "/api/deleteCustomer", method = RequestMethod.POST)
	public void deleteCustomer(@RequestBody Map<String, String> payload){
		Customer customer=getCustomer(Long.parseLong(payload.get("id")));
		customer.deleted="deleted";
		customer.username=null;
		customerDao.save(customer);
		System.out.println("Deleted customer with id: " + payload.get("id"));
	}
}
    