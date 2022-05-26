package presentation.controller.admin.listeners;

import business.IDeliveryServiceProcessing;
import data.Serializer;
import presentation.view.admin.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddCompositeProductListener implements ActionListener {
  private final AdminView view;
  private final IDeliveryServiceProcessing deliveryServiceProcessing;

  public AddCompositeProductListener(AdminView view, IDeliveryServiceProcessing deliveryServiceProcessing) {
    this.view = view;
    this.deliveryServiceProcessing = deliveryServiceProcessing;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String title = view.getCompositeTitle();
    List<Integer> productIds = view.getCheckedRowsOfJTable();
    if(productIds == null || title.isBlank() ) {
      view.showMessage("Select at least one base product and check title is not empty!", JOptionPane.WARNING_MESSAGE);
    } else {
      deliveryServiceProcessing.createComposedProduct(title, productIds);
      view.showMessage("Added composed product " + title, JOptionPane.INFORMATION_MESSAGE);
      Serializer.serialize(deliveryServiceProcessing);
    }


  }
}
