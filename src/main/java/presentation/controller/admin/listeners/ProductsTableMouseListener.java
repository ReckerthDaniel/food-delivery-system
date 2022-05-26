package presentation.controller.admin.listeners;

import business.IDeliveryServiceProcessing;
import presentation.view.admin.AdminView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductsTableMouseListener implements MouseListener {
  private final AdminView view;
  private final IDeliveryServiceProcessing deliveryServiceProcessing;

  public ProductsTableMouseListener(AdminView view, IDeliveryServiceProcessing deliveryServiceProcessing) {
    this.view = view;
    this.deliveryServiceProcessing = deliveryServiceProcessing;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int row = view.getMenuTable().getSelectedRow();
    if (row != -1) {
      view.setMenuItemInfoOfRow(row);
      view.setReportTxt(deliveryServiceProcessing.findProductById(Integer.parseInt(view.getProductId())).toString());
      //view.fillMenuTable(deliveryServiceProcessing.getMenu());
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
