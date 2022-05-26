package presentation.controller.admin.listeners;

import business.IDeliveryServiceProcessing;
import business.composite.BaseProduct;
import data.Serializer;
import presentation.view.admin.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductListener implements ActionListener {
  private final AdminView view;
  private final IDeliveryServiceProcessing deliveryServiceProcessing;

  public AddProductListener(AdminView view, IDeliveryServiceProcessing deliveryServiceProcessing) {
    this.view = view;
    this.deliveryServiceProcessing = deliveryServiceProcessing;
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      BaseProduct product = view.getProductFromInfo();
      deliveryServiceProcessing.addProduct(product);
      view.clear();
      view.fillMenuTable(deliveryServiceProcessing.getMenu());
      view.showMessage("Product Added", JOptionPane.INFORMATION_MESSAGE);
      Serializer.serialize(deliveryServiceProcessing);
    } catch (IllegalArgumentException ex) {
      ex.printStackTrace();
      view.showMessage(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
    }
  }
}
