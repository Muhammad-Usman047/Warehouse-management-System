package warehousemanagementsystem112;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class ManagerLogin extends UserLogin {

    public ManagerLogin() {
        super("Manager Login");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("Usman") && password.equals("usman123")) {
                new Dashboard();
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid login");
            }
        } else if (e.getSource() == closeButton) {
            frame.dispose();
        }
    }
}
