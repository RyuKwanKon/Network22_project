package Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Text extends JTextField {
    Font lostFont = new Font("Tahoma", Font.ITALIC, 11);
    Font gainFont = new Font("Tahoma", Font.PLAIN, 11);
    public Text(int x, int y, int width, int height, String hint, Color background){
        if(x == -1 && y == -1){
            setLayout(null);
            setPreferredSize(new Dimension(width, height));
            System.out.println("eq");
        }
        else{
            setLayout(null);
            setBounds(x, y, width, height);
        }
        setBackground(background);
        setText(hint);
        setFont(lostFont);
        setForeground(Color.GRAY);

        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(hint)) {
                    setText("");
                    setFont(gainFont);
                } else {
                    setText(getText());
                    setFont(gainFont);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (getText().equals(hint)|| getText().length()==0) {
                    setText(hint);
                    setFont(lostFont);
                    setForeground(Color.GRAY);
                } else {
                    setText(getText());
                    setFont(gainFont);
                    setForeground(Color.BLACK);
                }
            }
        });
    }
}
