package presentation.controller.admin.listeners;

import business.IDeliveryServiceProcessing;
import business.Order;
import presentation.view.admin.AdminView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GenerateReport1Listener implements ActionListener {
  private final AdminView view;
  private final IDeliveryServiceProcessing deliveryServiceProcessing;

  public GenerateReport1Listener(AdminView view, IDeliveryServiceProcessing deliveryServiceProcessing) {
    this.view = view;
    this.deliveryServiceProcessing = deliveryServiceProcessing;
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    String startDateString = view.getStartDate();
    String endDateString = view.getEndDate();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    LocalDateTime startDate = LocalDateTime.parse(startDateString, formatter);
    LocalDateTime endDate = LocalDateTime.parse(endDateString, formatter);
    final Set<Order> orders = new LinkedHashSet<>(deliveryServiceProcessing.reportOrdersBetween(startDate, endDate));
    view.clearReport();
    orders.forEach(order -> view.setReportTxt(order.toString()));
  }
}
