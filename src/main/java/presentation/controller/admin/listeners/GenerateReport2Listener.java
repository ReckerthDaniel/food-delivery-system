package presentation.controller.admin.listeners;

import business.IDeliveryServiceProcessing;
import business.User;
import presentation.view.admin.AdminView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

public class GenerateReport2Listener implements ActionListener {
  private final AdminView view;
  private final IDeliveryServiceProcessing deliveryServiceProcessing;

  public GenerateReport2Listener(AdminView view, IDeliveryServiceProcessing deliveryServiceProcessing) {
    this.view = view;
    this.deliveryServiceProcessing = deliveryServiceProcessing;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int ordersClientsFrequency = view.getClientOrderFreq();
    int priceComparisonValue = view.getPriceComparator();
    final Set<User> users = deliveryServiceProcessing.reportClientsWithOrdersMoreThanAndTotalPriceGreaterThan(ordersClientsFrequency, priceComparisonValue);
    view.clearReport();
    users.forEach(user -> view.setReportTxt(user.toString()));
  }
}
