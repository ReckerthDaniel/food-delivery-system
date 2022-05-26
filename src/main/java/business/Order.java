package business;

import data.Utils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Order implements Serializable {
  private final int orderId = Utils.orderId++;
  private final int clientId;
  private final LocalDateTime orderDate;

  public Order(int clientId, LocalDateTime orderDate) {
    this.clientId = clientId;
    this.orderDate = orderDate;
  }

  public int getOrderId() {
    return orderId;
  }

  public int getClientId() {
    return clientId;
  }

  public LocalDateTime getOrderDate() {
    return orderDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return orderId == order.orderId && clientId == order.clientId && orderDate.equals(order.orderDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, clientId, orderDate);
  }

  @Override
  public String toString() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    return "Order " + orderId +
            ", clientId=" + clientId +
            ", orderDate=" + dateTimeFormatter.format(orderDate) + "\n";
  }
}
