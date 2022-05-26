package presentation.controller.client;

import business.IDeliveryServiceProcessing;
import business.Order;
import business.User;
import business.composite.MenuItem;
import data.FileWriter;
import data.Serializer;
import presentation.controller.client.listeners.SearchProductsListener;
import presentation.view.client.ClientView;
import presentation.view.login.LoginView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ClientController {
  private final ClientView clientView;
  private final LoginView loginView;
  private User loggedUser;
  private final IDeliveryServiceProcessing deliveryServiceProcessing;

  public ClientController(ClientView clientView, LoginView loginView, IDeliveryServiceProcessing deliveryServiceProcessing) {
    this.clientView = clientView;
    this.loginView = loginView;
    this.deliveryServiceProcessing = deliveryServiceProcessing;
    clientView.addRefreshButtonActionListener(new RefreshButtonListener());
    clientView.addSearchProductsButtonActionListener(new SearchProductsListener(clientView, deliveryServiceProcessing));
    clientView.addLOGOUTButtonActionListener(new LogoutButtonActionListener());
    clientView.addClearButtonActionListener(new ClearButtonActionListener());
    clientView.addCreateOrderButtonActionListener(new CreateOrderButtonActionListener());
  }

  public void setLoggedUser(User loggedUser) {
    this.loggedUser = loggedUser;
  }

  private class RefreshButtonListener implements java.awt.event.ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      clientView.fillMenuTable(deliveryServiceProcessing.getMenu());
    }
  }

  private class LogoutButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      clientView.clear();
      clientView.dispose();
      loginView.setVisible(true);
    }
  }

  private class ClearButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      clientView.clear();
      clientView.clear();
      clientView.fillMenuTable(deliveryServiceProcessing.getMenu());
    }
  }

  private class CreateOrderButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      List<Integer> products = clientView.getCheckedRowsOfJTable();
      if (products.isEmpty()) {
        clientView.showMessage("You must select at least one product", JOptionPane.WARNING_MESSAGE);
      } else {
        final List<MenuItem> collect = products.stream()
                .map(deliveryServiceProcessing::findProductById)
                .collect(Collectors.toList());
        final Order order = new Order(loggedUser.getId(), LocalDateTime.now());
        if(deliveryServiceProcessing.createOrder(order, collect)) {
          loggedUser.incrementOrdersFrequency();
          clientView.showMessage("Ordered added!", JOptionPane.INFORMATION_MESSAGE);
          String text = order.toString() + collect + "\nTotal price: " + deliveryServiceProcessing.getOrderTotalPrice(order) + "\n";
          clientView.setBillTxtArea(text);
          FileWriter.writeToFile("BILL-" + loggedUser.getId() + ".txt", text);
          Serializer.serialize(deliveryServiceProcessing);
        }

      }
    }
  }
}
