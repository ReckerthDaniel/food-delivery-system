package presentation.view.login;

import business.enums.Role;

import javax.swing.*;

import java.awt.event.ActionListener;

public class LoginView extends JFrame {
  private JTextField usernameTxtFld;
  private JPasswordField passwordTxtFld;
  private JButton loginButton;
  private JButton registerButton;
  private JComboBox comboBox1;
  private JPanel mainPanel;

  public LoginView() {
    setContentPane(mainPanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    pack();
    setVisible(true);
    setResizable(false);
  }

  public void setLoginButtonListener(ActionListener listener) {
    loginButton.addActionListener(listener);
  }

  public void setRegisterButtonListener(ActionListener listener) {
    registerButton.addActionListener(listener);
  }

  public String getUsername() {
    return usernameTxtFld.getText();
  }

  public String getPassword() {
    return new String(passwordTxtFld.getPassword());
  }

  public Role getRole() {
    final String selectedItem = (String) comboBox1.getSelectedItem();
    return Role.valueOf(selectedItem);
  }

  public void clearFields() {
    usernameTxtFld.setText("");
    passwordTxtFld.setText("");
  }

  public void showMessage(String message, int type) {
    JOptionPane.showMessageDialog(this, message, "Message", type);
  }

}
