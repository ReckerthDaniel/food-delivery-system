package business.composite;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public class CompositeProduct extends MenuItem implements Serializable {

  private Set<MenuItem> menuItems;

  public CompositeProduct(String title) {
    super(title);
    this.menuItems = new HashSet<>();
  }

  @Override
  public int computePrice() {
    return menuItems.stream()
            .map(MenuItem::computePrice)
            .mapToInt(Integer::intValue)
            .sum();
  }

  @Override
  public double computeRating() {
    final double rating = menuItems.stream()
            .map(MenuItem::computeRating)
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0.0);
    final DecimalFormat df = new DecimalFormat("#.##");
    return Double.parseDouble(df.format(rating));
  }

  @Override
  public int computeCalories() {
    return menuItems.stream()
            .map(MenuItem::computeCalories)
            .mapToInt(Integer::intValue)
            .sum();
  }

  @Override
  public int computeProtein() {
    return menuItems.stream()
            .map(MenuItem::computeProtein)
            .mapToInt(Integer::intValue)
            .sum();
  }

  @Override
  public int computeFat() {
    return menuItems.stream()
            .map(MenuItem::computeFat)
            .mapToInt(Integer::intValue)
            .sum();
  }

  @Override
  public int computeSodium() {
    return menuItems.stream()
            .map(MenuItem::computeSodium)
            .mapToInt(Integer::intValue)
            .sum();
  }

  public Set<MenuItem> getMenuItems() {
    return menuItems;
  }

  public void setMenuItems(Set<MenuItem> menuItems) {
    this.menuItems = menuItems;
  }

  public void addMenuItem(MenuItem menuItem) {
    this.menuItems.add(menuItem);
  }

  public void removeMenuItem(MenuItem menuItem) {
    this.menuItems.remove(menuItem);
  }

  @Override
  public String toString() {
    return getTitle() + "\n" +
            "   id=" + getId() + "\n" +
            "   contains=\n" + menuItems + "\n";
  }
}
