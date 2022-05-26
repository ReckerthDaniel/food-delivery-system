package business;

import business.composite.BaseProduct;
import business.composite.MenuItem;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface for the delivery service processing.
 *
 * @author Daniel Reckerth
 */
public interface IDeliveryServiceProcessing extends Serializable {

  void importInitialSetOfProducts(String filePath) throws IOException;

  /**
   * Add a new base product to the menu.
   *
   * @param product the product to add
   * @pre product != null
   */
  void addProduct(BaseProduct product);

  /**
   * Delete a product from the menu by id;
   *
   * @param id the id of the product to delete
   * @pre productId >= 0
   * @post menu.size() = menu.size@pre - 1
   */
  void deleteProduct(int id);

  /**
   * Modify the product, i.e. updating it.
   *
   * @param productId       the id of the product to update
   * @param updatingProduct the product to update
   * @pre orderId >= 0
   * @pre updatingProduct != null
   */
  void modifyProduct(int productId, BaseProduct updatingProduct);

  /**
   * Get the product with the given id.
   *
   * @param id the id of the product to find
   * @return the product with the given id
   * @pre orderId >= 0
   */
  MenuItem findProductById(int id);

  /**
   * Create a composed product based on the given base products.
   *
   * @param title      the title of the new product
   * @param productIds the ids of the products to add to the new product
   * @pre title != null
   * @pre productIds != null
   * @post menu.size() = menu.size@pre + 1
   */
  void createComposedProduct(String title, List<Integer> productIds);

  /**
   * Get orders between two dates.
   *
   * @param startDate the start date
   * @param endDate   the end date
   * @return the orders between the given dates
   * @pre startDate != null
   * @pre endDate != null
   */
  List<Order> reportOrdersBetween(LocalDateTime startDate, LocalDateTime endDate);

  /**
   * Get the menu items order more than specified number of times.
   *
   * @param numberOfTimes the number of times
   * @return the menu items ordered more than specified number of times
   * @pre numberOfTimes >= 0
   */
  List<MenuItem> reportProductsOrderedMoreThan(int numberOfTimes);

  /**
   * Get the clients with orders more than a number of times and price greater than specified.
   *
   * @param numberOfTimes   the number of times
   * @param priceComparator the price comparator
   * @return the clients with orders more than a number of times and price greater than specified
   * @pre numberOfTimes >= 0
   * @pre price >= 0
   */
  Set<User> reportClientsWithOrdersMoreThanAndTotalPriceGreaterThan(int numberOfTimes, int priceComparator);

  /**
   * Get the products ordered in a given date.
   *
   * @param date the date
   * @return the products ordered in a given date
   * @pre date != null
   */
  List<MenuItem> reportProductsOrderedIn(LocalDate date);

  /**
   * Get the menu
   *
   * @return the menu
   * @pre menu != null
   */
  List<MenuItem> getMenu();

  /**
   * Get the orders
   *
   * @return the orders
   * @pre orders != null
   */
  Map<Order, List<? extends MenuItem>> getOrders();

  /**
   * Get products based on criteria.
   *
   * @param title    the title
   * @param rating   the rating
   * @param calories the calories
   * @param proteins the proteins
   * @param fats     the fats
   * @param sodium   the sodium
   * @param price    the price
   * @return the products based on criteria
   */
  List<MenuItem> searchProductsByCriteria(String title, double rating, int calories, int proteins, int fats, int sodium, int price);

  /**
   * Create an order
   *
   * @param order         orders
   * @param orderProducts the order products
   * @pre clientId >= 0
   * @pre orderProducts != null
   * @pre date != null
   * @post orders.size() = orders.size@pre + 1
   * @return true if the order is placed
   */
  boolean createOrder(Order order, List<MenuItem> orderProducts);

  /**
   * Adds a property change listener.
   *
   * @param pcl the property change listener
   */
  void addPropertyChangeListener(PropertyChangeListener pcl);

  /**
   * Compute total price of an order
   *
   * @param order the order
   * @return the total price of an order
   */
  int getOrderTotalPrice(Order order);

  /**
   * Get the user service
   *
   * @return the user service
   */
  UserService getUserService();
}
