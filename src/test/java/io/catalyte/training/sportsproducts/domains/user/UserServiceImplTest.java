package io.catalyte.training.sportsproducts.domains.user;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import io.catalyte.training.sportsproducts.exceptions.ResourceNotFound;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.server.ResponseStatusException;

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
    User titania = new User(null,"titania@fairytail.org","P@$$w0rd","Admin","Erza","Scarlet");
    when(mockUserRepo.findById(1L))
        .thenReturn(Optional.of(titania));
    when(mockUserRepo.save(titania))
        .thenReturn(titania);

    Assertions.assertNotNull(userService.updateUser(1L,titania));
  }

  @Test
  void updateUserThrowsResponseStatusException() {
    User titania = new User(2L,"titania@fairytail.org","P@$$w0rd","Admin","Erza","Scarlet");

    Assertions.assertThrows(ResponseStatusException.class, () -> userService.updateUser(1L,titania));
  }

  @Test
  void updateUserThrowsDataAccessException() {
    User titania = new User(2L,"titania@fairytail.org","P@$$w0rd","Admin","Erza","Scarlet");
    doThrow(EmptyResultDataAccessException.class).when(mockUserRepo).findById(2L);
    assertThrows(ServerError.class, () -> userService.updateUser(2L, titania));
  }

  @Test
  void updateUserThrowsResourceNotFound() {
    User titania = new User(2L,"titania@fairytail.org","P@$$w0rd","Admin","Erza","Scarlet");
    when(mockUserRepo.findById(2L)).thenReturn(Optional.empty());
    Assertions.assertThrows(
        ResourceNotFound.class, () -> userService.updateUser(2L, titania));
  }

  @Test
  void updateUserThrowsDataAccessExceptionOnSave() {
    User titania = new User(3L,"titania@fairytail.org","P@$$w0rd","Admin","Erza","Scarlet");
    when(mockUserRepo.findById(3L)).thenReturn(Optional.of(titania));
    doThrow(EmptyResultDataAccessException.class).when(mockUserRepo).save(titania);
    assertThrows(ServerError.class, () -> userService.updateUser(3L, titania));
  }

  @Test
  void createUserCreatesUser() {
    User titania = new User("titania@fairytail.org","P@$$w0rd","Admin","Erza","Scarlet");
    when(userService.createUser(titania))
        .thenReturn(titania);
    Assertions.assertNotNull(userService.createUser(titania));
  }

  @Test
  void createUserThrowsResponseStatusException() {
    User titania = new User(null,"P@$$w0rd","Admin","Erza","Scarlet");

    Assertions.assertThrows(ResponseStatusException.class, () -> userService.createUser(titania));
  }

  @Test
  void createUserThrowsDataAccessException() {
    User titania = new User(3L,"titania@fairytail.org","P@$$w0rd","Admin","Erza","Scarlet");
    doThrow(EmptyResultDataAccessException.class).when(mockUserRepo).findByEmail(titania.email);
    assertThrows(ServerError.class, () -> userService.createUser(titania));
  }

  @Test
  void createUserThrowsResponseStatusExceptionConflict() {
    User titania = new User(3L,"titania@fairytail.org","P@$$w0rd","Admin","Erza","Scarlet");
    when(mockUserRepo.findByEmail(titania.email)).thenReturn(titania);
    assertThrows(ResponseStatusException.class, () -> userService.createUser(titania));
  }

  @Test
  void createUserThrowsDataAccessExceptionOnSave() {
    User titania = new User(3L,"titania@fairytail.org","P@$$w0rd","Admin","Erza","Scarlet");
    when(mockUserRepo.findByEmail(titania.email)).thenReturn(null);
    doThrow(EmptyResultDataAccessException.class).when(mockUserRepo).save(titania);
    assertThrows(ServerError.class, () -> userService.createUser(titania));
  }

  @Test
  void getUserByEmailGetsUser() {
    User titania = new User("titania@fairytail.org","P@$$w0rd","Admin","Erza","Scarlet");
    when(mockUserRepo.findByEmail(any(String.class)))
        .thenReturn(titania);
    Assertions.assertNotNull(userService.getUserByEmail(titania.email));
  }

  @Test
  void getUserByEmailThrowsDataAccessException() {
    User titania = new User(3L,"titania@fairytail.org","P@$$w0rd","Admin","Erza","Scarlet");
    doThrow(EmptyResultDataAccessException.class).when(mockUserRepo).findByEmail(titania.email);
    assertThrows(ServerError.class, () -> userService.getUserByEmail(titania.email));
  }

  @Test
  void getUserByEmailThrowsResourceNotFound() {
    User titania = new User(2L,"titania@fairytail.org","P@$$w0rd","Admin","Erza","Scarlet");
    when(mockUserRepo.findByEmail(titania.email)).thenReturn(null);
    Assertions.assertThrows(
        ResourceNotFound.class, () -> userService.getUserByEmail(titania.email));
  }
}