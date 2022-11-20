package Event;

import Page.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OnChattingEvent implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ENTER) {
            GamePageView.onChat.setVisible(true);
        }
    }
}
