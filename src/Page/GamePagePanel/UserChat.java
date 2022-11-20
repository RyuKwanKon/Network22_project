package Page.GamePagePanel;

import Component.*;
import javax.swing.*;
import java.awt.*;

public class UserChat extends JLabel {
    public UserChat(String message, Color fontColor){
        setText(message);
        setFont(FontStyle.chatFont);
        setForeground(fontColor);
        setPreferredSize(new Dimension(320, 19));
        setOpaque(false);
    }
}
