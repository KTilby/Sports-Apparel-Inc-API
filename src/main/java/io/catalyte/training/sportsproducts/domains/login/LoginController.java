package io.catalyte.training.sportsproducts.domains.login;

import static io.catalyte.training.sportsproducts.constants.Paths.LOGIN_PATH;

import io.catalyte.training.sportsproducts.domains.user.User;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = LOGIN_PATH)
public class LoginController {

  @Autowired
  LoginService loginService;

  @PostMapping
  User login(@RequestBody Credentials credentials) {
    return loginService.login(credentials);
  }
}
