package business.composite;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable {

  private double rating;
  private int calories;
  private int protein;
  private int fat;
  private int sodium;
  private int price;

  public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
    super(title);
    this.rating = rating;
    this.calories = calories;
    this.protein = protein;
    this.fat = fat;
    this.sodium = sodium;
    this.price = price;
  }

  @Override
  public int computePrice() {
    return this.price;
  }

  @Override
  public double computeRating() {
    return this.rating;
  }

  @Override
  public int computeCalories() {
    return this.calories;
  }

  @Override
  public int computeProtein() {
    return this.protein;
  }

  @Override
  public int computeFat() {
    return this.fat;
  }

  @Override
  public int computeSodium() {
    return this.sodium;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public int getCalories() {
    return calories;
  }

  public void setCalories(int calories) {
    this.calories = calories;
  }

  public int getProtein() {
    return protein;
  }

  public void setProtein(int protein) {
    this.protein = protein;
  }

  public int getFat() {
    return fat;
  }

  public void setFat(int fat) {
    this.fat = fat;
  }

  public int getSodium() {
    return sodium;
  }

  public void setSodium(int sodium) {
    this.sodium = sodium;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return getTitle() + "\n" +
            "   id=" + getId() + "\n" +
            "   rating=" + rating + "\n" +
            "   calories=" + calories + "\n" +
            "   protein=" + protein + "\n" +
            "   fat=" + fat + "\n" +
            "   sodium=" + sodium + "\n" +
            "   price=" + price + "\n";
  }
}
