package presentation.controller.admin.listeners;

import business.IDeliveryServiceProcessing;
import business.composite.MenuItem;
import presentation.view.admin.AdminView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GenerateReport4Listener implements ActionListener {
  private final AdminView view;
  private final IDeliveryServiceProcessing deliveryServiceProcessing;

  public GenerateReport4Listener(AdminView view, IDeliveryServiceProcessing deliveryServiceProcessing) {
    this.view = view;
    this.deliveryServiceProcessing = deliveryServiceProcessing;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String productsOrderedDateString = view.getProductOrderDate();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate productsOrderedDate = LocalDate.parse(productsOrderedDateString, formatter);
    final Set<MenuItem> menuItems = new LinkedHashSet<>(deliveryServiceProcessing.reportProductsOrderedIn(productsOrderedDate));
    view.clearReport();
    menuItems.forEach(menuItem -> view.setReportTxt(menuItem.toString()));
  }
}
