package me.iannguyen.springdemo.service;

import java.util.List;

import me.iannguyen.springdemo.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int customerID);

	public void delete(int customerID);

	public List<Customer> searchCustomers(String theSearchName);

}
