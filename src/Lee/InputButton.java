package Lee;

import Component.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Frame.Frame.*;
import SW.*;

import static Frame.Frame.*;

public class InputButton extends JButton{
    public InputButton(){
        Color border = new Color(254, 132, 207);
        setBounds(540, 500, 200, 40);
        setForeground(border);
        setFont(FontStyle.BidFont);
        setText("Start");
        setHorizontalAlignment(JLabel.CENTER);
        setBorder(new CustomLineBorder(border, 2,
                CustomLineBorder.RIGHT | CustomLineBorder.TOP | CustomLineBorder.LEFT | CustomLineBorder.BOTTOM));
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = Login.loginInput.getText();
                outMsg.println("userConnection/" + userName);
                LoginPage.setVisible(false);
                gamePage.setVisible(true);
            }
        });
    }
}
