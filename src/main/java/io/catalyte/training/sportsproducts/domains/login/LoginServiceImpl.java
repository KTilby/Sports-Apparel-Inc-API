package io.catalyte.training.sportsproducts.domains.login;

import io.catalyte.training.sportsproducts.domains.user.User;
import io.catalyte.training.sportsproducts.domains.user.UserService;
import io.catalyte.training.sportsproducts.exceptions.BadRequest;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

  @Autowired
  UserService userService;
  @Override
  public User login(Credentials credentials) {
    String email = credentials.getEmail();
    String password = credentials.getPassword();

    User user = userService.getUserByEmail(email);

    if(user == null) {
      throw new BadRequest("Incorrect Email");
    }

    if(!Objects.equals(password, user.getPassword())) {
      throw new BadRequest("Incorrect Password");
    }

      return user;
  }
}
