package Lee;

import Component.FontStyle;
import Page.GamePagePanel.GamePage;

import javax.swing.*;
import java.awt.*;

public class NotEntry extends JPanel {
    public NotEntry(){
        setBounds(0, 0, 1280, 720);
        setLayout(null);
        setVisible(true);
        add(new CurrentTitle(600, 240, "개"));
        add(new CurrentTitle(630, 240, "임"));
        add(new CurrentTitle(660, 240, "중"));
        add(new CurrentTitle(690, 240, "."));
        add(new CurrentTitle(720, 240, "."));
        add(new CurrentTitle(750, 240, "."));
        add(new GamePage());
    }
    class CurrentTitle extends JLabel{
        CurrentTitle(int x, int y, String title){
            setText(title);
            setBounds(x, y, 70, 70);
            setFont(FontStyle.BidFont);
            setForeground(new Color(154, 254, 132));
        }
    }
}
