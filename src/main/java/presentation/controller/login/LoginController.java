package presentation.controller.login;

import business.IDeliveryServiceProcessing;
import business.User;
import business.UserService;
import business.enums.Role;
import data.Serializer;
import presentation.controller.admin.AdminController;
import presentation.controller.client.ClientController;
import presentation.controller.employee.EmployeeController;
import presentation.view.admin.AdminView;
import presentation.view.client.ClientView;
import presentation.view.employee.EmployeeView;
import presentation.view.login.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

  private final IDeliveryServiceProcessing iDeliveryServiceProcessing;

  private final LoginView loginView;
  private final AdminView adminView;
  private final ClientView clientView;
  private final EmployeeView employeeView;

  private final AdminController adminController;
  private final ClientController clientController;
  private final EmployeeController employeeController;

  public LoginController(LoginView loginView, AdminView adminView, ClientView clientView, EmployeeView employeeView, IDeliveryServiceProcessing iDeliveryServiceProcessing) {
    this.iDeliveryServiceProcessing = iDeliveryServiceProcessing;

    this.loginView = loginView;
    this.adminView = adminView;
    this.clientView = clientView;
    this.employeeView = employeeView;

    this.adminController = new AdminController(adminView, loginView, iDeliveryServiceProcessing);
    this.clientController = new ClientController(clientView, loginView, iDeliveryServiceProcessing);
    this.employeeController = new EmployeeController(employeeView, loginView, iDeliveryServiceProcessing);

    loginView.setLoginButtonListener(new LoginButtonListener());
    loginView.setRegisterButtonListener(new RegisterButtonListener());
  }

  private class LoginButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      String username = loginView.getUsername();
      String password = loginView.getPassword();
      try {
        User loggedUser = iDeliveryServiceProcessing.getUserService().login(username, password);
        switch (loggedUser.getRole()) {
          case ADMIN -> {
            loginView.setVisible(false);
            adminView.setVisible(true);
          }
          case EMPLOYEE -> {
            loginView.setVisible(false);
            employeeView.setVisible(true);
          }
          case CLIENT -> {
            loginView.setVisible(false);
            clientView.setVisible(true);
            clientController.setLoggedUser(loggedUser);
          }

        }
      } catch (IllegalArgumentException ex) {
        loginView.showMessage(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private class RegisterButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      String username = loginView.getUsername();
      String password = loginView.getPassword();
      Role role = loginView.getRole();
      User user = new User(username, password, role);
      if (iDeliveryServiceProcessing.getUserService().register(user)) {
        loginView.showMessage("User created successfully", JOptionPane.INFORMATION_MESSAGE);
        Serializer.serialize(iDeliveryServiceProcessing);
      } else {
        loginView.showMessage("Username taken or empty fields!", JOptionPane.ERROR_MESSAGE);
      }
      loginView.clearFields();
    }
  }


}
