package presentation.view.admin;

import business.composite.BaseProduct;
import business.composite.MenuItem;
import business.validator.FieldValidator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class AdminView extends JFrame {
  private JPanel mainPanel;
  private JTextField productIdTxtFld;
  private JTextField productTitleTxtFld;
  private JTextField productRatingTxtFld;
  private JTextField productCaloriesTxtFld;
  private JTextField productProteinTxtFld;
  private JTextField productFatTxtFld;
  private JTextField productSodiumTxtFld;
  private JTextField productPriceTxtFld;
  private JButton addButton;
  private JButton deleteButton;
  private JButton modifyButton;
  private JButton searchButton;
  private JTextField compositeTitleTxtFld;
  private JTextField startDateTxtFld;
  private JTextField endDateTxtFld;
  private JTextField clientOrderFreqTxtFld;
  private JTextField priceComparatorTxtFld;
  private JTextField productOrderFreqTxtFld;
  private JButton generate1Button;
  private JButton generate2Button;
  private JButton generate3Button;
  private JButton generate4Button;
  private JTextField productOrderDateTxtFld;
  private JTable menuTable;
  private JTextArea reportTxtArea;
  private JButton importMenuButton;
  private JButton clearButton;
  private JButton LOGOUTButton;
  private JButton addCompositeButton;

  public AdminView() {
    setContentPane(mainPanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    pack();
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    init();
  }

  private void init() {
    productIdTxtFld.setEditable(false);
    // menuTable.setModel(new DefaultTableModel(null, new String[]{"ID", "Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"}));
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

  public void setAddButtonActionListener(ActionListener listener) {
    addButton.addActionListener(listener);
  }

  public void setDeleteButtonActionListener(ActionListener listener) {
    deleteButton.addActionListener(listener);
  }

  public void setModifyButtonActionListener(ActionListener listener) {
    modifyButton.addActionListener(listener);
  }

  public void setSearchButtonActionListener(ActionListener listener) {
    searchButton.addActionListener(listener);
  }

  public void setGenerate1ButtonActionListener(ActionListener listener) {
    generate1Button.addActionListener(listener);
  }

  public void setGenerate2ButtonActionListener(ActionListener listener) {
    generate2Button.addActionListener(listener);
  }

  public void setGenerate3ButtonActionListener(ActionListener listener) {
    generate3Button.addActionListener(listener);
  }

  public void setGenerate4ButtonActionListener(ActionListener listener) {
    generate4Button.addActionListener(listener);
  }

  public void setImportMenuButtonActionListener(ActionListener listener) {
    importMenuButton.addActionListener(listener);
  }

  public void setMenuTableActionListener(MouseListener listener) {
    menuTable.addMouseListener(listener);
  }

  public void setClearButtonActionListener(ActionListener listener) {
    clearButton.addActionListener(listener);
  }

  public void setLOGOUTButtonActionListener(ActionListener listener) {
    LOGOUTButton.addActionListener(listener);
  }

  public void setAddCompositeButtonActionListener(ActionListener listener) {
    addCompositeButton.addActionListener(listener);
  }

  public void disableImportMenuButton() {
    importMenuButton.setEnabled(false);
  }

  public String getProductId() {
    return productIdTxtFld.getText();
  }

  public String getProductTitle() {
    return productTitleTxtFld.getText();
  }

  public String getProductRating() {
    return productRatingTxtFld.getText();
  }

  public String getProductCalories() {
    return productCaloriesTxtFld.getText();
  }

  public String getProductProtein() {
    return productProteinTxtFld.getText();
  }

  public String getProductFat() {
    return productFatTxtFld.getText();
  }

  public String getProductSodium() {
    return productSodiumTxtFld.getText();
  }

  public String getProductPrice() {
    return productPriceTxtFld.getText();
  }

  public String getCompositeTitle() {
    return compositeTitleTxtFld.getText();
  }

  public String getStartDate() {
    return startDateTxtFld.getText();
  }

  public String getEndDate() {
    return endDateTxtFld.getText();
  }

  public int getClientOrderFreq() {
    return Integer.parseInt(clientOrderFreqTxtFld.getText());
  }

  public int getPriceComparator() {
    return Integer.parseInt(priceComparatorTxtFld.getText());
  }

  public int getProductOrderFreq() {
    return Integer.parseInt(productOrderFreqTxtFld.getText());
  }

  public String getProductOrderDate() {
    return productOrderDateTxtFld.getText();
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

  public void setMenuItemInfoOfRow(int row) {
    String id = menuTable.getValueAt(row, 1).toString();
    String title = menuTable.getValueAt(row, 2).toString();
    String rating = menuTable.getValueAt(row, 3).toString();
    String calories = menuTable.getValueAt(row, 4).toString();
    String protein = menuTable.getValueAt(row, 5).toString();
    String fat = menuTable.getValueAt(row, 6).toString();
    String sodium = menuTable.getValueAt(row, 7).toString();
    String price = menuTable.getValueAt(row, 8).toString();
    productIdTxtFld.setText(id);
    productTitleTxtFld.setText(title);
    productRatingTxtFld.setText(rating);
    productCaloriesTxtFld.setText(calories);
    productProteinTxtFld.setText(protein);
    productFatTxtFld.setText(fat);
    productSodiumTxtFld.setText(sodium);
    productPriceTxtFld.setText(price);
  }

  public JTable getMenuTable() {
    return menuTable;
  }

  public BaseProduct getProductFromInfo() throws IllegalArgumentException {
    if (areProductFieldsValid()) {
      String title = getProductTitle();
      double rating = Double.parseDouble(getProductRating());
      int calories = Integer.parseInt(getProductCalories());
      int proteins = Integer.parseInt(getProductProtein());
      int fats = Integer.parseInt(getProductFat());
      int sodium = Integer.parseInt(getProductSodium());
      int price = Integer.parseInt(getProductPrice());
      return new BaseProduct(title, rating, calories, proteins, fats, sodium, price);
    } else {
      throw new IllegalArgumentException("Invalid product fields");
    }
  }

  public void showMessage(String message, int type) {
    JOptionPane.showMessageDialog(this, message, "Message", type);
  }

  public void clearReport() {
    reportTxtArea.setText("");
  }

  public void clear() {
    productIdTxtFld.setText("");
    productTitleTxtFld.setText("");
    productRatingTxtFld.setText("");
    productCaloriesTxtFld.setText("");
    productProteinTxtFld.setText("");
    productFatTxtFld.setText("");
    productSodiumTxtFld.setText("");
    productPriceTxtFld.setText("");
    compositeTitleTxtFld.setText("");
    reportTxtArea.setText("");
  }

  public void clearReportArea() {
    reportTxtArea.setText("");
  }

  public void setReportTxt(String txt) {
    reportTxtArea.setText(reportTxtArea.getText() + "\n" + txt);
  }

  public boolean areProductFieldsValid() {
    boolean titleValid = !FieldValidator.isFieldEmpty(getProductTitle());
    boolean ratingValid = FieldValidator.isFieldRealNumber(getProductRating());
    boolean caloriesValid = FieldValidator.isFieldNumber(getProductCalories());
    boolean proteinValid = FieldValidator.isFieldNumber(getProductProtein());
    boolean fatValid = FieldValidator.isFieldNumber(getProductFat());
    boolean sodiumValid = FieldValidator.isFieldNumber(getProductSodium());
    boolean priceValid = FieldValidator.isFieldNumber(getProductPrice());

    return titleValid && ratingValid && caloriesValid && proteinValid && fatValid && sodiumValid && priceValid;
  }

  public boolean areAllFieldsEmpty() {
    return FieldValidator.isFieldEmpty(productTitleTxtFld.getText())
            && FieldValidator.isFieldEmpty(productRatingTxtFld.getText())
            && FieldValidator.isFieldEmpty(productCaloriesTxtFld.getText())
            && FieldValidator.isFieldEmpty(productProteinTxtFld.getText())
            && FieldValidator.isFieldEmpty(productFatTxtFld.getText())
            && FieldValidator.isFieldEmpty(productSodiumTxtFld.getText())
            && FieldValidator.isFieldEmpty(productPriceTxtFld.getText());
  }

}
