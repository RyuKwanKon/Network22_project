package Page.GamePagePanel;

import ClientThread.ChatThread;
import Page.GamePageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static Frame.Frame.client;
import static Page.GamePagePanel.ScrollChatting.vertical;
import static Page.GamePageView.currentChatting;

public class UserCurrentText extends JTextField {
    String text = "";
    public UserCurrentText(){
        setPreferredSize(new Dimension(260, 28));
        setForeground(new Color(154, 254, 132));
        setBackground(new Color(18, 23, 28));
        setBorder(null);
        setOpaque(false);
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                setText(getText());
            }
        });
        setFocusable(true);
        requestFocus();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(getText() != "") {
                        currentChatting.add(new UserChat("이승원:  " + OnChat.input.getText(), new Color(154, 254, 132)));
                        ChatThread thread = new ChatThread(client.getSocket());
                        thread.start();
                    }
                    GamePageView.onChat.setVisible(false);
                    vertical.setValue(vertical.getMaximum());
                    GamePageView.on = !GamePageView.on;
                    setText("");
                }
            }
        });
    }
}
