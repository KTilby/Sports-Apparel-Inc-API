package io.catalyte.training.sportsproducts.domains.user;

import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerControllerTest {

  ResultMatcher okStatus = MockMvcResultMatchers.status().isOk();
  ResultMatcher createdStatus = MockMvcResultMatchers.status().isCreated();
  ResultMatcher expectedType = MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON);

  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  void createCustomer() throws Exception {
    String json = "{\"email\":\"salamander@fairytail.org\","
        + "\"password\":\"P@$$w0rd\","
        + "\"role\":\"Customer\","
        + "\"firstName\":\"Natsu\","
        + "\"lastName\":\"Dragneel\","
        + "\"shippingAddress1\":\"584 Lilly Dr\","
        + "\"shippingAddress2\":\"\","
        + "\"shippingCity\":\"Magnolia\","
        + "\"shippingState\":\"CV\","
        + "\"shippingZipCode\":\"38435\","
        + "\"billingAddress1\":\"36 Snapdragon Way\","
        + "\"billingAddress2\":\"\","
        + "\"billingCity\":\"Magnolia\","
        + "\"billingState\":\"CV\","
        + "\"billingZipCode\":\"38435\"}";
    this.mockMvc
        .perform(post("/users/customers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(createdStatus)
        .andExpect(expectedType)
        .andExpect(jsonPath("$.firstName").value(equalTo("Natsu")));
  }

  @Test
  void updateCustomer() throws Exception {
    String json = "{"
        + "\"id\":\"2\","
        + "\"email\":\"salamander@fairytail.org\","
        + "\"password\":\"P@$$w0rd\","
        + "\"role\":\"Customer\","
        + "\"firstName\":\"Natsu\","
        + "\"lastName\":\"Dragneel\","
        + "\"shippingAddress1\":\"584 Lilly Dr\","
        + "\"shippingAddress2\":\"\","
        + "\"shippingCity\":\"Magnolia\","
        + "\"shippingState\":\"CV\","
        + "\"shippingZipCode\":\"38435\","
        + "\"billingAddress1\":\"36 Snapdragon Way\","
        + "\"billingAddress2\":\"\","
        + "\"billingCity\":\"Magnolia\","
        + "\"billingState\":\"CV\","
        + "\"billingZipCode\":\"38435\"}";
    this.mockMvc
        .perform(put("/users/customers/2")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(okStatus)
        .andExpect(expectedType)
        .andExpect(jsonPath("$.firstName").value(equalTo("Natsu")));
  }

  @Test
  void getCustomerByEmail() throws Exception {
    this.mockMvc
        .perform(get("/users/customers/elementary@watson.uk"))
        .andExpect(okStatus)
        .andExpect(expectedType)
        .andExpect(jsonPath("$.firstName").value(equalTo("Sherlock")));
  }
}