package business;

import business.enums.Role;
import data.Utils;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
  private final int id = Utils.userId++;
  private String username;
  private String password;
  private Role role;
  private int ordersClientsFrequency = 0;

  public User(String username, String password, Role role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public int getOrdersClientsFrequency() {
    return ordersClientsFrequency;
  }

  public void incrementOrdersFrequency() {
    this.ordersClientsFrequency++;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id && username.equals(user.username) && password.equals(user.password) && role == user.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password, role);
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", role=" + role +
            '}';
  }
}
