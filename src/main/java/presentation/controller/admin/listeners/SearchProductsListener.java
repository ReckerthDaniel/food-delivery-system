package presentation.controller.admin.listeners;

import business.IDeliveryServiceProcessing;
import business.composite.MenuItem;
import presentation.view.admin.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static business.validator.FieldValidator.*;

public class SearchProductsListener implements ActionListener {
  private final AdminView view;
  private final IDeliveryServiceProcessing deliveryServiceProcessing;

  public SearchProductsListener(AdminView view, IDeliveryServiceProcessing deliveryServiceProcessing) {
    this.view = view;
    this.deliveryServiceProcessing = deliveryServiceProcessing;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (view.areAllFieldsEmpty()) {
      view.showMessage("Provide at least one search criteria", JOptionPane.WARNING_MESSAGE);
    } else {
      String title = view.getProductTitle();
      double rating = 0;
      int calories = 0;
      int protein = 0;
      int fat = 0;
      int sodium = 0;
      int price = 0;
      if (isFieldRealNumber(view.getProductRating())) {
        rating = Double.parseDouble(view.getProductRating());
      }
      if (isFieldNumber(view.getProductCalories())) {
        calories = Integer.parseInt(view.getProductCalories());
      }
      if (isFieldNumber(view.getProductProtein())) {
        protein = Integer.parseInt(view.getProductProtein());
      }
      if (isFieldNumber(view.getProductFat())) {
        fat = Integer.parseInt(view.getProductFat());
      }
      if (isFieldNumber(view.getProductSodium())) {
        sodium = Integer.parseInt(view.getProductSodium());
      }
      if (isFieldNumber(view.getProductPrice())) {
        price = Integer.parseInt(view.getProductPrice());
      }
      List<MenuItem> menuItems = deliveryServiceProcessing.searchProductsByCriteria(title, rating, calories, protein, fat, sodium, price);
      view.fillMenuTable(menuItems);
    }
  }

}
