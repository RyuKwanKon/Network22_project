package Page.GamePagePanel;

import Component.*;
import Page.GamePageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OnChat extends JPanel {
    public static JTextField input = new UserCurrentText();
    public OnChat(){
        Color background = new Color(18, 23, 28);
        Color border = new Color(154, 254, 132);
        setLayout(new BorderLayout());
        setBackground(background);
        setBounds(30, 600, 270, 30);
        setBorder(new CustomLineBorder(border, 1,
                CustomLineBorder.RIGHT | CustomLineBorder.TOP | CustomLineBorder.LEFT | CustomLineBorder.BOTTOM));
        input.setText("");
        add(input, BorderLayout.CENTER);
    }
}
