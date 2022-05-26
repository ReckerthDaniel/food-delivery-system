package business;

import java.io.Serializable;

public interface UserService extends Serializable {

  User login(String username, String password) throws IllegalArgumentException;

  boolean register(User user);

  User findClientById(int id) throws IllegalArgumentException;
}
