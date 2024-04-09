package io.catalyte.training.sportsproducts.domains.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UserServiceImpl.class)
class UserServiceImplTest {

  @Mock
  UserRepository mockUserRepo;

  @InjectMocks
  UserServiceImpl userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void updateUserUpdatesUser() {
    User titania = new User(1L,"titania@fairytail.org","P@$$w0rd","Admin","Erza","Scarlet");
    when(mockUserRepo.findById(titania.id))
        .thenReturn(Optional.of(titania));
    when(mockUserRepo.save(titania))
        .thenReturn(titania);

    Assertions.assertNotNull(userService.updateUser(1L,titania));
  }

  @Test
  void createUserCreatesUser() {
    User titania = new User("titania@fairytail.org","P@$$w0rd","Admin","Erza","Scarlet");
    when(userService.createUser(titania))
        .thenReturn(titania);
    Assertions.assertNotNull(userService.createUser(titania));
  }

  @Test
  void getUserByEmailGetsUser() {
    User titania = new User("titania@fairytail.org","P@$$w0rd","Admin","Erza","Scarlet");
    when(mockUserRepo.findByEmail(any(String.class)))
        .thenReturn(titania);
    Assertions.assertNotNull(userService.getUserByEmail(titania.email));
  }
}