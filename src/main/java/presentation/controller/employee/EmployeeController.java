package presentation.controller.employee;

import business.IDeliveryServiceProcessing;
import business.Order;
import business.composite.MenuItem;
import presentation.view.employee.EmployeeView;
import presentation.view.login.LoginView;

import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

public class EmployeeController implements PropertyChangeListener {
  private final EmployeeView view;
  private final LoginView loginView;
  private final IDeliveryServiceProcessing deliveryServiceProcessing;

  public EmployeeController(EmployeeView view, LoginView loginView, IDeliveryServiceProcessing deliveryServiceProcessing) {
    this.view = view;
    this.loginView = loginView;
    this.deliveryServiceProcessing = deliveryServiceProcessing;
    this.deliveryServiceProcessing.addPropertyChangeListener(this);
    view.addLogoutButtonListener(new LogoutButtonActionListener());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    view.appendTextToNotificationTxtArea(evt.getNewValue().toString() + "\n");
  }

  private class LogoutButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      view.dispose();
      loginView.setVisible(true);
    }
  }
}
