package business;

import business.enums.Role;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class UserServiceImpl implements UserService, Serializable {
  Set<User> users;

  public UserServiceImpl() {
    this.users = new LinkedHashSet<>();
  }

  @Override
  public User login(String username, String password) throws IllegalArgumentException {
    final Optional<User> first = users.stream()
            .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(encodePassword(password)))
            .findFirst();

    return first.orElseThrow(() -> new IllegalArgumentException("User not found"));
  }

  @Override
  public boolean register(User user) {
    assert user != null;
    assert user.getUsername() != null;
    assert user.getPassword() != null;
    assert user.getRole() != null;
    if(user.getUsername().isBlank() || user.getPassword().isBlank()) {
      return false;
    }
    user.setPassword(encodePassword(user.getPassword()));
    return users.add(user);
  }

  @Override
  public User findClientById(int id) throws IllegalArgumentException {
    final Optional<User> byId = users.stream()
            .filter(user -> user.getId() == id && user.getRole() == Role.CLIENT)
            .findFirst();

    return byId.orElseThrow(() -> new IllegalArgumentException("Client not found"));
  }

  @Override
  public Set<User> getUsers() {
    return users;
  }

  private String encodePassword(String password) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
      StringBuilder hexString = new StringBuilder();

      for (byte b : hash) {
        String hex = Integer.toHexString(0xff & b);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }

      return hexString.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
