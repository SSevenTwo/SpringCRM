package me.iannguyen.springdemo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import me.iannguyen.springdemo.entity.Customer;
import me.iannguyen.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//Inject customer service
	@Autowired // Spring will find any component and if it fits - put it in
	private CustomerService customerService; 

	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		
		// Get customers
		List<Customer> customers = customerService.getCustomers();
		
		// Add to model
		theModel.addAttribute("customers",customers);
		
		return "customer-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String addCustomer(Model theModel) {
		
		// Create new customer to send to form
		Customer newCustomer = new Customer();
		
		// Bind to model
		theModel.addAttribute("customer",newCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer") //customer model attribute on the form
	public String addCustomer(@ModelAttribute("customer")Customer customer) {
		
		// Saving or updating the customer can use the same method
		// Click through to see we used session.saveOrUpdate()
		// If an id is present it will update.
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String updateCustomer(@RequestParam("customerID") int customerID,
								Model theModel) {
		
		// Get customer
		Customer customer = customerService.getCustomer(customerID);
		
		// Set model attribute to the customer so the form is prepopulated
		theModel.addAttribute("customer",customer);
		
		return "customer-form";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerID") int customerID,
								Model theModel) {
		
		customerService.delete(customerID);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                    Model theModel) {

        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
                
        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "customer-list";        
    }
	
	public void testJDBC() {
		String user = "springstudent";
		String pass = "springstudent";
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		// get connection to database
		try {
			
			System.out.println("Attempting to connect to database: " + jdbcUrl);
			
			Class.forName(driver);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl,user,pass);
			
			System.out.println("Successfully connected.");
			
			myConn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
