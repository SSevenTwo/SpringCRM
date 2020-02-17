package me.iannguyen.springdemo.dao;

import java.util.List;

import me.iannguyen.springdemo.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int customerID);

	public void delete(int customerID);

	public List<Customer> searchCustomers(String theSearchName);

}
