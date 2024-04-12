package io.catalyte.training.sportsproducts.domains.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import io.catalyte.training.sportsproducts.data.ProductFactory;
import io.catalyte.training.sportsproducts.exceptions.ResourceNotFound;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.web.client.HttpServerErrorException.ServiceUnavailable;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ProductServiceImpl.class)
public class ProductServiceImplTest {

  @InjectMocks
  private ProductServiceImpl productServiceImpl;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private ProductRepository productRepository;

  Product testProduct;

  ProductFactory productFactory;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    productFactory = new ProductFactory();
    testProduct = productFactory.createRandomProduct();
    when(productRepository.findById(anyLong())).thenReturn(Optional.of(testProduct));
  }

  @Test
  public void getProductsThrowsDataAccessException(){
    doThrow(EmptyResultDataAccessException.class).when(productRepository).findAll(Example.of(testProduct));
    Assertions.assertThrows(ServerError.class, () -> productServiceImpl.getProducts(testProduct));
  }

  @Test
  public void getProductByIdReturnsProduct() {
    Product actual = productServiceImpl.getProductById(123L);
    assertEquals(testProduct, actual);
  }

  @Test
  public void getProductByIdThrowsErrorWhenNotFound() {
    when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
    assertThrows(ResourceNotFound.class, () -> productServiceImpl.getProductById(123L));
  }

  @Test
  public void getProductByIdThrowsDataAccessException() {
    doThrow(EmptyResultDataAccessException.class).when(productRepository).findById(anyLong());
    assertThrows(ServerError.class, () -> productServiceImpl.getProductById(123L));
  }
}
