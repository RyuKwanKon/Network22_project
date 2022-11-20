package Page.GamePagePanel;

import javax.swing.*;
import java.awt.*;

public class ConnectUser extends JLabel {
    public static Font BidFont = new Font("Tahoma", Font.CENTER_BASELINE, 17);
    public ConnectUser(String UserName){
        setPreferredSize(new Dimension(200, 40));
        setFont(BidFont);
        setForeground(new Color(132, 167, 254));
        setText(UserName);
    }
}
