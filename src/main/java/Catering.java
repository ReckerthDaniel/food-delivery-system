import business.*;
import business.enums.Role;
import data.Serializer;
import presentation.controller.login.LoginController;
import presentation.view.admin.AdminView;
import presentation.view.client.ClientView;
import presentation.view.employee.EmployeeView;
import presentation.view.login.LoginView;

import java.io.IOException;

public class Catering {

  public static void main(String[] args) throws IOException {

    IDeliveryServiceProcessing deliveryServiceProcessing = Serializer.deserialize();
    AdminView adminView = new AdminView();
    LoginView loginView = new LoginView();
    ClientView clientView = new ClientView();
    EmployeeView employeeView = new EmployeeView();
    LoginController loginController = new LoginController(loginView, adminView, clientView, employeeView, deliveryServiceProcessing);
  }
}
