package presentation.controller.admin.listeners;

import business.IDeliveryServiceProcessing;
import business.composite.BaseProduct;
import data.Serializer;
import presentation.view.admin.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyProductListener implements ActionListener {
  private final AdminView view;
  private final IDeliveryServiceProcessing deliveryServiceProcessing;

  public ModifyProductListener(AdminView view, IDeliveryServiceProcessing deliveryServiceProcessing) {
    this.view = view;
    this.deliveryServiceProcessing = deliveryServiceProcessing;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      int productId = Integer.parseInt(view.getProductId());
      BaseProduct product = view.getProductFromInfo();
      deliveryServiceProcessing.modifyProduct(productId, product);
      view.clearReport();
      view.showMessage("Product modified successfully", JOptionPane.INFORMATION_MESSAGE);
      Serializer.serialize(deliveryServiceProcessing);
    } catch (IllegalArgumentException ex) {
      view.showMessage(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
    }
  }

}
