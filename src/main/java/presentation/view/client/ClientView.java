package presentation.view.client;

import business.composite.MenuItem;
import business.validator.FieldValidator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClientView extends JFrame {
  private JPanel mainPanel;
  private JPanel infoPanel;
  private JTextField titleTxtFld;
  private JTextField ratingTxtFld;
  private JTextField caloriesTxtFld;
  private JTextField proteinTxtFld;
  private JTextField fatTxtFld;
  private JTextField sodiumTxtFld;
  private JTextField priceTxtFld;
  private JButton searchProductsButton;
  private JButton createOrderButton;
  private JTable menuTable;
  private JTextArea billTxtArea;
  private JButton clearButton;
  private JButton LOGOUTButton;
  private JButton refreshButton;

  public ClientView() {
    setContentPane(mainPanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    pack();
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    init();
  }

  private void init() {
    DefaultTableModel model = new DefaultTableModel() {
      public Class<?> getColumnClass(int column) {
        if (column == 0) {
          return Boolean.class;
        }
        return String.class;
      }
    };
    menuTable.setModel(model);
    model.addColumn("Select");
    model.addColumn("ID");
    model.addColumn("Title");
    model.addColumn("Rating");
    model.addColumn("Calories");
    model.addColumn("Protein");
    model.addColumn("Fat");
    model.addColumn("Sodium");
    model.addColumn("Price");
  }

  public void addRefreshButtonActionListener(ActionListener listener) {
    refreshButton.addActionListener(listener);
  }

  public void addClearButtonActionListener(ActionListener listener) {
    clearButton.addActionListener(listener);
  }

  public void addSearchProductsButtonActionListener(ActionListener listener) {
    searchProductsButton.addActionListener(listener);
  }

  public void addCreateOrderButtonActionListener(ActionListener listener) {
    createOrderButton.addActionListener(listener);
  }

  public void addLOGOUTButtonActionListener(ActionListener listener) {
    LOGOUTButton.addActionListener(listener);
  }

  public String getTitle() {
    return titleTxtFld.getText();
  }

  public String getRating() {
    return ratingTxtFld.getText();
  }

  public String getCalories() {
    return caloriesTxtFld.getText();
  }

  public String getProtein() {
    return proteinTxtFld.getText();
  }

  public String getFat() {
    return fatTxtFld.getText();
  }

  public String getSodium() {
    return sodiumTxtFld.getText();
  }

  public String getPrice() {
    return priceTxtFld.getText();
  }

  public String getBill() {
    return billTxtArea.getText();
  }

  public void setBill(String bill) {
    billTxtArea.setText(bill);
  }

  public void fillMenuTable(List<MenuItem> menuItems) {
    DefaultTableModel model = (DefaultTableModel) menuTable.getModel();
    model.setRowCount(0);
    for (MenuItem menuItem : menuItems) {
      model.addRow(new Object[]{false, menuItem.getId(), menuItem.getTitle(),
              menuItem.computeRating(), menuItem.computeCalories(),
              menuItem.computeProtein(), menuItem.computeFat(),
              menuItem.computeSodium(), menuItem.computePrice()});
    }
  }

  public void showMessage(String message, int type) {
    JOptionPane.showMessageDialog(this, message, "Message", type);
  }

  public boolean areAllFieldsEmpty() {
    return FieldValidator.isFieldEmpty(titleTxtFld.getText())
            && FieldValidator.isFieldEmpty(ratingTxtFld.getText())
            && FieldValidator.isFieldEmpty(caloriesTxtFld.getText())
            && FieldValidator.isFieldEmpty(proteinTxtFld.getText())
            && FieldValidator.isFieldEmpty(fatTxtFld.getText())
            && FieldValidator.isFieldEmpty(sodiumTxtFld.getText())
            && FieldValidator.isFieldEmpty(priceTxtFld.getText());
  }

  public void clear() {
    titleTxtFld.setText("");
    ratingTxtFld.setText("");
    caloriesTxtFld.setText("");
    proteinTxtFld.setText("");
    fatTxtFld.setText("");
    sodiumTxtFld.setText("");
    priceTxtFld.setText("");
    billTxtArea.setText("");
  }

  public List<Integer> getCheckedRowsOfJTable() {
    DefaultTableModel model = (DefaultTableModel) menuTable.getModel();
    List<Integer> selectedIds = new ArrayList<>();
    for (int i = 0; i < model.getRowCount(); i++) {
      if (model.getValueAt(i, 0).equals(true)) {
        selectedIds.add((Integer) model.getValueAt(i, 1));
      }
    }
    return selectedIds;
  }

  public void setBillTxtArea(String bill) {
    billTxtArea.setText(bill);
  }

}
