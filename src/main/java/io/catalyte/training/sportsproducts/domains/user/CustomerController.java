package io.catalyte.training.sportsproducts.domains.user;

import static io.catalyte.training.sportsproducts.constants.Paths.CUSTOMERS_PATH;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = CUSTOMERS_PATH)
public class CustomerController {

  Logger logger = LogManager.getLogger(CustomerController.class);

  @Autowired
  private final CustomerServiceImpl customerService;


  public CustomerController(CustomerServiceImpl customerService) {
    this.customerService = customerService;
  }

  // Methods

  /**
   * Controller method for logging the customer in
   *
   * @param customer        Customer to login
   * @return Customer
   */
  @PostMapping()
  public ResponseEntity<Customer> createCustomer(
      @RequestBody Customer customer
  ) {
    logger.info("Request received for createCustomer");
    return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
  }

  /**
   * Controller method for updating the customer
   *
   * @param id          Id of the customer to update
   * @param customer        customer to update
   * @return Customer - Updated customer
   */
  @PutMapping(path = "/{id}")
  public ResponseEntity<Customer> updateCustomer(
      @PathVariable Long id,
      @RequestBody Customer customer
  ) {
    logger.info("Request received for updateCustomer");
    return new ResponseEntity<>(customerService.updateCustomer(id, customer), HttpStatus.OK);
  }

  /**
   * Controller method for getting a customer by email
   *
   * @param email Email to get user by
   * @return Customer found in database
   */
  @GetMapping(path = "/{email}")
  public ResponseEntity<Customer> getCustomerByEmail(
      @PathVariable String email
  ) {
    logger.info("Request received for getCustomerByEmail");
    return new ResponseEntity<>(customerService.getCustomerByEmail(email), HttpStatus.OK);
  }
}
