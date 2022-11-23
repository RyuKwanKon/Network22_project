package Page.GamePagePanel;
import javax.swing.*;
import java.awt.*;

import static GameData.UserData.haveCoin;

public class CurrentCoin extends JLabel {
    public static Font BidFont = new Font("Tahoma", Font.CENTER_BASELINE, 28);
    public CurrentCoin(){
        setBounds(1160, 30, 100, 70);
        setFont(BidFont);
        setForeground(new Color(132, 167, 254));
        setText(String.valueOf(haveCoin));
        setHorizontalAlignment(JLabel.LEFT);
    }
}
