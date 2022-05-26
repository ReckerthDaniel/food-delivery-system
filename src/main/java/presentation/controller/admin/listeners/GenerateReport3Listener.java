package presentation.controller.admin.listeners;

import business.IDeliveryServiceProcessing;
import business.composite.MenuItem;
import presentation.view.admin.AdminView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GenerateReport3Listener implements ActionListener {
  private final AdminView view;
  private final IDeliveryServiceProcessing deliveryServiceProcessing;

  public GenerateReport3Listener(AdminView view, IDeliveryServiceProcessing deliveryServiceProcessing) {
    this.view = view;
    this.deliveryServiceProcessing = deliveryServiceProcessing;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int productOrderedFrequency = view.getProductOrderFreq();
    final Set<MenuItem> menuItems = new LinkedHashSet<>(deliveryServiceProcessing.reportProductsOrderedMoreThan(productOrderedFrequency));
    view.clearReport();
    menuItems.forEach(menuItem -> view.setReportTxt(menuItem.toString()));
  }
}
