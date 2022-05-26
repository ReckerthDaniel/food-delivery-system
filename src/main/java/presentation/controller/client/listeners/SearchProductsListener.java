package presentation.controller.client.listeners;

import business.IDeliveryServiceProcessing;
import business.composite.MenuItem;
import presentation.view.client.ClientView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static business.validator.FieldValidator.isFieldNumber;
import static business.validator.FieldValidator.isFieldRealNumber;

public class SearchProductsListener implements ActionListener {
  private final ClientView view;
  private final IDeliveryServiceProcessing deliveryServiceProcessing;

  public SearchProductsListener(ClientView view, IDeliveryServiceProcessing deliveryServiceProcessing) {
    this.view = view;
    this.deliveryServiceProcessing = deliveryServiceProcessing;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (view.areAllFieldsEmpty()) {
      view.showMessage("Provide at least one search criteria", JOptionPane.WARNING_MESSAGE);
    } else {
      String title = view.getTitle();
      double rating = 0;
      int calories = 0;
      int protein = 0;
      int fat = 0;
      int sodium = 0;
      int price = 0;
      if (isFieldRealNumber(view.getRating())) {
        rating = Double.parseDouble(view.getRating());
      }
      if (isFieldNumber(view.getCalories())) {
        calories = Integer.parseInt(view.getCalories());
      }
      if (isFieldNumber(view.getProtein())) {
        protein = Integer.parseInt(view.getProtein());
      }
      if (isFieldNumber(view.getFat())) {
        fat = Integer.parseInt(view.getFat());
      }
      if (isFieldNumber(view.getSodium())) {
        sodium = Integer.parseInt(view.getSodium());
      }
      if (isFieldNumber(view.getPrice())) {
        price = Integer.parseInt(view.getPrice());
      }
      List<MenuItem> menuItems = deliveryServiceProcessing.searchProductsByCriteria(title, rating, calories, protein, fat, sodium, price);
      view.fillMenuTable(menuItems);
    }
  }
}
