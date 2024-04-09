package io.catalyte.training.sportsproducts.domains.user;

import org.springframework.stereotype.Service;

import static io.catalyte.training.sportsproducts.constants.Roles.CUSTOMER;

import io.catalyte.training.sportsproducts.exceptions.ResourceNotFound;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final Logger logger = LogManager.getLogger(UserController.class);
  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  // METHODS

  /**
   * Updates customer given valid credentials
   *
   * @param id          Id of the customer to update
   * @param updatedCustomer Customer to update
   * @return Customer - Updated customer
   */
  @Override
  public Customer updateCustomer(Long id, Customer updatedCustomer) {

    // AUTHENTICATES CUSTOMER - SAME ID, SAME PERSON
//    boolean isAuthenticated = updatedCustomer.id.equals(id);

//    if (!isAuthenticated) {
//      logger.error("Email in the request body does not match email from JWT");
//      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//          "Email in the request body does not match email from JWT Token");
//    }

    // UPDATES CUSTOMER
    Customer existingCustomer;

    try {
      existingCustomer = customerRepository.findById(id).orElse(null);
    } catch (DataAccessException dae) {
      logger.error(dae.getMessage());
      throw new ServerError(dae.getMessage());
    }

    if (existingCustomer == null) {
      logger.error("Customer with id: " + id + " does not exist");
      throw new ResourceNotFound("Customer with id: " + id + " does not exist");
    }

    // TEMPORARY LOGIC TO PREVENT USER FROM UPDATING THEIR ROLE
    updatedCustomer.setRole(existingCustomer.getRole());

    // GIVE THE CUSTOMER ID IF NOT SPECIFIED IN BODY TO AVOID DUPLICATE CUSTOMERS
    if (updatedCustomer.getId() == null) {
      updatedCustomer.setId(id);
    }

    try {
      logger.info("Saved customer to");
      return customerRepository.save(updatedCustomer);
    } catch (DataAccessException dae) {
      logger.error(dae.getMessage());
      throw new ServerError(dae.getMessage());
    }

  }

  /**
   * Creates customer in the database, given email is not null and not taken
   *
   * @param customer Customer to create
   * @return Customer
   */
  @Override
  public Customer createCustomer(Customer customer) {

    String email = customer.getEmail();

    // CHECK TO MAKE SURE EMAIL EXISTS ON INCOMING CUSTOMER
    if (email == null) {
      logger.error("Customer must have an email field");
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User must have an email field");
    }

    // CHECK TO MAKE SURE USER EMAIL IS NOT TAKEN
    Customer existingCustomer;

    try {
      existingCustomer = customerRepository.findByEmail(customer.getEmail());
    } catch (DataAccessException dae) {
      logger.error(dae.getMessage());
      throw new ServerError(dae.getMessage());
    }

    if (existingCustomer != null) {
      logger.error("Email is taken");
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is taken");
    }

    customer.setRole(CUSTOMER);

    // SAVE CUSTOMER
    try {
      logger.info("Saved customer");
      return customerRepository.save(customer);
    } catch (DataAccessException dae) {
      logger.error(dae.getMessage());
      throw new ServerError(dae.getMessage());
    }

  }

  /**
   * Gets customer by an email
   *
   * @param email Email of the customer
   * @return The customer
   */
  @Override
  public Customer getCustomerByEmail(String email) {

    Customer customer;

    try {
      customer = customerRepository.findByEmail(email);
    } catch (DataAccessException dae) {
      logger.error(dae.getMessage());
      throw new ServerError(dae.getMessage());
    }

    if (customer == null) {
      throw new ResourceNotFound("Customer with email " + email + " does not exist.");
    }

    return customer;

  }
}
