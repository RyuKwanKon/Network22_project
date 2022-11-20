package Page.GamePagePanel;

import javax.swing.*;
import java.awt.*;

import static Page.GamePageView.currentChatting;

public class ScrollChatting extends JScrollPane {
    public static JScrollBar vertical;
    public ScrollChatting(){
        setViewportView(currentChatting);
        setBounds(30, 300, 320, 300);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        setBackground(Color.BLUE);
        setOpaque(false);
        getViewport().setOpaque(false);
        setBorder(null);
        vertical = getVerticalScrollBar();
    }
}
