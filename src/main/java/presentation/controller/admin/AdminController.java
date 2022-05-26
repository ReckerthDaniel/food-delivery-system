package presentation.controller.admin;


import business.IDeliveryServiceProcessing;
import presentation.controller.admin.listeners.*;
import presentation.view.admin.AdminView;
import presentation.view.login.LoginView;

import java.awt.event.ActionListener;

public class AdminController {

  private final AdminView view;
  private final LoginView loginView;
  private final IDeliveryServiceProcessing deliveryServiceProcessing;


  public AdminController(AdminView view, LoginView loginView, IDeliveryServiceProcessing deliveryServiceProcessing) {
    this.view = view;
    this.loginView = loginView;
    this.deliveryServiceProcessing = deliveryServiceProcessing;
    view.setAddButtonActionListener(new AddProductListener(view, deliveryServiceProcessing));
    view.setDeleteButtonActionListener(new DeleteProductListener(view, deliveryServiceProcessing));
    view.setModifyButtonActionListener(new ModifyProductListener(view, deliveryServiceProcessing));
    view.setGenerate1ButtonActionListener(new GenerateReport1Listener(view, deliveryServiceProcessing));
    view.setGenerate2ButtonActionListener(new GenerateReport2Listener(view, deliveryServiceProcessing));
    view.setGenerate3ButtonActionListener(new GenerateReport3Listener(view, deliveryServiceProcessing));
    view.setGenerate4ButtonActionListener(new GenerateReport4Listener(view, deliveryServiceProcessing));
    view.setSearchButtonActionListener(new SearchProductsListener(view, deliveryServiceProcessing));
    view.setImportMenuButtonActionListener(new ImportButtonListener(view, deliveryServiceProcessing));
    view.setMenuTableActionListener(new ProductsTableMouseListener(view, deliveryServiceProcessing));
    view.setClearButtonActionListener(new ClearButtonActionListener());
    view.setLOGOUTButtonActionListener(new LogoutButtonActionListener());
    view.setAddCompositeButtonActionListener(new AddCompositeProductListener(view, deliveryServiceProcessing));
  }

  private class ClearButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      view.clear();
      view.fillMenuTable(deliveryServiceProcessing.getMenu());
      view.setReportTxt("");
    }
  }

  private class LogoutButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      view.dispose();
      loginView.setVisible(true);
    }
  }

}
