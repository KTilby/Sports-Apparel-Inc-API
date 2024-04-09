package io.catalyte.training.sportsproducts.domains.login;

import io.catalyte.training.sportsproducts.domains.user.User;

public interface LoginService {

  User login(Credentials credentials);
}
