package me.iannguyen.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.iannguyen.springdemo.entity.Customer;

@Repository // for component scanning and translating jdbc exceptions
public class CustomerDAOImpl implements CustomerDAO {

	// Injecting the bean from our config file
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	// @Transactional - Handles all the begin/commit transaction
	public List<Customer> getCustomers() {

		// get session
		Session session = sessionFactory.getCurrentSession();

		// make query
		Query<Customer> query = session.createQuery("FROM Customer ORDER BY lastName", Customer.class);

		// execute query
		List<Customer> customers = query.getResultList();

		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int customerID) {
		// get session
		Session session = sessionFactory.getCurrentSession();

		// make query - can also just do session.get
		Query<Customer> query = session.createQuery("FROM Customer WHERE id =:customerID", Customer.class);

		query.setParameter("customerID", customerID);

		// execute query
		Customer customer = query.getSingleResult();

		return customer;
	}

	@Override
	public void delete(int customerID) {
		// get session
		Session session = sessionFactory.getCurrentSession();

		// make query - can also just do session.get
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("DELETE FROM Customer WHERE id =:customerID");

		query.setParameter("customerID", customerID);
		
		// execute query
		query.executeUpdate();

	}
	
	@Override
    public List<Customer> searchCustomers(String theSearchName) {

        Session currentSession = sessionFactory.getCurrentSession();
        
        // instantiate query
		@SuppressWarnings("rawtypes")
        Query query = null;
        
        // only search by name if theSearchName is not empty
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            query = currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            query.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty so just get all customers
            query = currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
		@SuppressWarnings("unchecked")
        List<Customer> customers = query.getResultList();
                
        // return the results        
        return customers;
        
    }

}
