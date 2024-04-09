package io.catalyte.training.sportsproducts.domains.user;

public interface CustomerService {

  public Customer updateCustomer(Long id, Customer customer);

  public Customer createCustomer(Customer customer);

  public Customer getCustomerByEmail(String email);

}
