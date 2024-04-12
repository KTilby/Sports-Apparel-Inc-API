package io.catalyte.training.sportsproducts.domains.purchase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import io.catalyte.training.sportsproducts.data.ProductFactory;
import io.catalyte.training.sportsproducts.domains.product.Product;
import io.catalyte.training.sportsproducts.domains.product.ProductService;
import io.catalyte.training.sportsproducts.domains.user.User;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

class PurchaseServiceImplTest {

  @Mock
  PurchaseRepository purchaseRepository;

  @Mock
  LineItemRepository lineItemRepository;

  @Mock
  ProductService productService;

  @InjectMocks
  PurchaseServiceImpl purchaseService;

  Product testProduct, testProduct2;

  ProductFactory productFactory;

  Purchase testPurchase, testPurchase2;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    productFactory = new ProductFactory();
    testProduct = productFactory.createRandomProduct();
    testProduct2 = new Product("Goner", "Slick", "Very slick", "Men", "Hockey", "Sunglasses", "12-23-1978", "", "", "", "", true, false,14.99);
    testPurchase = new Purchase();
    testPurchase2 = new Purchase();

    LineItem testItem = new LineItem(1L, testPurchase, testProduct, 1);
    LineItem testItem2 = new LineItem(2L, testPurchase2, testProduct2, 1);
    testPurchase.setProducts(Set.of(testItem));

    BillingAddress testBillingAddress = new BillingAddress();
    testBillingAddress.setBillingStreet("1251 W 1374 S");
    testBillingAddress.setBillingCity("Magnolia");
    testBillingAddress.setBillingState("GA");
    testBillingAddress.setBillingZip(28692);
    testBillingAddress.setEmail("makarov@fairytail.org");
    testBillingAddress.setPhone("952-968-7532");
    testPurchase.setBillingAddress(testBillingAddress);

    DeliveryAddress testDeliveryAddress = new DeliveryAddress();
    testDeliveryAddress.setDeliveryStreet("1251 W 1374 S");
    testDeliveryAddress.setDeliveryCity("Magnolia");
    testDeliveryAddress.setDeliveryState("GA");
    testDeliveryAddress.setDeliveryZip(28692);
    testPurchase.setDeliveryAddress(testDeliveryAddress);

    CreditCard testCard = new CreditCard("1234567890123456","123","12/99","Makarov Dreyar");
    testPurchase.setCreditCard(testCard);

    testPurchase2.setProducts(Set.of(testItem, testItem2));
    testPurchase2.setBillingAddress(testBillingAddress);
    testPurchase2.setDeliveryAddress(testDeliveryAddress);
    testPurchase2.setCreditCard(testCard);
  }

  @Test
  void findAllPurchasesFindsAll() {
    when(purchaseRepository.findAll()).thenReturn(new ArrayList<>());
    Assertions.assertNotNull(purchaseService.findAllPurchases());
  }

  @Test
  void findAllPurchasesThrowsDataAccessException() {
    doThrow(EmptyResultDataAccessException.class).when(purchaseRepository).findAll();
    assertThrows(ServerError.class, () -> purchaseService.findAllPurchases());
  }

  @Test
  void savePurchaseSavesPurchase() {
    when(purchaseRepository.save(testPurchase)).thenReturn(testPurchase);
    Assertions.assertNotNull(purchaseService.savePurchase(testPurchase));
  }

  @Test
  void savePurchaseThrowsDataAccessException() {
    doThrow(EmptyResultDataAccessException.class).when(purchaseRepository).save(testPurchase);
    assertThrows(ServerError.class, () -> purchaseService.savePurchase(testPurchase));
  }

  @Test
  void savePurchaseThrowsDataAccessExceptionOnListItem() {
    doThrow(EmptyResultDataAccessException.class).when(lineItemRepository).save(any(LineItem.class));
    assertThrows(ServerError.class, () -> purchaseService.savePurchase(testPurchase));
  }

  @Test
  void savePurchaseSavesPurchaseNoLineItems() {
    when(productService.getProductById(any(Long.class))).thenReturn(testProduct2);
    Assertions.assertNotNull(purchaseService.savePurchase(testPurchase2));
  }
}