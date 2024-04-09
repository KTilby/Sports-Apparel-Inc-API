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
class UserControllerTest {

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
  void createUser() throws Exception {
    String json = "{\"email\":\"titania@fairytail.org\",\"password\":\"P@$$w0rd\",\"role\":\"Admin\",\"firstName\":\"Erza\",\"lastName\":\"Scarlet\"}";
    this.mockMvc
        .perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(createdStatus)
        .andExpect(expectedType)
        .andExpect(jsonPath("$.firstName").value(equalTo("Erza")));
  }

  @Test
  void updateUser() throws Exception {
    String json = "{\"id\":\"1\",\"email\":\"titania@fairytail.org\",\"password\":\"P@$$w0rd\",\"role\":\"Admin\",\"firstName\":\"Erza\",\"lastName\":\"Scarlet\"}";
    this.mockMvc
        .perform(put("/users/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(okStatus)
        .andExpect(expectedType)
        .andExpect(jsonPath("$.firstName").value(equalTo("Erza")));
  }

  @Test
  void getUserByEmail() throws Exception {
    this.mockMvc
        .perform(get("/users/user@sportsapi.com"))
        .andExpect(okStatus)
        .andExpect(expectedType)
        .andExpect(jsonPath("$.firstName").value(equalTo("John")));
  }
}