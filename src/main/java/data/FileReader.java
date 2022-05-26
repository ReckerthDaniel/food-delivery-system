package data;

import business.composite.BaseProduct;
import business.composite.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;


public class FileReader {

  public static Set<MenuItem> importProductsFromCsv(String filePath) throws IOException {
    Set<MenuItem> menuItems = new LinkedHashSet<>();
    try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
      String line = br.readLine(); // skip header
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        String title = values[0];
        double rating = Double.parseDouble(values[1]);
        int calories = Integer.parseInt(values[2]);
        int protein = Integer.parseInt(values[3]);
        int fat = Integer.parseInt(values[4]);
        int sodium = Integer.parseInt(values[5]);
        int price = Integer.parseInt(values[6]);
        MenuItem product = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
        if (!menuItems.add(product)) {
          Utils.menuItemId--;
        }
      }
      return menuItems;
    }
  }

}

