package io.catalyte.training.sportsproducts.domains.purchase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
class PurchaseControllerTest {

  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  void savePurchase() throws Exception {
    String json = "{"
        + "\"billingStreet\":\"1251 W 1374 S\","
        + "\"billingCity\":\"Magnolia\","
        + "\"billingState\":\"GA\","
        + "\"billingZip\":28692,"
        + "\"email\":\"makarov@fairytail.org\","
        + "\"phone\":\"952-968-7532\","
        + "\"deliveryStreet\":\"1251 W 1374 S\","
        + "\"deliveryCity\":\"Magnolia\","
        + "\"deliveryState\":\"GA\","
        + "\"deliveryZip\":28692, "
        + "\"cardNumber\":\"1234567890123456\","
        + "\"cvv\":\"123\","
        + "\"expiration\":\"12/99\","
        + "\"cardHolder\":\"Makarov Dreyar\"}";
    this.mockMvc
        .perform(MockMvcRequestBuilders.post("/purchases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  void findAllPurchases() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/purchases"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}