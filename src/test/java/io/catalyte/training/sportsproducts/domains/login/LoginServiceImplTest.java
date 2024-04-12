package io.catalyte.training.sportsproducts.domains.login;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import io.catalyte.training.sportsproducts.domains.user.User;
import io.catalyte.training.sportsproducts.domains.user.UserService;
import io.catalyte.training.sportsproducts.exceptions.BadRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LoginServiceImplTest {

  @Mock
  UserService mockUserService;

  @InjectMocks
  private LoginServiceImpl loginService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  User zaref = new User("spriggan@alvarez.gov", "N@t$u400", "Admin", "Zaref", "Dragneel");
  Credentials zarefSignin = new Credentials("spriggan@alvarez.gov","N@t$u400");
  Credentials zarefBadSignin = new Credentials("spriggan@alvarez.gov","Acn010g!@");

  @Test
  void loginLogsIn() {
    when(mockUserService.getUserByEmail("spriggan@alvarez.gov")).thenReturn(zaref);
    Assertions.assertEquals(loginService.login(zarefSignin), zaref);
  }
  @Test
  void loginBadEmail() {
    when(mockUserService.getUserByEmail("spriggan@alvarez.gov")).thenReturn(null);
    Assertions.assertThrows(BadRequest.class, () -> loginService.login(zarefSignin));
  }
  @Test
  void loginBadPassword() {
    when(mockUserService.getUserByEmail("spriggan@alvarez.gov")).thenReturn(zaref);
    Assertions.assertThrows(BadRequest.class, () -> loginService.login(zarefBadSignin));
  }
}