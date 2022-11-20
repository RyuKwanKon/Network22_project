package Lee;

import Component.CustomLineBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class InputUser extends JTextField {
    Font lostFont = new Font("Tahoma", Font.ITALIC, 15);
    Font gainFont = new Font("Tahoma", Font.PLAIN, 15);
    public InputUser(){
        Color border = new Color(115, 255, 138);
        setBounds(540, 450, 200, 40);
        setForeground(new Color(255, 255, 255));
        setBackground(new Color(18, 23, 28));
        setOpaque(false);
        setFocusable(false);
        setFocusable(true);
        setText("UserName");
        setBorder(new CustomLineBorder(border, 3,
                CustomLineBorder.RIGHT | CustomLineBorder.TOP | CustomLineBorder.LEFT | CustomLineBorder.BOTTOM));
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals("UserName")) {
                    setText("");
                    setFont(gainFont);
                } else {
                    setText(getText());
                    setFont(gainFont);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (getText().equals("UserName")|| getText().length()==0) {
                    setText("UserName");
                    setFont(lostFont);
                    setForeground(Color.WHITE);
                } else {
                    setText(getText());
                    setFont(gainFont);
                    setForeground(Color.WHITE);
                }
            }
        });
    }
}
