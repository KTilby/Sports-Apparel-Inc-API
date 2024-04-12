package io.catalyte.training.sportsproducts.domains.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import io.catalyte.training.sportsproducts.exceptions.BadRequest;
import io.catalyte.training.sportsproducts.exceptions.ResourceNotFound;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.web.client.HttpClientErrorException.Conflict;
import org.springframework.web.server.ResponseStatusException;

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
        "38435",
        "098-765-4321");

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
  void updateCustomerUpdatesCustomer() {
    Customer result = customerService.updateCustomer(1L, testCustomer);
    assertEquals(testCustomer, result);
  }

  @Test
  void updateCustomerThrowsResourceNotFound() {
    when(customerRepository.findById(196L)).thenReturn(Optional.empty());
    Assertions.assertThrows(ResourceNotFound.class, () -> customerService.updateCustomer(196L, testCustomer));
  }

  @Test
  void updateCustomerThrowsDataAccessException() {
    doThrow(EmptyResultDataAccessException.class).when(customerRepository).findById(123L);
    assertThrows(ServerError.class, () -> customerService.updateCustomer(123L, testCustomer));
  }

  @Test
  void updateCustomerThrowsDataAccessExceptionOnSave() {
    doThrow(EmptyResultDataAccessException.class).when(customerRepository).save(testCustomer);
    assertThrows(ServerError.class, () -> customerService.updateCustomer(123L, testCustomer));
  }

  @Test
  void createCustomerCreatesCustomer() {
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
        "38435",
        "123-456-7890");
    when(customerService.createCustomer(natsu))
        .thenReturn(natsu);
    Assertions.assertNotNull(customerService.createCustomer(natsu));
  }

  @Test
  void createCustomerThrowsBadRequest() {
    Customer natsu = new Customer(
        null,
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
        "38435",
        "123-456-7890");
    Assertions.assertThrows(ResponseStatusException.class, () -> customerService.createCustomer(natsu));
  }

  @Test
  void createCustomerThrowsDataAccessException() {
    doThrow(EmptyResultDataAccessException.class).when(customerRepository).findByEmail(testCustomer.email);
    assertThrows(ServerError.class, () -> customerService.createCustomer(testCustomer));
  }

  @Test
  void createCustomerThrowsConflict() {
    when(customerRepository.findByEmail(testCustomer.email)).thenReturn(testCustomer);
    Assertions.assertThrows(ResponseStatusException.class, () -> customerService.createCustomer(testCustomer));
  }

  @Test
  void createCustomerThrowsDataAccessExceptionOnSave() {
    doThrow(EmptyResultDataAccessException.class).when(customerRepository).save(testCustomer);
    assertThrows(ServerError.class, () -> customerService.createCustomer(testCustomer));
  }

  @Test
  void getCustomerByEmailGetsCustomer() {
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
        "38435",
        "000-000-0000");
    when(customerRepository.findByEmail("salamander@fairytail.org"))
        .thenReturn(testCustomer);
    Assertions.assertNotNull(customerService.getCustomerByEmail("salamander@fairytail.org"));
  }

  @Test
  void getCustomerByEmailThrowsDataAccessException() {
    doThrow(EmptyResultDataAccessException.class).when(customerRepository).findByEmail(testCustomer.email);
    assertThrows(ServerError.class, () -> customerService.getCustomerByEmail(testCustomer.email));
  }

  @Test
  void getCustomerByEmailThrowsResourceNotFound() {
    when(customerRepository.findByEmail(testCustomer.email)).thenReturn(null);
    Assertions.assertThrows(ResourceNotFound.class, () -> customerService.getCustomerByEmail(testCustomer.email));
  }
}