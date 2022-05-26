package presentation.controller.admin.listeners;

import business.IDeliveryServiceProcessing;
import data.Serializer;
import presentation.view.admin.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProductListener implements ActionListener {
  private final AdminView view;
  private final IDeliveryServiceProcessing deliveryServiceProcessing;

  public DeleteProductListener(AdminView view, IDeliveryServiceProcessing deliveryServiceProcessing) {
    this.view = view;
    this.deliveryServiceProcessing = deliveryServiceProcessing;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(view.getProductId().isEmpty()) {
      view.showMessage("Choose item to delete from menu", JOptionPane.WARNING_MESSAGE);
    } else {
      deliveryServiceProcessing.deleteProduct(Integer.parseInt(view.getProductId()));
      view.clear();
      view.fillMenuTable(deliveryServiceProcessing.getMenu());
      view.showMessage("Product deleted", JOptionPane.INFORMATION_MESSAGE);
      Serializer.serialize(deliveryServiceProcessing);
    }
  }
}
