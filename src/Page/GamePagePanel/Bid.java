package Page.GamePagePanel;

import ClientThread.BidThread;
import Component.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Frame.MainFrame.client;
import static java.awt.Cursor.HAND_CURSOR;

public class Bid extends JLabel {
    public Bid(){
        Color border = new Color(254, 132, 207);
        setBounds(550, 400, 220, 40);
        setForeground(border);
        setFont(FontStyle.BidFont);
        setText("wait");
        setEnabled(false);
        setCursor(new Cursor(HAND_CURSOR));
        setHorizontalAlignment(JLabel.CENTER);
        setBorder(new CustomLineBorder(border, 2,
                CustomLineBorder.RIGHT | CustomLineBorder.TOP | CustomLineBorder.LEFT | CustomLineBorder.BOTTOM));
    }
}
