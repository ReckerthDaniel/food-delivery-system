package business.composite;

import data.Utils;

import java.io.Serializable;
import java.util.Objects;

public abstract class MenuItem implements Serializable {
  private int id = Utils.menuItemId++;

  private String title;

  public MenuItem(String title) {
    this.title = title;
  }

  public abstract int computePrice();

  public abstract double computeRating();

  public abstract int computeCalories();

  public abstract int computeProtein();

  public abstract int computeFat();

  public abstract int computeSodium();

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MenuItem menuItem = (MenuItem) o;
    return title.equals(menuItem.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title);
  }
}
