package io.catalyte.training.sportsproducts.domains.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

class CustomerServiceImplTest {

  @Mock
  CustomerRepository customerRepository;

  @InjectMocks
  CustomerServiceImpl customerService;

  Customer testCustomer;

  List<Customer> testList = new ArrayList<>();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    testCustomer = new Customer(
        1L,
        "salamander@fairytail.org",
        "P@$$w0rd",
        "Customer",
        "Natsu",
        "Dragneel",
        "584 Lilly Dr",
        "",
        "Magnolia",
        "CV",
        "38435",
        "36 Snapdragon Way",
        "",
        "Magnolia",
        "CV",
        "38435");

    testList = new ArrayList<>();
    testList.add(testCustomer);
    customerService.createCustomer(testCustomer);

    when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of(testList.get(0)));
    when(customerRepository.saveAll(anyCollection())).thenReturn(testList);
    when(customerRepository.save(any(Customer.class))).thenReturn(testList.get(0));
    when(customerRepository.findAll()).thenReturn(testList);
    when(customerRepository.findAll(any(Example.class))).thenReturn(testList);
    when(customerRepository.existsById(1L)).thenReturn(true);
    when(customerRepository.existsById(1L)).thenReturn(true);
  }



  @Test
  void updateCustomer() {
    Customer result = customerService.updateCustomer(1L, testCustomer);
    assertEquals(testCustomer, result);
  }

  @Test
  void createCustomer() {
    Customer natsu = new Customer(
        "salamander@fairytail.org",
        "P@$$w0rd",
        "Customer",
        "Natsu",
        "Dragneel",
        "584 Lilly Dr",
        "",
        "Magnolia",
        "CV",
        "38435",
        "36 Snapdragon Way",
        "",
        "Magnolia",
        "CV",
        "38435");
    when(customerService.createCustomer(natsu))
        .thenReturn(natsu);
    Assertions.assertNotNull(customerService.createCustomer(natsu));
  }

  @Test
  void getCustomerByEmail() {
    Customer natsu = new Customer(
        "salamander1@fairytail.org",
        "P@$$w0rd",
        "Customer",
        "Natsu",
        "Dragneel",
        "584 Lilly Dr",
        "",
        "Magnolia",
        "CV",
        "38435",
        "36 Snapdragon Way",
        "",
        "Magnolia",
        "CV",
        "38435");
    when(customerRepository.findByEmail("salamander@fairytail.org"))
        .thenReturn(testCustomer);
    Assertions.assertNotNull(customerService.getCustomerByEmail("salamander@fairytail.org"));
  }
}