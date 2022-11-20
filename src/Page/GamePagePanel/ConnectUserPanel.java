package Page.GamePagePanel;

import javax.swing.*;
import java.awt.*;

public class ConnectUserPanel extends JPanel {
    public ConnectUserPanel(){
        setLayout(new GridLayout(0, 1, 10, 0));
        setOpaque(false);
        setBounds(10, 10, 200, 200);
        add(new ConnectUser("User: " + "류관곤"));
        add(new ConnectUser("User: " + "이승원"));
        add(new ConnectUser("User: " + "배민우"));
        add(new ConnectUser("User: " + "이소정"));
    }
}
