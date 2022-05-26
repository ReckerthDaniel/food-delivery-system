package presentation.controller.admin.listeners;

import business.IDeliveryServiceProcessing;
import data.Serializer;
import presentation.view.admin.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ImportButtonListener implements ActionListener {
  private final AdminView view;
  private final IDeliveryServiceProcessing deliveryServiceProcessing;

  public ImportButtonListener(AdminView view, IDeliveryServiceProcessing deliveryServiceProcessing) {
    this.view = view;
    this.deliveryServiceProcessing = deliveryServiceProcessing;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      deliveryServiceProcessing.importInitialSetOfProducts("products.csv");
      view.fillMenuTable(deliveryServiceProcessing.getMenu());
      view.disableImportMenuButton();
      view.showMessage("Products imported successfully", JOptionPane.INFORMATION_MESSAGE);
      Serializer.serialize(deliveryServiceProcessing);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
