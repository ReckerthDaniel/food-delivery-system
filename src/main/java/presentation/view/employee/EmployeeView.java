package presentation.view.employee;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame {
  private JPanel mainPanel;
  private JButton LOGOUTButton;
  private JTextArea notificationTxtArea;

  public EmployeeView() {
    setContentPane(mainPanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    pack();
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    notificationTxtArea.setEditable(false);
  }

  public void addLogoutButtonListener(ActionListener listener) {
    LOGOUTButton.addActionListener(listener);
  }

  public void setNotificationTxtArea(String txt) {
    notificationTxtArea.setText(txt);
  }

  public void appendTextToNotificationTxtArea(String txt) {
    notificationTxtArea.append(txt);
  }

}
