package business;

import business.composite.BaseProduct;
import business.composite.CompositeProduct;
import business.composite.MenuItem;
import data.FileReader;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class is responsible for implementing the delivery interface.
 *
 * @author Daniel Reckerth
 * @invariant wellFormed()
 */
public class DeliveryService implements IDeliveryServiceProcessing {
  private final Map<Order, List<? extends MenuItem>> orders;
  private Set<MenuItem> menu;
  private final UserService userService;
  private final PropertyChangeSupport support = new PropertyChangeSupport(this);

  public DeliveryService(UserService userService) {
    this.orders = new HashMap<>();
    this.userService = userService;
  }


  @Override
  public void importInitialSetOfProducts(String filePath) throws IOException {
    assert filePath != null;
    menu = FileReader.importProductsFromCsv(filePath);
  }

  @Override
  public void addProduct(BaseProduct product) {
    assert product != null;
    assert wellFormed();
    menu.add(product);
  }

  @Override
  public void deleteProduct(int id) {
    assert id >= 0;
    assert wellFormed();
    menu.removeIf(menuItem -> menuItem.getId() == id);

  }

  @Override
  public void modifyProduct(int productId, BaseProduct updatingProduct) {
    assert productId < 0;
    assert updatingProduct != null;
    assert wellFormed();
    final Optional<MenuItem> byTitle = menu.stream()
            .filter(menuItem -> menuItem.getId() == productId)
            .findFirst();
    updatingProduct.setId(productId);

    byTitle.ifPresent(menuItem -> {
      menu.remove(menuItem);
      menu.add(updatingProduct);
    });
  }

  @Override
  public MenuItem findProductById(int id) {
    assert wellFormed();
    assert id >= 0;
    return menu.stream()
            .filter(menuItem -> menuItem.getId() == id)
            .findFirst()
            .orElse(null);
  }

  @Override
  public void createComposedProduct(String title, List<Integer> productsIds) {
    assert title != null;
    assert productsIds != null;
    assert !productsIds.isEmpty();
    assert wellFormed();
    List<MenuItem> menuItems = getMenu();

    CompositeProduct compositeProduct = new CompositeProduct(title);
    productsIds.forEach(id -> compositeProduct.addMenuItem(menuItems.get(id)));
    menu.add(compositeProduct);
  }

  @Override
  public List<Order> reportOrdersBetween(LocalDateTime startDate, LocalDateTime endDate) {
    assert startDate != null;
    assert endDate != null;
    assert startDate.isBefore(endDate);
    assert wellFormed();
    return orders.keySet().stream()
            .filter(menuItems -> menuItems.getOrderDate().isAfter(startDate) && menuItems.getOrderDate().isBefore(endDate))
            .collect(Collectors.toList());
  }

  @Override
  public List<MenuItem> reportProductsOrderedMoreThan(int numberOfTimes) {
    assert numberOfTimes > 0;
    assert wellFormed();
    List<MenuItem> popularProducts = new ArrayList<>();
    menu.stream()
            .filter(menuItem -> orders.entrySet().stream()
                    .filter(entry -> entry.getValue().contains(menuItem)).count() > numberOfTimes)
            .forEach(popularProducts::add);
    return popularProducts;
  }

  @Override
  public boolean createOrder(Order order, List<MenuItem> orderProducts) {
    assert wellFormed();
    Map.Entry<Order, List<? extends MenuItem>> entry = Map.entry(order, orderProducts);
    try {
      orders.put(order, orderProducts);
      String text = order.toString() + orderProducts + "\nTotal price: " + getOrderTotalPrice(order) + "\n";
      support.firePropertyChange("orders", this.orders, text);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener pcl) {
    support.addPropertyChangeListener(pcl);
  }

  @Override
  public Set<User> reportClientsWithOrdersMoreThanAndTotalPriceGreaterThan(int numberOfTimes, int priceComparator) {
    assert numberOfTimes > 0;
    assert priceComparator > 0;
    assert wellFormed();
    Set<User> clients = new LinkedHashSet<>();

    orders.entrySet().stream()
            .filter(entry -> userService.findClientById(entry.getKey().getClientId()).getOrdersClientsFrequency() > numberOfTimes && entry.getValue().stream()
                    .mapToInt(MenuItem::computePrice)
                    .sum() > priceComparator)
            .forEach(entry -> clients.add(userService.findClientById(entry.getKey().getClientId())));

    return clients;
  }

  @Override
  public List<MenuItem> reportProductsOrderedIn(LocalDate date) {
    assert date != null;
    assert wellFormed();
    return orders.entrySet().stream()
            .filter(entry -> entry.getKey().getOrderDate().toLocalDate().equals(date))
            .flatMap(entry -> entry.getValue().stream())
            .collect(Collectors.toList());
  }

  @Override
  public Map<Order, List<? extends MenuItem>> getOrders() {
    assert wellFormed();
    return orders;
  }

  @Override
  public List<MenuItem> searchProductsByCriteria(String title, double rating, int calories, int proteins, int fats, int sodium, int price) {
    assert wellFormed();
    Predicate<MenuItem> predicate = s -> true;
    if (!title.isBlank()) {
      predicate = s -> s.getTitle().toLowerCase().contains(title.toLowerCase());
    }
    if (rating != 0.0) {
      predicate = predicate.and(s -> s.computeRating() >= rating);
    }
    if (calories != 0) {
      predicate = predicate.and(s -> s.computeCalories() >= calories);
    }
    if (proteins != 0) {
      predicate = predicate.and(s -> s.computeProtein() >= proteins);
    }
    if (fats != 0) {
      predicate = predicate.and(s -> s.computeFat() >= fats);
    }
    if (sodium != 0) {
      predicate = predicate.and(s -> s.computeSodium() >= sodium);
    }
    if (price != 0) {
      predicate = predicate.and(s -> s.computePrice() >= price);
    }

    return menu.stream()
            .filter(predicate)
            .collect(Collectors.toList());
  }

  @Override
  public List<MenuItem> getMenu() {
    assert wellFormed();
    List<MenuItem> menuItems = new ArrayList<>(menu);
    menuItems.sort(Comparator.comparing(MenuItem::getId));
    return menuItems;
  }

  public boolean wellFormed() {
    return menu != null;
  }

  public int getOrderTotalPrice(Order order) {
    final List<? extends MenuItem> menuItems = orders.get(order);
    return menuItems.stream().mapToInt(MenuItem::computePrice).sum();
  }

  @Override
  public UserService getUserService() {
    return userService;
  }
}
